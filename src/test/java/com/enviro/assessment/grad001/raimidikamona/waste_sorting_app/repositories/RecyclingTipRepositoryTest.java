package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.RecyclingTip;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.WasteCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RecyclingTipRepositoryTest {

    @Autowired
    private RecyclingTipRepository testRepository;

    @AfterEach
    void tearDown(){
        testRepository.deleteAll();
    }

    @Test
    void findByApplicableMaterialWithValidMaterial() {
        // Given
        RecyclingTip tip_1 = new RecyclingTip(
                "Rinse plastic containers before recycling to remove food residue.",
                "Plastic", "https://www.exampleLink.com/");
        RecyclingTip savedTip = testRepository.save(tip_1);

        // When
        List<RecyclingTip> result = testRepository.findByApplicableMaterial("Plastic");

        // Then
        List<RecyclingTip> expected = Arrays.asList(savedTip);
        assertEquals(result, expected);
    }

    @Test
    void findByApplicableMaterialWithInvalidMaterial() {
        // Given
        RecyclingTip tip_1 = new RecyclingTip(
                "Rinse plastic containers before recycling to remove food residue.",
                "Plastic", "https://www.exampleLink.com/");
        testRepository.save(tip_1);

        // When
        List<RecyclingTip> result = testRepository.findByApplicableMaterial("Glass");

        // Then
        List<RecyclingTip> expected = Arrays.asList();
        assertEquals(result, expected);
    }
}