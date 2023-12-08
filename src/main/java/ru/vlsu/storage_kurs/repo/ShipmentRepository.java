package ru.vlsu.storage_kurs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlsu.storage_kurs.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
