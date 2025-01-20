package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.WasteCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WasteCategoryRepositoryTest {

    @Autowired
    private WasteCategoryRepository testRepository;

    @AfterEach
    void tearDown(){
        testRepository.deleteAll();
    }

    @Test
    void testFindByNameWithValidName() {
        // Given
        WasteCategory category_1 = new WasteCategory("Organic", "Food waste", true);
        WasteCategory savedCategory = testRepository.save(category_1);

        // When
        List<WasteCategory> result = testRepository.findByName(savedCategory.getName());

        // Then
        List<WasteCategory> expected = Arrays.asList(savedCategory);
        assertEquals(result, expected);
    }

    @Test
    void testFindByNameWithinValidName() {
        // Given
        WasteCategory category_1 = new WasteCategory("Organic", "Food waste", true);
        testRepository.save(category_1);

        // When
        List<WasteCategory> result = testRepository.findByName("Plastic");

        // Then
        List<WasteCategory> expected = Arrays.asList();
        assertEquals(result, expected);
    }
}