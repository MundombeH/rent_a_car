package com.Bitech.rent_a_car.Dtos.Pessoa;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

public class MotoristaRequestDTO extends PessoaDTO {

    @NotBlank(message = "A carta de condução é obrigatória.")
    private String cartaDeConducao;
    private Set<UUID> aluguer = new HashSet<>();
    

    // Getter e Setter
    public String getCartaDeConducao() {
        return cartaDeConducao;
    }

    public void setCartaDeConducao(String cartaDeConducao) {
        this.cartaDeConducao = cartaDeConducao;
    }

    public Set<UUID> getAluguer() {
        return aluguer;
    }

    public void setAluguer(Set<UUID> aluguer) {
        this.aluguer = aluguer;
    }

}