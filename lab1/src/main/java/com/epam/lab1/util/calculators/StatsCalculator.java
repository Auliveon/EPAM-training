package com.epam.lab1.util.calculators;

import com.epam.lab1.model.Armor;
import com.epam.lab1.model.Equipment;
import com.epam.lab1.model.Weapon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsCalculator {

    public int[] calculateStats(List<Equipment> equipmentList) {

        int defense = 0,
                damage = 0,
                weight = 0,
                equipCost = 0;

        for (Equipment elem : equipmentList) {
            if (elem instanceof Armor) {
                Armor armor = (Armor) elem;
                defense += armor.getDefense();


            } else {

                Weapon weapon = (Weapon) elem;
                damage += weapon.getDamage();

            }

            weight += elem.getWeight();
            equipCost += elem.getCost();
        }

        return new int[]{damage, defense, weight, equipCost};

    }
}
