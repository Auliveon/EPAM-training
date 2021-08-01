package com.epam.lab1.model;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class EquipmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(nullable = false)
    private String equipmentType;

    @Column
    private String description;

    public EquipmentType() {
    }

    public EquipmentType(Long pid, String equipmentType, String description) {
        this.pid = pid;
        this.equipmentType = equipmentType;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentType that = (EquipmentType) o;
        return pid.equals(that.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }
}
