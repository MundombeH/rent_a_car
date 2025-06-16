package com.Bitech.rent_a_car.Models.Pessoa;

public enum GeneroEnum {
    MASCULINO("Masculino"),
    NAO_BINARIO("Não binário"),
    FEMININO("Feminino");
    
    private String genero;
    
    GeneroEnum(String genero) {
        this.genero = genero;
        
    }
    public String getGenero() {
        return genero;

    }

}
