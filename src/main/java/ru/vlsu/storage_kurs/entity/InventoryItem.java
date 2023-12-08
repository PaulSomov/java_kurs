package ru.vlsu.storage_kurs.entity;

import lombok.*;
import ru.vlsu.storage_kurs.entity.status.INVENTORY_ITEM_STATUS;

import javax.persistence.*;

@Entity
@Data
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String serialNumber;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;
    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @Enumerated(EnumType.STRING)
    private INVENTORY_ITEM_STATUS status;
}