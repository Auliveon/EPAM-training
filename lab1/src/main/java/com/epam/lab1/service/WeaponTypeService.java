package com.epam.lab1.service;

import com.epam.lab1.model.Weapon;
import com.epam.lab1.model.WeaponType;

public interface WeaponTypeService {

    void save(WeaponType weaponType);

    WeaponType getByPid(Long pid);
}
