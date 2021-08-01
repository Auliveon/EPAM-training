package com.epam.lab1.repository;

import com.epam.lab1.model.Armor;
import com.epam.lab1.model.Knight;
import com.epam.lab1.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmorRepository extends JpaRepository<Armor, Long> {
    @Query("select a from Armor a where a.knight =:knight")
    List<Armor> getArmorByKnight(Knight knight);

    @Query("select a from Armor a where a.knight is null")
    List<Armor> getArmorWithoutOwner();

    @Query("select a from Armor a where a.knight is null order by a.weight asc")
    List<Armor> getSortedArmorListByWeightWithoutOwner();

    @Query("select a from Armor a where a.knight is null and a.cost between :minPrice and :maxPrice order by a.weight asc")
    List<Armor> getSortedArmorListByWeightAndCostRangeWithoutOwner(Integer minPrice, Integer maxPrice);

    @Query("select a from Armor a where a.knight is null and a.cost between :minPrice and :maxPrice order by a.cost asc")
    List<Armor> getSortedArmorListByCostRangeWithoutOwner(Integer minPrice, Integer maxPrice);
}
