package com.epam.lab1.service;

import com.epam.lab1.model.Equipment;

import java.util.List;

public interface EquipmentService {

    void save(Equipment equipment);

    void saveAll(List<Equipment> equipment);

}
