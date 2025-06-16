package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.Bitech.rent_a_car.Models.Aluguer.CategoriaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ModeloRequestDTO {

    private UUID id;

    @NotBlank
    @NotNull
    private String descricao;

    // @NotNull
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;
    private Set<UUID> carro = new HashSet<>();
    private UUID fabricante;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public Set<UUID> getCarro() {
        return carro;
    }

    public void setCarro(Set<UUID> carro) {
        this.carro = carro;
    }

    public UUID getFabricante() {
        return fabricante;
    }

    public void setFabricante(UUID fabricante) {
        this.fabricante = fabricante;
    }


}
