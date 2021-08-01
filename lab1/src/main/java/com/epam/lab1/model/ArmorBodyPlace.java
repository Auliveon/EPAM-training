package com.epam.lab1.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class ArmorBodyPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column
    private String place;

    @OneToMany(mappedBy = "armorBodyPlace", fetch = FetchType.LAZY)
    private List<Armor> armors;

    public ArmorBodyPlace() {
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Armor> getArmors() {
        return armors;
    }

    public void setArmors(List<Armor> armors) {
        this.armors = armors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArmorBodyPlace armorBodyPlace = (ArmorBodyPlace) o;
        return pid.equals(armorBodyPlace.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }
}
