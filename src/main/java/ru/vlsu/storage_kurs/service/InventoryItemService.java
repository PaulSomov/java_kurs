package ru.vlsu.storage_kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vlsu.storage_kurs.entity.BatchItem;
import ru.vlsu.storage_kurs.entity.InventoryItem;
import ru.vlsu.storage_kurs.entity.status.INVENTORY_ITEM_STATUS;
import ru.vlsu.storage_kurs.repo.InventoryItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryItemService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }

    public List<InventoryItem> getAllStoredInventoryItems() {
        return inventoryItemRepository.findAllByStatus(INVENTORY_ITEM_STATUS.STORED);
    }
    public Optional<InventoryItem> getInventoryItemById(Long id) {
        return inventoryItemRepository.findById(id);
    }

    public InventoryItem saveInventoryItem(BatchItem batchItem) {
        InventoryItem inventoryItem = new InventoryItem();

        inventoryItem.setBatch(batchItem.getBatch());
        inventoryItem.setProduct(batchItem.getProduct());
        inventoryItem.setSerialNumber(batchItem.getSerialNumber());
        inventoryItem.setStatus(INVENTORY_ITEM_STATUS.STORED);
        return inventoryItemRepository.saveAndFlush(inventoryItem);
    }

    public void deleteInventoryItem(Long id) {
        inventoryItemRepository.deleteById(id);
    }
}

