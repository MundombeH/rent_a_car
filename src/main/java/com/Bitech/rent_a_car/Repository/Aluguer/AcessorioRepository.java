package com.Bitech.rent_a_car.Repository.Aluguer;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bitech.rent_a_car.Models.Aluguer.AcessorioModels;

public interface AcessorioRepository extends JpaRepository<AcessorioModels, UUID>{

}
