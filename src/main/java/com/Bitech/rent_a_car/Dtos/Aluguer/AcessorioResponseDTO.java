package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.util.UUID;

import com.Bitech.rent_a_car.Models.Aluguer.CarroModels;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AcessorioResponseDTO {

    private UUID id;

    @NotBlank
    @NotNull
    private String descricao;
    private CarroModels carro;

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
    public CarroModels getCarro() {
        return carro;
    }
    public void setCarro(CarroModels carro) {
        this.carro = carro;
    }
}
