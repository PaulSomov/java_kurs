package ru.vlsu.storage_kurs.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.vlsu.storage_kurs.entity.status.SHIPMENT_STATUS;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private SHIPMENT_STATUS status;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime shipmentTime;

    @OneToMany(mappedBy = "shipment")
    List<InventoryItem> inventoryItems;
}
