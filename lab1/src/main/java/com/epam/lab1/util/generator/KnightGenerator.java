package com.epam.lab1.util.generator;

import com.epam.lab1.model.Equipment;
import com.epam.lab1.model.Knight;
import com.epam.lab1.service.impl.KnightServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class KnightGenerator {

    private KnightServiceImpl knightService;

    public KnightGenerator(KnightServiceImpl knightService) {
        this.knightService = knightService;
    }

    public void generate(int count) {

        Random rnd = new Random();

        List<Knight> knightList = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            Knight knight = new Knight();

            knight.setBalance(rnd.nextInt(40000) + 10000);

            knight.setAge(rnd.nextInt(40) + 20);

            knight.setName("Knight" + i);

            knightList.add(knight);
        }

        knightService.saveAll(knightList);
    }




}
