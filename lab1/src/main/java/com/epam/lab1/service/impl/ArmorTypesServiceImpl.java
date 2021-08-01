package com.epam.lab1.service.impl;

import com.epam.lab1.model.ArmorType;
import com.epam.lab1.repository.ArmorTypeRepository;
import com.epam.lab1.service.ArmorTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArmorTypesServiceImpl implements ArmorTypesService {

    @Autowired
    private ArmorTypeRepository armorTypeRepository;

    @Override
    public void save(ArmorType armorType) {
        armorTypeRepository.save(armorType);
    }

    @Override
    public ArmorType getByPid(Long pid) {
        return armorTypeRepository.getById(pid);
    }
}
