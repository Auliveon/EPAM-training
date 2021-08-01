package com.epam.lab1.service.impl;

import com.epam.lab1.model.WeaponType;
import com.epam.lab1.repository.WeaponRepository;
import com.epam.lab1.repository.WeaponTypeRepository;
import com.epam.lab1.service.WeaponTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeaponTypeServiceImpl implements WeaponTypeService {

    @Autowired
    private WeaponTypeRepository weaponTypeRepository;

    @Override
    public void save(WeaponType weaponType) {
        weaponTypeRepository.save(weaponType);
    }

    @Override
    public WeaponType getByPid(Long pid) {
       return weaponTypeRepository.getById(pid);
    }
}
