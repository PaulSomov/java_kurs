package ru.vlsu.storage_kurs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlsu.storage_kurs.entity.Batch;
import ru.vlsu.storage_kurs.entity.BatchItem;

public interface BatchItemRepository extends JpaRepository<BatchItem, Long>{
}
