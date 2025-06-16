package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.Bitech.rent_a_car.Models.Aluguer.CarroModels;
import com.Bitech.rent_a_car.Models.Aluguer.CategoriaEnum;
import com.Bitech.rent_a_car.Models.Aluguer.FabricanteModels;

public class ModeloResponseDTO {

    private UUID id;
    private String descricao;

    // @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    private Set<CarroModels> carro = new HashSet<>();

    private FabricanteModels fabricante;
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

    public Set<CarroModels> getCarro() {
        return carro;
    }

    public void setCarro(Set<CarroModels> carro) {
        this.carro = carro;
    }

    public FabricanteModels getFabricante() {
        return fabricante;
    }

    public void setFabricante(FabricanteModels fabricante) {
        this.fabricante = fabricante;
    }
}
