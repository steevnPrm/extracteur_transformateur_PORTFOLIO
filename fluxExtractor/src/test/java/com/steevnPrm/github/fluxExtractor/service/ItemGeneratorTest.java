package com.steevnPrm.github.fluxExtractor.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.steevnPrm.github.fluxExtractor.entity.ItemEntity;

@ExtendWith(MockitoExtension.class)
class ItemGeneratorTest {

    @Mock
    private ItemService iService;

    @InjectMocks
    private ItemGenerator itemGenerator;

    @Test
    @SuppressWarnings("unchecked")
    void testRunThousandItems() {
        // Arrange
        when(iService.createItem(anyInt(), anyString())).thenReturn(new ItemEntity());

        // Act
        itemGenerator.runThousandItems();

        // Assert
        verify(iService, times(1000)).createItem(anyInt(), anyString());
        
        ArgumentCaptor<List<ItemEntity>> captor = ArgumentCaptor.forClass(List.class);
        verify(iService, times(1)).processFilterAndSave(captor.capture());
        
        List<ItemEntity> processedList = captor.getValue();
        assertEquals(1000, processedList.size());
    }
}
