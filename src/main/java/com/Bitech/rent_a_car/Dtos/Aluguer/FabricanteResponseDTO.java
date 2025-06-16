package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.Bitech.rent_a_car.Models.Aluguer.ModeloModels;

public class FabricanteResponseDTO {

    private UUID id;
    private String nome;
    private Set<ModeloModels> modelo = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<ModeloModels> getModelo() {
        return modelo;
    }

    public void setModelo(Set<ModeloModels> modelo) {
        this.modelo = modelo;
    }
}
