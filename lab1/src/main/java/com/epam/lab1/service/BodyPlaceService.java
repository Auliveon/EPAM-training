package com.epam.lab1.service;

import com.epam.lab1.model.ArmorBodyPlace;

import java.util.List;

public interface BodyPlaceService {

    void save(ArmorBodyPlace armorBodyPlace);

    ArmorBodyPlace getByPid(Long pid);

    List<ArmorBodyPlace> getAll();

}
