package com.epam.lab1.service.impl;

import com.epam.lab1.model.ArmorBodyPlace;
import com.epam.lab1.repository.BodyPlaceRepository;
import com.epam.lab1.service.BodyPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyPlaceServiceImpl implements BodyPlaceService {


    private BodyPlaceRepository bodyPlaceRepository;

    public BodyPlaceServiceImpl(BodyPlaceRepository bodyPlaceRepository) {
        this.bodyPlaceRepository = bodyPlaceRepository;
    }

    @Override
    public void save(ArmorBodyPlace armorBodyPlace) {
        bodyPlaceRepository.save(armorBodyPlace);
    }

    @Override
    public ArmorBodyPlace getByPid(Long pid) {
        return bodyPlaceRepository.getById(pid);
    }

    @Override
    public List<ArmorBodyPlace> getAll() {
        return bodyPlaceRepository.findAll();
    }
}
