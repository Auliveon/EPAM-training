package com.epam.lab1.service;

import com.epam.lab1.model.Knight;

public interface KnightService {

    void save(Knight knight);

    Knight getByPid(Long pid);
}
