package com.epam.lab1.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name =  "weapon_type")
public class WeaponType extends EquipmentType {

    @OneToMany(mappedBy = "weaponType", fetch = FetchType.LAZY)
    private List<Weapon> weapons;

    public WeaponType() {
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }
}
