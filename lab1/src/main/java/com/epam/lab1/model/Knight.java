package com.epam.lab1.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Knight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private Integer balance;

    @OneToMany(mappedBy = "knight", fetch = FetchType.LAZY)
    private List<Weapon> equippedWeapons;

    @OneToMany(mappedBy = "knight", fetch = FetchType.LAZY)
    private List<Armor> equippedArmors = new ArrayList<>();

    public Knight() {
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Weapon> getEquippedWeapons() {
        return equippedWeapons;
    }

    public void setEquippedWeapons(List<Weapon> equippedWeapons) {
        this.equippedWeapons = equippedWeapons;
    }

    public List<Armor> getEquippedArmors() {
        return equippedArmors;
    }

    public void setEquippedArmors(List<Armor> equippedArmors) {
        this.equippedArmors = equippedArmors;
    }
}
