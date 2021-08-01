package com.epam.lab1.service;

import com.epam.lab1.model.Armor;
import com.epam.lab1.model.ArmorType;

public interface ArmorTypesService {

    void save(ArmorType armorType);

    ArmorType getByPid(Long pid);

}
