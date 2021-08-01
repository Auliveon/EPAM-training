package com.epam.lab1.repository;

import com.epam.lab1.model.Knight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnightRepository extends JpaRepository<Knight, Long> {
}
