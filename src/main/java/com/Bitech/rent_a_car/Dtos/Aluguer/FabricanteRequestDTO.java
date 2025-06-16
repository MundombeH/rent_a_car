package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FabricanteRequestDTO {
    private UUID id;

    @NotBlank
    @NotNull
    private String nome;

    private Set<UUID> modelo = new HashSet<>();

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

    public Set<UUID> getModelo() {
        return modelo;
    }

    public void setModelo(Set<UUID> modelo) {
        this.modelo = modelo;
    }
}
