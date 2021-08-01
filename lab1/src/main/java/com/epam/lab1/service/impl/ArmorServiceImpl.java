package com.epam.lab1.service.impl;

import com.epam.lab1.model.Armor;
import com.epam.lab1.model.Knight;
import com.epam.lab1.model.Weapon;
import com.epam.lab1.repository.ArmorRepository;
import com.epam.lab1.service.ArmorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArmorServiceImpl implements ArmorService {


    private ArmorRepository armorRepository;

    public ArmorServiceImpl(ArmorRepository armorRepository) {
        this.armorRepository = armorRepository;
    }

    @Override
    public void save(Armor armor) {
        armorRepository.save(armor);
    }

    public List<Armor> getAll() {
        return armorRepository.findAll();
    }

    public Armor getById(Long pid) {
        return armorRepository.getById(pid);
    }

    public List<Armor> getArmorBiKnightId(Knight knight) {
        return armorRepository.getArmorByKnight(knight);
    }

    public List<Armor> getArmorWithoutOwner() {
        return armorRepository.getArmorWithoutOwner();
    }

    public List<Armor> getSortedArmorListByWeightWithoutOwner() {
        return armorRepository.getSortedArmorListByWeightWithoutOwner();
    }

    public List<Armor> getSortedArmorListByWeightAndCostRangeWithoutOwner(Integer minPrice, Integer maxPrice) {
        return armorRepository.getSortedArmorListByWeightAndCostRangeWithoutOwner(minPrice, maxPrice);
    }

    public List<Armor> getSortedArmorListByCostRangeWithoutOwner(Integer minPrice, Integer maxPrice) {
        return armorRepository.getSortedArmorListByCostRangeWithoutOwner(minPrice, maxPrice);
    }


}
