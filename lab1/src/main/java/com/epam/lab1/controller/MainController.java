package com.epam.lab1.controller;

import com.epam.lab1.model.Armor;
import com.epam.lab1.model.Equipment;
import com.epam.lab1.model.Knight;
import com.epam.lab1.model.Weapon;
import com.epam.lab1.service.impl.*;
import com.epam.lab1.util.EquipmentControl;
import com.epam.lab1.util.calculators.StatsCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ArmorServiceImpl armorService;

    @Autowired
    private WeaponServiceImpl weaponService;

    @Autowired
    private WeaponTypeServiceImpl weaponTypeService;

    @Autowired
    private ArmorTypesServiceImpl armorTypesService;

    @Autowired
    private EquipmentServiceImpl equipmentService;

    @Autowired
    private KnightServiceImpl knightService;

    @Autowired
    private StatsCalculator statsCalculator;

    @Autowired
    private EquipmentControl equipmentControl;

    @GetMapping
    public String main(Model model) throws IOException {

        model.addAttribute("knightlist", knightService.getAll());

        model.addAttribute("weaponlist", weaponService.getWeaponWithoutOwner());

        return "index";
    }

    @RequestMapping("/getknighttable")
    public String getKnightTable(Model model) throws IOException {

        model.addAttribute("knightlist", knightService.getAll());

        return "tables/knightTable :: knight_list";
    }

    @RequestMapping("/getallowneditemsbyknight")
    public String getAllOwnedItemsByKnight(@RequestParam(name = "pid") Long pid, Model model) throws IOException {

        List<Equipment> equipmentList = new ArrayList<>();

        equipmentList.addAll(armorService.getArmorBiKnightId(knightService.getByPid(pid)));
        equipmentList.addAll(weaponService.getWeaponByKnight(knightService.getByPid(pid)));
        model.addAttribute("equipmentlist", equipmentList);
        int[] stats = statsCalculator.calculateStats(equipmentList);

        int damage = stats[0];
        int defense = stats[1];
        int weight = stats[2];
        int equipCost = stats[3];
        int balance = knightService.getByPid(pid).getBalance();

        model.addAttribute("knightName", knightService.getByPid(pid).getName());
        model.addAttribute("damage", damage);
        model.addAttribute("defense", defense);
        model.addAttribute("weight", weight);
        model.addAttribute("balance", balance);
        model.addAttribute("equipCost", equipCost);
        return "forms/inventoryTable :: inventory_form";
    }

    @RequestMapping("/removeowner")
    public String removeOwner(@RequestParam(name = "pid") Long pid,
                              @RequestParam(name = "class") String className,
                              @RequestParam(name = "knightPid") Long knightPid,
                              Model model) throws IOException {

        Knight knight = equipmentControl.unEquip(pid, className);

        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.addAll(armorService.getArmorBiKnightId(knight));
        equipmentList.addAll(weaponService.getWeaponByKnight(knight));
        model.addAttribute("equipmentlist", equipmentList);
        int[] stats = statsCalculator.calculateStats(equipmentList);

        int damage = stats[0];
        int defense = stats[1];
        int weight = stats[2];
        int equipCost = stats[3];
        int balance = knightService.getByPid(knightPid).getBalance();

        model.addAttribute("knightName", knightService.getByPid(knightPid).getName());
        model.addAttribute("damage", damage);
        model.addAttribute("defense", defense);
        model.addAttribute("weight", weight);
        model.addAttribute("balance", balance);
        model.addAttribute("equipCost", equipCost);

        return "forms/inventoryTable :: inventory_form";
    }

    @RequestMapping("/getweapontable")
    public String getAllWeapon(@RequestParam(name = "sortbyweight") boolean isSortByWeight,
                               @RequestParam(name = "sortbycost") boolean isSortByCost,
                               @RequestParam(name = "minprice") Integer minPrice,
                               @RequestParam(name = "maxprice") Integer maxPrice,
                               @RequestParam(name = "knightid") Long knightId,
                               Model model) throws IOException {

        if (knightId > -1) {
            if (isSortByWeight & !isSortByCost) {
                model.addAttribute("weaponlist", equipmentControl.getAvailableWeapons(weaponService.getSortedWeaponListByWeightWithoutOwner(), knightId));
                return "tables/weaponTable :: weapon_list";
            } else if (isSortByWeight & isSortByCost) {
                model.addAttribute("weaponlist", equipmentControl.getAvailableWeapons(weaponService.getSortedWeaponListByWeightAndCostRangeWithoutOwner(minPrice, maxPrice), knightId));
                return "tables/weaponTable :: weapon_list";
            } else if (!isSortByWeight & isSortByCost) {
                model.addAttribute("weaponlist", equipmentControl.getAvailableWeapons(weaponService.getSortedWeaponListByCostRangeWithoutOwner(minPrice, maxPrice), knightId));
                return "tables/weaponTable :: weapon_list";
            } else {
                model.addAttribute("weaponlist", equipmentControl.getAvailableWeapons(weaponService.getWeaponWithoutOwner(), knightId));
                return "tables/weaponTable :: weapon_list";
            }

        } else {
            model.addAttribute("weaponlist", weaponService.getWeaponWithoutOwner());
            return "tables/weaponTable :: weapon_list";
        }


    }

    @RequestMapping("/getarmortable")
    public String getAllArmor(@RequestParam(name = "sortbyweight") boolean isSortByWeight,
                              @RequestParam(name = "sortbycost") boolean isSortByCost,
                              @RequestParam(name = "minprice") int minPrice,
                              @RequestParam(name = "maxprice") int maxPrice,
                              @RequestParam(name = "knightid") Long knightId,
                              Model model) throws IOException {

        if (knightId > -1) {


            if (isSortByWeight & !isSortByCost) {
                model.addAttribute("armorlist",  equipmentControl.getAvailableArmors(armorService.getSortedArmorListByWeightWithoutOwner(), knightId));
                return "tables/armorTable :: armor_list";
            } else if (isSortByWeight && isSortByCost) {
                model.addAttribute("armorlist",  equipmentControl.getAvailableArmors(armorService.getSortedArmorListByWeightAndCostRangeWithoutOwner(minPrice, maxPrice), knightId));
                return "tables/armorTable :: armor_list";
            } else if (!isSortByWeight && isSortByCost) {
                model.addAttribute("armorlist", equipmentControl.getAvailableArmors(armorService.getSortedArmorListByCostRangeWithoutOwner(minPrice, maxPrice), knightId));
                return "tables/armorTable :: armor_list";
            } else {
                model.addAttribute("armorlist", equipmentControl.getAvailableArmors(armorService.getArmorWithoutOwner(), knightId));
                System.out.println(11111);
                return "tables/armorTable :: armor_list";
            }
        } else {
            model.addAttribute("armorlist", armorService.getArmorWithoutOwner());
            return "tables/armorTable :: armor_list";
        }
    }

    @RequestMapping("/addweaponowner")
    public String addWeaponOwner(@RequestParam(name = "pid") Long weaponPid, @RequestParam(name = "knightpid") Long knightPid,
                                 Model model) throws IOException {

        Weapon weapon = weaponService.getById(weaponPid);
        equipmentControl.equip(weapon, knightPid);

        List<Equipment> equipmentList = new ArrayList<>();

        equipmentList.addAll(armorService.getArmorBiKnightId(knightService.getByPid(knightPid)));
        equipmentList.addAll(weaponService.getWeaponByKnight(knightService.getByPid(knightPid)));
        model.addAttribute("equipmentlist", equipmentList);
        int[] stats = statsCalculator.calculateStats(equipmentList);
        for (Equipment m: equipmentList) {
            System.out.println(m.getClass().getSuperclass());
        }
        int damage = stats[0];
        int defense = stats[1];
        int weight = stats[2];
        int equipCost = stats[3];
        int balance = knightService.getByPid(knightPid).getBalance();

        model.addAttribute("knightName", knightService.getByPid(knightPid).getName());
        model.addAttribute("damage", damage);
        model.addAttribute("defense", defense);
        model.addAttribute("weight", weight);
        model.addAttribute("balance", balance);
        model.addAttribute("equipCost", equipCost);
        return "forms/inventoryTable :: inventory_form";
    }

    @RequestMapping("/addarmorowner")
    public String addArmorOwner(@RequestParam(name = "pid") Long armorPid, @RequestParam(name = "knightpid") Long knightPid,
                                Model model) throws IOException {

        Armor armor = armorService.getById(armorPid);
        equipmentControl.equip(armor, knightPid);

        List<Equipment> equipmentList = new ArrayList<>();

        equipmentList.addAll(armorService.getArmorBiKnightId(knightService.getByPid(knightPid)));
        equipmentList.addAll(weaponService.getWeaponByKnight(knightService.getByPid(knightPid)));
        model.addAttribute("equipmentlist", equipmentList);

        int[] stats = statsCalculator.calculateStats(equipmentList);

        for (Equipment m: equipmentList) {
            System.out.println(m.getClass().getCanonicalName());
        }

        int damage = stats[0];
        int defense = stats[1];
        int weight = stats[2];
        int equipCost = stats[3];
        int balance = knightService.getByPid(knightPid).getBalance();

        model.addAttribute("knightName", knightService.getByPid(knightPid).getName());
        model.addAttribute("damage", damage);
        model.addAttribute("defense", defense);
        model.addAttribute("weight", weight);
        model.addAttribute("balance", balance);
        model.addAttribute("equipCost", equipCost);
        return "forms/inventoryTable :: inventory_form";
    }
}
