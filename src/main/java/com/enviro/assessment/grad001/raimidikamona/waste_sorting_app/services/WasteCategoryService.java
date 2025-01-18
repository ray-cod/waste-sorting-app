package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.services;


import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.WasteCategory;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteCategoryService {

    private final WasteCategoryRepository repository;

    @Autowired
    public WasteCategoryService(WasteCategoryRepository repository){
        this.repository = repository;
    }

    /**
     * Retrieve all waste categories.
     *
     * @return a list of WasteCategory objects.
     */
    public List<WasteCategory> getAllWasteCategories() {
        return repository.findAll();
    }

    /**
     * Retrieve a specific waste category by its ID.
     *
     * @param id the ID of the WasteCategory.
     * @return an Optional containing the WasteCategory if found, or empty otherwise.
     */
    public Optional<WasteCategory> getWasteCategoryById(Long id) {
        return repository.findById(id);
    }

    /**
     * Add a new waste category.
     *
     * @param wasteCategory the WasteCategory object to save.
     * @return the saved WasteCategory object.
     */
    public WasteCategory createWasteCategory(WasteCategory wasteCategory) {
        return repository.save(wasteCategory);
    }

    /**
     * Update a specific waste category by ID.
     *
     * @param id            the ID of the WasteCategory to update.
     * @param updatedCategory the WasteCategory object containing updated details.
     * @return the updated WasteCategory object.
     * @throws RuntimeException if the category is not found.
     */
    public WasteCategory updateWasteCategory(Long id, WasteCategory updatedCategory) {
        return repository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(updatedCategory.getName());
                    existingCategory.setDescription(updatedCategory.getDescription());
                    existingCategory.setRecyclable(updatedCategory.isRecyclable());
                    return repository.save(existingCategory);
                })
                .orElseThrow(() -> new RuntimeException("WasteCategory with ID " + id + " not found"));
    }

    /**
     * Delete a waste category by its ID.
     *
     * @param id the ID of the WasteCategory to delete.
     */
    public void deleteWasteCategory(Long id) {
        repository.deleteById(id);
    }

    /**
     * Retrieve waste categories by name (case-insensitive, partial match).
     *
     * @param name the name or partial name of the WasteCategory.
     * @return a list of matching WasteCategory objects.
     */
    public List<WasteCategory> findWasteCategoriesByName(String name) {
        return repository.findByName(name);
    }
}
