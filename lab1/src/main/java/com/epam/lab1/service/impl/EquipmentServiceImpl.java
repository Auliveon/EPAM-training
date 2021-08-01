package com.epam.lab1.service.impl;

import com.epam.lab1.model.Equipment;
import com.epam.lab1.repository.EquipmentRepository;
import com.epam.lab1.service.EquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private EquipmentRepository equipmentRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public void save(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    @Override
    public void saveAll(List<Equipment> equipment) {
        equipmentRepository.saveAll(equipment);
    }

    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }
}
