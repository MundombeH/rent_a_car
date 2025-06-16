package com.Bitech.rent_a_car.Repository.Aluguer;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bitech.rent_a_car.Models.Aluguer.ModeloModels;

@Repository
public interface ModeloRepository extends JpaRepository<ModeloModels,UUID>{

}
