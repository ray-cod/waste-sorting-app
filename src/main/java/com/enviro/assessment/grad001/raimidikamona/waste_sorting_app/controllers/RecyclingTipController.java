package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.controllers;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.RecyclingTip;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.services.RecyclingTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/recycling_tips")
public class RecyclingTipController {

    private final RecyclingTipService service;

    @Autowired
    public RecyclingTipController(RecyclingTipService service) {
        this.service = service;
    }

    @GetMapping
    public List<RecyclingTip> getAllRecyclingTips() {
        return service.getAllRecyclingTips();
    }

    @PostMapping
    public RecyclingTip createRecyclingTip(@RequestBody RecyclingTip recyclingTip) {
        return service.createRecyclingTip(recyclingTip);
    }

    @PutMapping("/{id}")
    public RecyclingTip updateRecyclingTip(
            @PathVariable Long id,
            @RequestBody RecyclingTip updatedTip) {
        return service.updateRecyclingTip(id, updatedTip);
    }

    @DeleteMapping("/{id}")
    public void deleteRecyclingTip(@PathVariable Long id) {
        service.deleteRecyclingTip(id);
    }

    @GetMapping("/material/{material}")
    public List<RecyclingTip> getRecyclingTipsByMaterial(@PathVariable String material) {
        return service.getRecyclingTipsByMaterial(material);
    }
}
