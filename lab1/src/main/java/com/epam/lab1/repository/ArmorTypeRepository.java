package com.epam.lab1.repository;

import com.epam.lab1.model.ArmorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmorTypeRepository extends JpaRepository<ArmorType, Long> {
}
