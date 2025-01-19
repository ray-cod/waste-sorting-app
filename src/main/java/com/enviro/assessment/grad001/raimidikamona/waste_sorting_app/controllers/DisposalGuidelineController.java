package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.controllers;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.DisposalGuideline;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.services.DisposalGuidelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposal-guidelines")
public class DisposalGuidelineController {

    private final DisposalGuidelineService service;

    @Autowired
    public DisposalGuidelineController(DisposalGuidelineService service) {
        this.service = service;
    }

    @GetMapping
    public List<DisposalGuideline> getAllDisposalGuidelines() {
        return service.getAllDisposalGuidelines();
    }

    @PostMapping
    public DisposalGuideline createDisposalGuideline(@RequestBody DisposalGuideline guideline) {
        return service.createDisposalGuideline(guideline);
    }

    @PutMapping("/{id}")
    public DisposalGuideline updateDisposalGuideline(
            @PathVariable Long id,
            @RequestBody DisposalGuideline updatedGuideline) {
        return service.updateDisposalGuideline(id, updatedGuideline);
    }

    @DeleteMapping("/{id}")
    public void deleteDisposalGuideline(@PathVariable Long id) {
        service.deleteDisposalGuideline(id);
    }

    @GetMapping("/category/{wasteCategoryId}")
    public List<DisposalGuideline> getDisposalGuidelinesByWasteCategory(
            @PathVariable Long wasteCategoryId) {
        return service.getDisposalGuidelinesByWasteCategory(wasteCategoryId);
    }
}
