package com.epam.lab1.repository;

import com.epam.lab1.model.WeaponType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponTypeRepository extends JpaRepository<WeaponType, Long> {
}
