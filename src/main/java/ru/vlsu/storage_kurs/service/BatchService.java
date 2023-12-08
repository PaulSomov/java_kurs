package ru.vlsu.storage_kurs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vlsu.storage_kurs.entity.Batch;
import ru.vlsu.storage_kurs.entity.BatchItem;
import ru.vlsu.storage_kurs.entity.status.BATCH_STATUS;
import ru.vlsu.storage_kurs.repo.BatchItemRepository;
import ru.vlsu.storage_kurs.repo.BatchRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BatchService {

    private final BatchRepository batchRepository;
    private final BatchItemRepository batchItemRepository;

    private final InventoryItemService inventoryItemService;

    @Autowired
    public BatchService(BatchRepository batchRepository, BatchItemRepository batchItemRepository, InventoryItemService inventoryItemServicel) {
        this.batchRepository = batchRepository;
        this.batchItemRepository = batchItemRepository;
        this.inventoryItemService = inventoryItemServicel;
    }

    public void addItems(Batch batch){
        batch.getBatchItems().stream()
                .forEach(batchItem -> {
                   inventoryItemService.saveInventoryItem( batchItem);
                });
    }
    public Batch addBatchItem(Long batchId, BatchItem newBatchItem) {
        Batch batch = getBatchById(batchId).orElse(null);
        BatchItem batchItemToSave = new BatchItem();
        batchItemToSave.setProduct(newBatchItem.getProduct());
        batchItemToSave.setSerialNumber(newBatchItem.getSerialNumber());
        if (batch != null) {
            batchItemToSave.setBatch(batch);
            batchItemRepository.saveAndFlush(batchItemToSave);
        }
        batch = getBatchById(batchId).orElse(null);
        return batch;
    }

    public void changeBatchStatus(Long batchId, String newStatus) {
        Batch batch = batchRepository.findById(batchId).orElse(null);
        if (batch != null) {

            batch.setBatchStatus(BATCH_STATUS.valueOf(newStatus));
            batchRepository.save(batch);
        }
    }


    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    public Optional<Batch> getBatchById(Long id) {
        return batchRepository.findById(id);
    }

    public Batch saveBatch(Batch batch) {
        return batchRepository.save(batch);
    }

    public void deleteBatch(Long id) {
        batchRepository.deleteById(id);
    }
}
