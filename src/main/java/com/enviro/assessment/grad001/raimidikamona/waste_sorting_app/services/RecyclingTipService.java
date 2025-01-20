package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.services;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.exceptions.ResourceNotFoundException;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.RecyclingTip;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories.RecyclingTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingTipService {

    private final RecyclingTipRepository repository;

    @Autowired
    public RecyclingTipService(RecyclingTipRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieve all recycling tips.
     *
     * @return a list of RecyclingTip objects.
     */
    public List<RecyclingTip> getAllRecyclingTips() {
        return repository.findAll();
    }

    /**
     * Retrieve a specific recycling tip by its ID.
     *
     * @param id the ID of the RecyclingTip.
     * @return an Optional containing the RecyclingTip if found, or empty otherwise.
     */
    public Optional<RecyclingTip> getRecyclingTipById(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("RecyclingTip with ID " + id + " not found");
        }
        return repository.findById(id);
    }

    /**
     * Add a new recycling tip.
     *
     * @param recyclingTip the RecyclingTip object to save.
     * @return the saved RecyclingTip object.
     */
    public RecyclingTip createRecyclingTip(RecyclingTip recyclingTip) {
        return repository.save(recyclingTip);
    }

    /**
     * Update a recycling tip by ID.
     *
     * @param id           the ID of the RecyclingTip to update.
     * @param updatedTip the RecyclingTip object with updated details.
     * @return the updated RecyclingTip object.
     * @throws RuntimeException if the RecyclingTip is not found.
     */
    public RecyclingTip updateRecyclingTip(Long id, RecyclingTip updatedTip) {
        return repository.findById(id)
                .map(existingTip -> {
                    existingTip.setTip(updatedTip.getTip());
                    existingTip.setApplicableMaterial(updatedTip.getApplicableMaterial());
                    existingTip.setExternalLink(updatedTip.getExternalLink());
                    return repository.save(existingTip);
                })
                .orElseThrow(() -> new ResourceNotFoundException("RecyclingTip with ID " + id + " not found"));
    }

    /**
     * Delete a recycling tip by its ID.
     *
     * @param id the ID of the RecyclingTip to delete.
     */
    public void deleteRecyclingTip(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("RecyclingTip with ID " + id + " not found");
        }
        repository.deleteById(id);
    }

    /**
     * Retrieve recycling tips by applicable material.
     *
     * @param material the material to filter tips by.
     * @return a list of RecyclingTip objects applicable to the material.
     */
    public List<RecyclingTip> getRecyclingTipsByMaterial(String material) {
        return repository.findByApplicableMaterial(material);
    }
}
