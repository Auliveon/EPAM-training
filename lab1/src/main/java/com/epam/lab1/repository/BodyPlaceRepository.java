package com.epam.lab1.repository;

import com.epam.lab1.model.ArmorBodyPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BodyPlaceRepository extends JpaRepository<ArmorBodyPlace, Long> {


}
