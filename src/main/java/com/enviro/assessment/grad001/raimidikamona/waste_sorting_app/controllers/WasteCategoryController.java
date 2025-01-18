package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.controllers;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.WasteCategory;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.services.WasteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/waste_category")
public class WasteCategoryController {

    private final WasteCategoryService service;

    @Autowired
    public WasteCategoryController(WasteCategoryService service){
        this.service = service;
    }

    @GetMapping
    public List<WasteCategory> getAllWasteCategories() {
        return service.getAllWasteCategories();
    }

    @GetMapping("/{id}")
    public Optional<WasteCategory> getWasteCategoryById(@PathVariable Long id) {
        return service.getWasteCategoryById(id);
    }

    @PostMapping
    public WasteCategory createWasteCategory(@RequestBody WasteCategory wasteCategory) {
        return service.createWasteCategory(wasteCategory);
    }

    @PutMapping("/{id}")
    public WasteCategory updateWasteCategory(@PathVariable Long id, @RequestBody WasteCategory updatedCategory){
        return service.updateWasteCategory(id, updatedCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteWasteCategory(@PathVariable Long id) {
        service.deleteWasteCategory(id);
    }

    @GetMapping("/search")
    public List<WasteCategory> searchWasteCategoriesByName(@RequestParam String name) {
        return service.findWasteCategoriesByName(name);
    }
}
