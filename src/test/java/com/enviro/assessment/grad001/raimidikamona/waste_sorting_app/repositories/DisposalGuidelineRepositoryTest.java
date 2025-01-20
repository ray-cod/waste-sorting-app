package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.DisposalGuideline;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.WasteCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DisposalGuidelineRepositoryTest {

    @Autowired
    private DisposalGuidelineRepository testRepository;

    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    @AfterEach
    void tearDown(){
        testRepository.deleteAll();
        wasteCategoryRepository.deleteAll();
    }

    @Test
    void findByWasteCategoryIdWithValidId() {
        // Given
        WasteCategory category = new WasteCategory("Organic", "Food waste", true);
        WasteCategory savedCategory = wasteCategoryRepository.save(category);
        DisposalGuideline guideline_1 = new DisposalGuideline("Composting", "Guide for composting organic materials.", "Place food scraps and yard waste in a compost bin. Avoid including meat, dairy, or oils.", true, savedCategory);
        DisposalGuideline savedGuideline = testRepository.save(guideline_1);

        // When
        List<DisposalGuideline> result = testRepository.findByWasteCategoryId(savedCategory.getId());

        // Then
        List<DisposalGuideline> expected = Arrays.asList(savedGuideline);
        assertEquals(result, expected);
    }

    @Test
    void findByWasteCategoryIdWithInvalidId() {
        // Given
        WasteCategory category_1 = new WasteCategory("Organic", "Food waste", true);
        WasteCategory savedCategory_1 = wasteCategoryRepository.save(category_1);
        WasteCategory category_2 = new WasteCategory("Plastic", "Recyclable plastic", true);
        WasteCategory savedCategory_2 = wasteCategoryRepository.save(category_2);
        DisposalGuideline guideline_1 = new DisposalGuideline("Composting", "Guide for composting organic materials.", "Place food scraps and yard waste in a compost bin. Avoid including meat, dairy, or oils.", true, savedCategory_1);
        DisposalGuideline savedGuideline = testRepository.save(guideline_1);

        // When
        List<DisposalGuideline> result = testRepository.findByWasteCategoryId(savedCategory_2.getId());

        // Then
        List<DisposalGuideline> expected = Arrays.asList();
        assertEquals(result, expected);
    }
}