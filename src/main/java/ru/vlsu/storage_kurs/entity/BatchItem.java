package ru.vlsu.storage_kurs.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BatchItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

}