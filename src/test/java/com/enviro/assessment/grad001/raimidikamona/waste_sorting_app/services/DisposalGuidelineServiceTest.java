package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.services;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.exceptions.ResourceNotFoundException;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.DisposalGuideline;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.WasteCategory;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories.WasteCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DisposalGuidelineServiceTest {

    @Mock
    private DisposalGuidelineRepository repository;
    @Mock
    private WasteCategoryRepository wasteCategoryRepository;

    @InjectMocks
    private DisposalGuidelineService testService;

    @Test
    void testGetAllDisposalGuidelines() {
        // When
        testService.getAllDisposalGuidelines();
        // Then
        verify(repository).findAll();
    }

    @Test
    void testGetDisposalGuidelineById() {
        WasteCategory savedCategory = new WasteCategory(1L, "Organic", "Food waste", true);
        DisposalGuideline guideline = new DisposalGuideline(1L, "Composting",
                "Guide for composting organic materials.",
                "Place food scraps and yard waste in a compost bin. Avoid including meat, dairy, or oils.",
                true, savedCategory);
        when(repository.findById(1L)).thenReturn(Optional.of(guideline));
        when(repository.existsById(1L)).thenReturn(true);

        // When
        Optional<DisposalGuideline> result = testService.getDisposalGuidelineById(guideline.getId());

        // Then
        assertTrue(result.isPresent());
        assertEquals("Composting", result.get().getName());
        verify(repository).findById(1L);
    }

    @Test
    void testGetDisposalGuidelineWithInvalidId(){
        assertThrows(ResourceNotFoundException.class,
                () -> testService.getDisposalGuidelineById(1L),
                "Expected ResourceNotFoundException to be thrown for an invalid ID"
        );

        verify(repository).existsById(1L);
    }

    @Test
    void testCreateDisposalGuideline() {
        // Given
        WasteCategory savedCategory = new WasteCategory(1L, "Organic", "Food waste", true);
        DisposalGuideline guideline = new DisposalGuideline(1L, "Composting",
                "Guide for composting organic materials.",
                "Place food scraps and yard waste in a compost bin. Avoid including meat, dairy, or oils.",
                true, savedCategory);
        when(wasteCategoryRepository.existsById(1L)).thenReturn(true);

        // When
        testService.createDisposalGuideline(guideline);

        // Then
        ArgumentCaptor<DisposalGuideline> guidelineArgumentCaptor = ArgumentCaptor.forClass(DisposalGuideline.class);
        verify(repository).save(guidelineArgumentCaptor.capture());

        DisposalGuideline capturedGuideline = guidelineArgumentCaptor.getValue();
        assertEquals(capturedGuideline, guideline);
    }

    @Test
    void testUpdateDisposalGuideline() {
        // Given
        Long id = 1L;
        WasteCategory savedCategory = new WasteCategory(1L, "Organic", "Food waste", true);
        DisposalGuideline existingGuideline = new DisposalGuideline(1L, "Composting",
                "Guide for composting organic materials.",
                "Place food scraps and yard waste in a compost bin. Avoid including meat, dairy, or oils.",
                true, savedCategory);
        DisposalGuideline updatedGuideline = new DisposalGuideline(1L, "Composting",
                "Updated description.",
                "Place food scraps and yard waste in a compost bin. Avoid including meat, dairy, or oils.",
                false, savedCategory);

        when(wasteCategoryRepository.existsById(1L)).thenReturn(true);
        when(repository.findById(id)).thenReturn(Optional.of(existingGuideline));
        when(repository.save(any(DisposalGuideline.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        DisposalGuideline result = testService.updateDisposalGuideline(id, updatedGuideline);

        // Then
        assertNotNull(result);
        assertEquals("Updated description.", result.getDescription());
        assertFalse(result.isRecyclable());

        verify(repository).findById(id);
        verify(repository).save(existingGuideline);
    }

    @Test
    void testUpdateNonExistingDisposalGuideline(){
        // Given
        Long id = 1L;
        WasteCategory savedCategory = new WasteCategory(1L, "Organic", "Food waste", true);
        DisposalGuideline updatedGuideline = new DisposalGuideline(1L, "Composting",
                "Updated description.",
                "Place food scraps and yard waste in a compost bin. Avoid including meat, dairy, or oils.",
                false, savedCategory);

        when(repository.findById(id)).thenReturn(Optional.empty());
        when(wasteCategoryRepository.existsById(1L)).thenReturn(true);

        // When & Then
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> testService.updateDisposalGuideline(id, updatedGuideline),
                "Expected RuntimeException for non-existing WasteCategory"
        );

        assertEquals("DisposalGuideline with ID 1 not found", exception.getMessage());
        verify(repository).findById(id);
        verify(repository, never()).save(any());
    }

    @Test
    void testDeleteDisposalGuideline() {
        // Given
        doNothing().when(repository).deleteById(1L);
        when(repository.existsById(1L)).thenReturn(true);

        // When
        testService.deleteDisposalGuideline(1L);

        // Then
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteDisposalGuidelineWithInvalidId(){
        assertThrows(ResourceNotFoundException.class,
                () -> testService.deleteDisposalGuideline(1L),
                "Expected ResourceNotFoundException to be thrown for an invalid ID"
        );

        verify(repository).existsById(1L);
    }

    @Test
    void testGetDisposalGuidelinesByWasteCategory() {
        // Given
        WasteCategory category = new WasteCategory(1L, "Organic", "Food waste", true);
        DisposalGuideline guideline = new DisposalGuideline(1L, "Composting",
                "Guide for composting organic materials.",
                "Place food scraps and yard waste in a compost bin. Avoid including meat, dairy, or oils.",
                true, category);
        when(repository.findByWasteCategoryId(1L)).thenReturn(Arrays.asList(guideline));

        // When
        List<DisposalGuideline> result = testService.getDisposalGuidelinesByWasteCategory(category.getId());

        // Then
        assertTrue(!result.isEmpty());
        assertEquals("Composting", result.get(0).getName());
        verify(repository).findByWasteCategoryId(1L);
    }
}