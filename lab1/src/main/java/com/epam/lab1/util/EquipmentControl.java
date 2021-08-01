package com.epam.lab1.util;

import com.epam.lab1.model.Armor;
import com.epam.lab1.model.Equipment;
import com.epam.lab1.model.Knight;
import com.epam.lab1.model.Weapon;
import com.epam.lab1.service.impl.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipmentControl {

    private final ArmorServiceImpl armorService;

    private final WeaponServiceImpl weaponService;

    private final KnightServiceImpl knightService;

    public EquipmentControl(ArmorServiceImpl armorService,
                            WeaponServiceImpl weaponService,
                            KnightServiceImpl knightService) {

        this.armorService = armorService;
        this.weaponService = weaponService;
        this.knightService = knightService;
    }

    public List<Weapon> getAvailableWeapons(List<Weapon> weapons, Long knightPid) {

        Knight knight = knightService.getByPid(knightPid);

        List<Weapon> ownedWeapons = weaponService.getWeaponByKnight(knight);

        int oneHandCount = 0;

        if (weapons != null && weapons.size() > 0) {
            for (int i = 0; i < ownedWeapons.size(); i++) {
                if (ownedWeapons.get(i).getType().getEquipmentType().equals("ONE_HAND")) {
                    oneHandCount++;
                }
                if (ownedWeapons.get(i).getType().getEquipmentType().equals("TWO_HAND") || oneHandCount > 1) {
                    weapons.clear();
                    return weapons;
                }

                for (int n = 0; n < weapons.size(); n++) {

                    if (oneHandCount == 1) {
                        if (weapons.get(n).getType().getEquipmentType().equals("TWO_HAND")) {
                            weapons.remove(n);
                            n--;
                            continue;
                        }
                    }
                }
            }

            for (int i = 0; i < weapons.size(); i++) {
                if (weapons.get(i).getCost() > knight.getBalance()) {
                    weapons.remove(i);
                    i--;
                }
            }
        }

        return weapons;

    }

    public List<Armor> getAvailableArmors(List<Armor> armors, Long knightPid) {

        Knight knight = knightService.getByPid(knightPid);

        System.out.println(knight.getBalance());

        List<Armor> ownedArmors = armorService.getArmorBiKnightId(knight);

        if (armors != null && armors.size() > 0) {
            for (int i = 0; i < ownedArmors.size(); i++) {
                for (int n = 0; n < armors.size(); n++) {
                    //System.out.println(22222);
                    if (armors.get(n).getArmorBodyPlace().getPlace().equals(ownedArmors.get(i).getArmorBodyPlace().getPlace())) {
                        armors.remove(n);
                        n--;
                        continue;
                    }

                }
            }

            for (int i = 0; i < armors.size(); i++) {
                if (armors.get(i).getCost() > knight.getBalance()) {
                    armors.remove(i);
                    i--;
                }
            }

        }

        return armors;
    }

    public void equip(Equipment equipment, Long knightPid) {

        Knight knight = knightService.getByPid(knightPid);

        if (equipment instanceof Armor) {
            Armor armor = (Armor) equipment;
            armor.setKnight(knight);
            knight.setBalance(knight.getBalance() - equipment.getCost());
            armorService.save(armor);

        } else if (equipment instanceof Weapon) {
            Weapon weapon = (Weapon) equipment;
            weapon.setKnight(knight);
            knight.setBalance(knight.getBalance() - equipment.getCost());
            weaponService.save(weapon);
        }

        knightService.save(knight);

    }

    public Knight unEquip(Long itemId, String className) {

        Knight knight = new Knight();

        if (className.equals("Armor")) {

            Armor armor = armorService.getById(itemId);
            knight = armor.getKnight();
            knight.setBalance(knight.getBalance() + armor.getCost());
            armor.setKnight(null);
            armorService.save(armor);

        } else if (className.equals("Weapon")) {

            Weapon weapon = weaponService.getById(itemId);
            knight = weapon.getKnight();
            knight.setBalance(knight.getBalance() + weapon.getCost());
            weapon.setKnight(null);
            weaponService.save(weapon);
        }

        return knight;
    }

}
