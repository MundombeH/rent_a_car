package com.Bitech.rent_a_car.Repository.Pessoa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bitech.rent_a_car.Models.Pessoa.FuncionarioModels;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModels, UUID> {

}
