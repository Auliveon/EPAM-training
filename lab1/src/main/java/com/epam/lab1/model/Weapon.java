package com.epam.lab1.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Weapon extends Equipment {

    @Column(nullable = false)
    private Integer damage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "pid")
    private WeaponType weaponType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "knight", referencedColumnName = "pid")
    private Knight knight;

    public Weapon() {
    }

    public Weapon(Integer damage, WeaponType weaponType, Knight knight) {
        this.damage = damage;
        this.weaponType = weaponType;
        this.knight = knight;
    }

    public Weapon(String name, Integer cost, Integer weight, Integer damage, WeaponType weaponType, Knight knight) {
        super(name, cost, weight);
        this.damage = damage;
        this.weaponType = weaponType;
        this.knight = knight;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public WeaponType getType() {
        return weaponType;
    }

    public void setType(WeaponType type) {
        this.weaponType = type;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public Knight getKnight() {
        return knight;
    }

    public void setKnight(Knight knight) {
        this.knight = knight;
    }
}
