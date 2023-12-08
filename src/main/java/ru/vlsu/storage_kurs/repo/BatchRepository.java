package ru.vlsu.storage_kurs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlsu.storage_kurs.entity.Batch;
import ru.vlsu.storage_kurs.entity.BatchItem;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {
}