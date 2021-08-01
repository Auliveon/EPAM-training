package com.epam.lab1.util.generator;

import com.epam.lab1.model.Armor;
import com.epam.lab1.model.Equipment;
import com.epam.lab1.model.Weapon;
import com.epam.lab1.service.impl.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EquipmentGenerator {

    private WeaponTypeServiceImpl weaponTypeService;

    private WeaponServiceImpl weaponService;

    private ArmorTypesServiceImpl armorTypesService;

    private ArmorServiceImpl armorService;

    private EquipmentServiceImpl equipmentService;

    private BodyPlaceServiceImpl bodyPlaceService;

    public EquipmentGenerator(WeaponTypeServiceImpl weaponTypeService,
                              WeaponServiceImpl weaponService,
                              ArmorTypesServiceImpl armorTypesService,
                              ArmorServiceImpl armorService,
                              EquipmentServiceImpl equipmentService,
                              BodyPlaceServiceImpl bodyPlaceService) {

        this.weaponTypeService = weaponTypeService;
        this.weaponService = weaponService;
        this.armorTypesService = armorTypesService;
        this.armorService = armorService;
        this.equipmentService = equipmentService;
        this.bodyPlaceService = bodyPlaceService;
    }

    public void generate(int count) {

        int weaponCount = 0;

        int armorCount = 0;

        Random rnd = new Random();

        List<Equipment> equipmentList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int type = rnd.nextInt(2);
            if (type == 0) {
                Weapon weapon = new Weapon();
                weapon.setDamage(rnd.nextInt(991) + 10);
                weapon.setCost(rnd.nextInt(10000));
                weapon.setWeight(rnd.nextInt(9) + 2);
                weapon.setType(weaponTypeService.getByPid((long) rnd.nextInt(2) + 1));
                weapon.setName("Оружие" + weaponCount);
                equipmentList.add(weapon);
                weaponCount++;
            } else {
                Armor armor = new Armor();
                armor.setDefense(rnd.nextInt(1991) + 10);
                armor.setCost(rnd.nextInt(10000));
                armor.setWeight(rnd.nextInt(13) + 2);
                armor.setType(armorTypesService.getByPid((long) rnd.nextInt(2) + 1));
                armor.setArmorBodyPlace(bodyPlaceService.getByPid((long) rnd.nextInt(4) + 1));
                armor.setName("Броня" + armorCount);
                equipmentList.add(armor);
                armorCount++;
            }
        }

        equipmentService.saveAll(equipmentList);
    }
}
