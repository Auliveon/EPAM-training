package com.epam.lab1.service.impl;

import com.epam.lab1.model.Armor;
import com.epam.lab1.model.Knight;
import com.epam.lab1.model.Weapon;
import com.epam.lab1.repository.WeaponRepository;
import com.epam.lab1.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeaponServiceImpl implements WeaponService {

    private WeaponRepository weaponRepository;

    public WeaponServiceImpl(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @Override
    public void save(Weapon weapon) {
        weaponRepository.save(weapon);
    }

    @Override
    public List<Weapon> getAll() {
        return weaponRepository.findAll();
    }

    @Override
    public Weapon getById(Long pid) {
        return weaponRepository.getById(pid);
    }

    public List<Weapon> getWeaponByKnight(Knight knight) {
        return weaponRepository.getWeaponByKnight(knight);
    }

    public List<Weapon> getWeaponWithoutOwner() {
        return weaponRepository.getWeaponWithoutOwner();
    }

    public List<Weapon> getSortedWeaponListByWeightWithoutOwner() {
        return weaponRepository.getSortedWeaponListByWeightWithoutOwner();
    }

    public List<Weapon> getSortedWeaponListByWeightAndCostRangeWithoutOwner(Integer minPrice, Integer maxPrice){
        return weaponRepository.getSortedWeaponListByWeightAndCostRangeWithoutOwner(minPrice, maxPrice);
    }

    public List<Weapon> getSortedWeaponListByCostRangeWithoutOwner(Integer minPrice, Integer maxPrice) {
        return weaponRepository.getSortedWeaponListByCostRangeWithoutOwner(minPrice, maxPrice);
    }

}
