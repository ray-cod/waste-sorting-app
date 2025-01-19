package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.services;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.exceptions.ResourceNotFoundException;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.DisposalGuideline;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisposalGuidelineService {

    private final DisposalGuidelineRepository repository;
    private final WasteCategoryRepository wasteCategoryRepository;

    @Autowired
    public DisposalGuidelineService(DisposalGuidelineRepository repository, WasteCategoryRepository wasteCategoryRepository){
        this.repository = repository;
        this.wasteCategoryRepository = wasteCategoryRepository;
    }

    /**
     * Retrieve all disposal guidelines.
     *
     * @return a list of DisposalGuideline objects.
     */
    public List<DisposalGuideline> getAllDisposalGuidelines() {
        return repository.findAll();
    }

    /**
     * Retrieve a specific disposal guideline by its ID.
     *
     * @param id the ID of the DisposalGuideline.
     * @return an Optional containing the DisposalGuideline if found, or empty otherwise.
     */
    public Optional<DisposalGuideline> getDisposalGuidelineById(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("DisposalGuideline with ID " + id + " not found");
        }
        return repository.findById(id);
    }

    /**
     * Add a new disposal guideline.
     *
     * @param guideline the DisposalGuideline object to save.
     * @return the saved DisposalGuideline object.
     */
    public DisposalGuideline createDisposalGuideline(DisposalGuideline guideline) {
        validateWasteCategoryExists(guideline.getWasteCategory().getId());
        return repository.save(guideline);
    }

    /**
     * Update a specific disposal guideline by ID.
     *
     * @param id            the ID of the DisposalGuideline to update.
     * @param updatedGuideline the DisposalGuideline object containing updated details.
     * @return the updated DisposalGuideline object.
     * @throws RuntimeException if the guideline is not found.
     */
    public DisposalGuideline updateDisposalGuideline(Long id, DisposalGuideline updatedGuideline) {
        validateWasteCategoryExists(updatedGuideline.getWasteCategory().getId());
        return repository.findById(id)
                .map(existingGuideline -> {
                    existingGuideline.setName(updatedGuideline.getName());
                    existingGuideline.setWasteCategory(updatedGuideline.getWasteCategory());
                    existingGuideline.setDescription(updatedGuideline.getDescription());
                    existingGuideline.setDisposalInstructions(updatedGuideline.getDisposalInstructions());
                    return repository.save(existingGuideline);
                })
                .orElseThrow(() -> new ResourceNotFoundException("DisposalGuideline with ID " + id + " not found"));
    }

    /**
     * Delete a disposal guideline by its ID.
     *
     * @param id the ID of the DisposalGuideline to delete.
     */
    public void deleteDisposalGuideline(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("DisposalGuideline with ID " + id + " not found");
        }
        repository.deleteById(id);
    }

    /**
     * Retrieve disposal guidelines by waste category.
     *
     * @param wasteCategoryId the ID of the WasteCategory to filter by.
     * @return a list of matching DisposalGuideline objects.
     */
    public List<DisposalGuideline> getDisposalGuidelinesByWasteCategory(Long wasteCategoryId) {
        return repository.findByWasteCategoryId(wasteCategoryId);
    }

    /**
     * Validate that the given WasteCategory exists.
     *
     * @param categoryId the ID if the provided WasteCategory.
     * @throws RuntimeException if the category doesn't exist.
     */
    private void validateWasteCategoryExists(Long categoryId) {
        if (!wasteCategoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("WasteCategory with ID " + categoryId + " does not exist");
        }
    }
}
