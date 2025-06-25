package com.Bitech.rent_a_car.Repository.Aluguer;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bitech.rent_a_car.Models.Aluguer.AcessorioModels;
import com.Bitech.rent_a_car.Models.Aluguer.CarroModels;

public interface AcessorioRepository extends JpaRepository<AcessorioModels, UUID>{

    List<AcessorioModels> findByCarro(CarroModels carros);

}
