package com.Bitech.rent_a_car.Dtos.Pessoa;

import java.util.HashSet;
import java.util.Set;

import com.Bitech.rent_a_car.Models.Aluguer.AluguerModels;

public class MotoristaResponseDTO extends PessoaDTO {

    private String cartaDeConducao;
        private Set<AluguerModels> aluguer = new HashSet<>();

    // Getter e Setter
    public String getCartaDeConducao() {
        return cartaDeConducao;
    }

    public void setCartaDeConducao(String cartaDeConducao) {
        this.cartaDeConducao = cartaDeConducao;
    }

    public Set<AluguerModels> getAluguer() {
        return aluguer;
    }

    public void setAluguer(Set<AluguerModels> aluguer) {
        this.aluguer = aluguer;
    }
}