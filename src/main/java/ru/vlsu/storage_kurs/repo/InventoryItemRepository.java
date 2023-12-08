package ru.vlsu.storage_kurs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlsu.storage_kurs.entity.InventoryItem;
import ru.vlsu.storage_kurs.entity.status.INVENTORY_ITEM_STATUS;
import ru.vlsu.storage_kurs.entity.status.SHIPMENT_STATUS;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findAllByStatus(INVENTORY_ITEM_STATUS status);
}
