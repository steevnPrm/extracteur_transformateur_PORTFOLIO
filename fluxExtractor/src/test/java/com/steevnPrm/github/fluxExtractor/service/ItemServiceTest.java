package com.steevnPrm.github.fluxExtractor.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.steevnPrm.github.fluxExtractor.entity.ItemEntity;
import com.steevnPrm.github.fluxExtractor.entity.ItemValidationErrorEntity;
import com.steevnPrm.github.fluxExtractor.repository.ItemRepository;
import com.steevnPrm.github.fluxExtractor.repository.ItemValidationErrorRepository;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository repository;

    @Mock
    private ItemValidationErrorRepository emRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    void testCreateItem() {
        // Arrange
        Integer value = 15;
        String desc = "Test Item";

        // Act
        ItemEntity item = itemService.createItem(value, desc);

        // Assert
        assertNotNull(item);
        assertNotNull(item.getId());
        assertEquals(value, item.getValue());
        assertEquals(desc, item.getDescription());
    }

    @Test
    void testSaveErrorlog() {
        // Arrange
        ItemEntity item = new ItemEntity();
        item.setId("bad-id");

        ItemValidationErrorEntity mockSavedError = new ItemValidationErrorEntity();
        mockSavedError.setErrorMessage("Log mal formatée");
        mockSavedError.setData(item);

        when(emRepository.save(any(ItemValidationErrorEntity.class))).thenReturn(mockSavedError);

        // Act
        ItemValidationErrorEntity result = itemService.saveErrorlog(item);

        // Assert
        assertNotNull(result);
        assertEquals("Log mal formatée", result.getErrorMessage());
        verify(emRepository, times(1)).save(any(ItemValidationErrorEntity.class));
    }

    @Test
    void testProcessFilterAndSave() {
        // Arrange
        ItemEntity validItem = new ItemEntity();
        validItem.setId("valid-id");
        validItem.setValue(10);
        validItem.setDescription("Valid");

        ItemEntity invalidValueItem = new ItemEntity();
        invalidValueItem.setId("invalid-value-id");
        invalidValueItem.setValue(-5);
        invalidValueItem.setDescription("Invalid Value");

        ItemEntity nullValueItem = new ItemEntity();
        nullValueItem.setId("null-value-id");
        nullValueItem.setValue(null);
        nullValueItem.setDescription("Null Value");

        ItemEntity nullDescItem = new ItemEntity();
        nullDescItem.setId("null-desc-id");
        nullDescItem.setValue(5);
        nullDescItem.setDescription(null);

        List<ItemEntity> items = Arrays.asList(validItem, invalidValueItem, nullValueItem, nullDescItem, null);

        // Act
        itemService.processFilterAndSave(items);

        // Assert
        // validItem should go to repository.save
        verify(repository, times(1)).save(validItem);
        // The other 4 (invalidValue, nullValue, nullDesc, null item) should go to
        // emRepository.save
        verify(emRepository, times(4)).save(any());
    }

    @Test
    void testGetAllItems() {
        // Arrange
        List<ItemEntity> items = Arrays.asList(new ItemEntity(), new ItemEntity());
        when(repository.findAll()).thenReturn(items);

        // Act
        List<ItemEntity> result = itemService.getAllItems();

        // Assert
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }
}
