package com.epam.lab1.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass()
public abstract class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private Integer cost;

    @Column(nullable = false)
    private Integer weight;




    public Equipment() {
    }

    public Equipment(String name, Integer cost, Integer weight) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return pid.equals(equipment.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }
}
