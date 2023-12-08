package ru.vlsu.storage_kurs.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    private String description;

    private int checkInterval;

    private String accuracyClass;

    private int phases;

}