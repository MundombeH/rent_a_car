package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.util.UUID;
public class AcessorioRequestDTO {

    private UUID id;
    private String descricao;
    private UUID carro;


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
    public UUID getCarro() {
        return carro;
    }
    public void setCarro(UUID carro) {
        this.carro = carro;
    }

}
