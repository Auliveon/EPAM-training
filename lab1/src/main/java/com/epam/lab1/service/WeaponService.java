package com.epam.lab1.service;

import com.epam.lab1.model.Weapon;

import java.util.List;

public interface WeaponService {

    void save(Weapon weapon);

    List<Weapon> getAll();

    Weapon getById(Long pid);
}
