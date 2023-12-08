package ru.vlsu.storage_kurs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlsu.storage_kurs.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}