package com.epam.lab1.repository;

import com.epam.lab1.model.Armor;
import com.epam.lab1.model.Knight;
import com.epam.lab1.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Long> {

    @Query("select w from Weapon w where w.knight =:knight")
    List<Weapon> getWeaponByKnight(Knight knight);

    @Query("select w from Weapon w where w.knight is null")
    List<Weapon> getWeaponWithoutOwner();

    @Query("select w from Weapon w where w.knight is null order by w.weight asc")
    List<Weapon> getSortedWeaponListByWeightWithoutOwner();

    @Query("select w from Weapon w where w.knight is null and w.cost between :minPrice and :maxPrice order by w.weight asc")
    List<Weapon> getSortedWeaponListByWeightAndCostRangeWithoutOwner(Integer minPrice, Integer maxPrice);

    @Query("select w from Weapon w where w.knight is null and w.cost between :minPrice and :maxPrice order by w.cost asc")
    List<Weapon> getSortedWeaponListByCostRangeWithoutOwner(Integer minPrice, Integer maxPrice);


}
