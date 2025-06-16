package com.Bitech.rent_a_car.Repository.Aluguer;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bitech.rent_a_car.Models.Aluguer.AluguerModels;

public interface AluguerRepository extends JpaRepository<AluguerModels, UUID>{

}
