package ru.vlsu.storage_kurs.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.vlsu.storage_kurs.entity.status.BATCH_STATUS;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String info;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime arrivalDate;

    @Enumerated(EnumType.STRING)
    private BATCH_STATUS batchStatus;

    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    private List<BatchItem> batchItems;

}