package com.epam.lab1.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "armor_type")
public class ArmorType extends EquipmentType {

    @OneToMany(mappedBy = "armorType", fetch = FetchType.LAZY)
    private List<Armor> armors;

    public ArmorType() {
    }

    public List<Armor> getArmors() {
        return armors;
    }

    public void setArmors(List<Armor> armors) {
        this.armors = armors;
    }

}
