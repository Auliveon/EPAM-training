package com.epam.lab1;

import com.epam.lab1.util.generator.EquipmentGenerator;
import com.epam.lab1.util.generator.KnightGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@SpringBootApplication
public class Lab1Application implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private KnightGenerator knightGenerator;

    @Autowired
    private EquipmentGenerator equipmentGenerator;

    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    public void loadData() {

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false,
                false, "UTF-8", new ClassPathResource("sql/dataFile.sql"));
        resourceDatabasePopulator.execute(dataSource);

        equipmentGenerator.generate(100);

        knightGenerator.generate(100);
    }
}
