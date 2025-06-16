package com.Bitech.rent_a_car.Models.Aluguer;

public enum CategoriaEnum {

    PADRAO("padrão"),
    LIGEIRO("Ligeiro"),
    PESADO("Pesado");

    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    CategoriaEnum(String categoria) {
        this.categoria = categoria;

    }

}