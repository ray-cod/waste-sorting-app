package com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.services;

import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.exceptions.ResourceNotFoundException;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.models.RecyclingTip;
import com.enviro.assessment.grad001.raimidikamona.waste_sorting_app.repositories.RecyclingTipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecyclingTipServiceTest {

    @Mock
    private RecyclingTipRepository repository;

    @InjectMocks
    private RecyclingTipService testService;

    @Test
    void getAllRecyclingTips() {
        // When
        testService.getAllRecyclingTips();
        // Then
        verify(repository).findAll();
    }

    @Test
    void testGetRecyclingTipById() {
        // Given
        RecyclingTip tip = new RecyclingTip(
                1L, "Rinse plastic containers before recycling to remove food residue.",
                "Plastic", "https://www.exampleLink.com/");
        when(repository.findById(1L)).thenReturn(Optional.of(tip));
        when(repository.existsById(1L)).thenReturn(true);

        // When
        Optional<RecyclingTip> result = testService.getRecyclingTipById(tip.getId());

        // Then
        assertTrue(result.isPresent());
        assertEquals("Plastic", result.get().getApplicableMaterial());
        verify(repository).findById(1L);
    }

    @Test
    void testGetRecyclingTipWithInvalidId(){
        assertThrows(ResourceNotFoundException.class,
                () -> testService.getRecyclingTipById(1L),
                "Expected ResourceNotFoundException to be thrown for an invalid ID"
        );

        verify(repository).existsById(1L);
    }

    @Test
    void createRecyclingTip() {
        // Given
        RecyclingTip tip = new RecyclingTip(
                "Rinse plastic containers before recycling to remove food residue.",
                "Plastic", "https://www.exampleLink.com/");

        // When
        testService.createRecyclingTip(tip);

        // Then
        ArgumentCaptor<RecyclingTip> tipArgumentCaptor = ArgumentCaptor.forClass(RecyclingTip.class);
        verify(repository).save(tipArgumentCaptor.capture());

        RecyclingTip capturedCategory = tipArgumentCaptor.getValue();
        assertEquals(capturedCategory, tip);
    }

    @Test
    void updateRecyclingTip() {
        // Given
        Long id = 1L;
        RecyclingTip existingTip = new RecyclingTip(id,
                "Rinse plastic containers before recycling to remove food residue.",
                "Plastic", "https://www.exampleLink.com/");
        RecyclingTip updatedTip = new RecyclingTip(
                "Rinse plastic containers before recycling to remove food residue.",
                "Plastic", "https://www.exampleLink_updated.com/");

        when(repository.findById(id)).thenReturn(Optional.of(existingTip));
        when(repository.save(any(RecyclingTip.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        RecyclingTip result = testService.updateRecyclingTip(id, updatedTip);

        // Then
        assertNotNull(result);
        assertEquals("https://www.exampleLink_updated.com/", result.getExternalLink());

        verify(repository).findById(id);
        verify(repository).save(existingTip);
    }

    @Test
    void updateNonExistingRecyclingTip() {
        // Given
        Long id = 1L;
        RecyclingTip updatedTip = new RecyclingTip(
                "Rinse plastic containers before recycling to remove food residue.",
                "Plastic", "https://www.exampleLink_updated.com/");

        when(repository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> testService.updateRecyclingTip(id, updatedTip),
                "Expected RuntimeException for non-existing WasteCategory"
        );

        assertEquals("RecyclingTip with ID 1 not found", exception.getMessage());
        verify(repository).findById(id);
        verify(repository, never()).save(any());
    }

    @Test
    void deleteRecyclingTip() {
        // Given
        doNothing().when(repository).deleteById(1L);
        when(repository.existsById(1L)).thenReturn(true);

        // When
        testService.deleteRecyclingTip(1L);

        // Then
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void getRecyclingTipsByMaterial() {
        // Given
        RecyclingTip tip = new RecyclingTip(
                "Rinse plastic containers before recycling to remove food residue.",
                "Plastic", "https://www.exampleLink.com/");
        when(repository.findByApplicableMaterial("Plastic")).thenReturn(Arrays.asList(tip));

        // When
        List<RecyclingTip> result = testService.getRecyclingTipsByMaterial(tip.getApplicableMaterial());

        // Then
        assertTrue(!result.isEmpty());
        assertEquals("Plastic", result.get(0).getApplicableMaterial());
        verify(repository).findByApplicableMaterial("Plastic");
    }
}