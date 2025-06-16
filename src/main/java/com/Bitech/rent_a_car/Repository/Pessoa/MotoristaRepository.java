package com.Bitech.rent_a_car.Repository.Pessoa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bitech.rent_a_car.Models.Pessoa.MotoristaModels;

public interface MotoristaRepository  extends JpaRepository<MotoristaModels, UUID> {

    

    
}
