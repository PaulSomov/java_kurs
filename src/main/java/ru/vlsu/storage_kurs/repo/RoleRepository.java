package ru.vlsu.storage_kurs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlsu.storage_kurs.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}