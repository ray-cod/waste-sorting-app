package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.services;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.exceptions.ResourceNotFoundException;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.WasteCategory;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories.WasteCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WasteCategoryServiceTest {

    @Mock
    private WasteCategoryRepository repository;

    @InjectMocks
    private WasteCategoryService testService;

    @Test
    void testGetAllWasteCategories() {
        // When
        testService.getAllWasteCategories();
        // Then
        verify(repository).findAll();
    }

    @Test
    void testGetWasteCategoryWithValidId() {
        // Given
        WasteCategory category = new WasteCategory(1L, "Organic", "Food waste", true);
        when(repository.findById(1L)).thenReturn(Optional.of(category));
        when(repository.existsById(1L)).thenReturn(true);

        // When
        Optional<WasteCategory> result = testService.getWasteCategoryById(category.getId());

        // Then
        assertTrue(result.isPresent());
        assertEquals("Organic", result.get().getName());
        verify(repository).findById(1L);
    }

    @Test
    void testGetWasteCategoryWithInvalidId(){
        assertThrows(ResourceNotFoundException.class,
                () -> testService.getWasteCategoryById(1L),
                "Expected ResourceNotFoundException to be thrown for an invalid ID"
        );

        verify(repository).existsById(1L);
    }

    @Test
    void testCreateWasteCategory() {
        // Given
        WasteCategory category = new WasteCategory("Organic", "Food waste", true);

        // When
        testService.createWasteCategory(category);

        // Then
        ArgumentCaptor<WasteCategory> categoryArgumentCaptor = ArgumentCaptor.forClass(WasteCategory.class);
        verify(repository).save(categoryArgumentCaptor.capture());

        WasteCategory capturedCategory = categoryArgumentCaptor.getValue();
        assertEquals(capturedCategory, category);
    }

    @Test
    void testUpdateExistingWasteCategory() {
        // Given
        Long id = 1L;
        WasteCategory existingCategory = new WasteCategory(id, "Organic", "Food waste", true);
        WasteCategory updatedCategory = new WasteCategory("Updated Organic", "Updated description", false);

        when(repository.findById(id)).thenReturn(Optional.of(existingCategory));
        when(repository.save(any(WasteCategory.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        WasteCategory result = testService.updateWasteCategory(id, updatedCategory);

        // Then
        assertNotNull(result);
        assertEquals("Updated Organic", result.getName());
        assertEquals("Updated description", result.getDescription());
        assertFalse(result.isRecyclable());

        verify(repository).findById(id);
        verify(repository).save(existingCategory);
    }

    @Test
    void testUpdateNonExistingWasteCategory(){
        // Given
        Long id = 1L;
        WasteCategory updatedCategory = new WasteCategory("Updated Organic", "Updated description", false);

        when(repository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> testService.updateWasteCategory(id, updatedCategory),
                "Expected RuntimeException for non-existing WasteCategory"
        );

        assertEquals("WasteCategory with ID 1 not found", exception.getMessage());
        verify(repository).findById(id);
        verify(repository, never()).save(any());
    }

    @Test
    void testDeleteWasteCategory() {
        // Given
        doNothing().when(repository).deleteById(1L);
        when(repository.existsById(1L)).thenReturn(true);

        // When
        testService.deleteWasteCategory(1L);

        // Then
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteWasteCategoryWithInvalidId(){
        assertThrows(ResourceNotFoundException.class,
                () -> testService.deleteWasteCategory(1L),
                "Expected ResourceNotFoundException to be thrown for an invalid ID"
        );

        verify(repository).existsById(1L);
    }

    @Test
    void findWasteCategoriesByName() {
        // Given
        WasteCategory category = new WasteCategory(1L, "Organic", "Food waste", true);
        when(repository.findByName("Organic")).thenReturn(Arrays.asList(category));

        // When
        List<WasteCategory> result = testService.findWasteCategoriesByName(category.getName());

        // Then
        assertTrue(!result.isEmpty());
        assertEquals("Organic", result.get(0).getName());
        verify(repository).findByName("Organic");
    }
}