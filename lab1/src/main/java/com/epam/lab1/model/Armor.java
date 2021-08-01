package com.epam.lab1.model;

import javax.persistence.*;
@Entity
public class Armor extends Equipment {

    @Column(nullable = false)
    private Integer defense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "pid")
    private ArmorType armorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", referencedColumnName = "pid")
    private ArmorBodyPlace armorBodyPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "knight", referencedColumnName = "pid")
    private Knight knight;

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    public ArmorBodyPlace getArmorBodyPlace() {
        return armorBodyPlace;
    }

    public void setArmorBodyPlace(ArmorBodyPlace armorBodyPlace) {
        this.armorBodyPlace = armorBodyPlace;
    }

    public Knight getKnight() {
        return knight;
    }

    public void setKnight(Knight knight) {
        this.knight = knight;
    }

    public Armor() {

    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public ArmorType getType() {
        return armorType;
    }

    public void setType(ArmorType type) {
        this.armorType = type;
    }


}
