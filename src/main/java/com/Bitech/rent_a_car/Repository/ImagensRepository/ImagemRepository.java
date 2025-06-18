package com.Bitech.rent_a_car.Repository.ImagensRepository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bitech.rent_a_car.Models.Aluguer.CarroModels;
import com.Bitech.rent_a_car.Models.ImagensModel.ImagemModels;

import java.util.List;


public interface ImagemRepository extends JpaRepository<ImagemModels, UUID>{

    List<ImagemModels> findByCarro(CarroModels carro);


}
