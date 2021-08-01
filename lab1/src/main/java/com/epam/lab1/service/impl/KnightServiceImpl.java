package com.epam.lab1.service.impl;

import com.epam.lab1.model.Knight;
import com.epam.lab1.repository.KnightRepository;
import com.epam.lab1.service.KnightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnightServiceImpl implements KnightService {

    private KnightRepository knightRepository;

    public KnightServiceImpl(KnightRepository knightRepository) {
        this.knightRepository = knightRepository;
    }

    @Override
    public void save(Knight knight) {
        knightRepository.save(knight);
    }

    @Override
    public Knight getByPid(Long pid) {
        return knightRepository.getById(pid);
    }

    public void saveAll(List<Knight> knights) {
        knightRepository.saveAll(knights);
    }

    public List<Knight> getAll() {
        return knightRepository.findAll();
    }
}
