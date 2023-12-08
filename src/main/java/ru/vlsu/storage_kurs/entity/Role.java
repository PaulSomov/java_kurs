package ru.vlsu.storage_kurs.entity;

import lombok.Data;


import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}