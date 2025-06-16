package com.Bitech.rent_a_car.Models.Pessoa;

import java.util.HashSet;
import java.util.Set;

import com.Bitech.rent_a_car.Models.Aluguer.AluguerModels;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "MOTORISTA")
public class MotoristaModels extends PessoaModels {
    private static final long serialVersionUID = 1L;

    private String cartaDeConducao;

    @OneToMany(mappedBy = "motorista")
    // @JsonIgnore
    private Set<AluguerModels> aluguer = new HashSet<>();

    public String getCartaDeConducao() {
        return cartaDeConducao;
    }

    public void setCartaDeConducao(String cartaDeConducao) {
        this.cartaDeConducao = cartaDeConducao;
    }

    public Set<AluguerModels> getAluguer() {
        return aluguer;
    }

    public void setAluguer(Set<AluguerModels> apolice) {
        this.aluguer = apolice;
    }
}