package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.Bitech.rent_a_car.Models.Aluguer.AcessorioModels;
import com.Bitech.rent_a_car.Models.Aluguer.AluguerModels;
import com.Bitech.rent_a_car.Models.Aluguer.ModeloModels;
import com.Bitech.rent_a_car.Models.ImagensModel.ImagemModels;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CarroResponseDTO {

    private UUID id;

    // @NotBlank
    // @NotNull
    private Double numeroDoChassi;
    private String matricula;
    private ModeloModels modelo;

    // @Positive
    // @NotNull

    // @NotNull
    // @NotBlank
    private String cor;

    @NotNull
    @Positive
    private Double valorDaDiaria;

    private Set<AluguerModels> aluguer = new HashSet<>();
    private Set<AcessorioModels> acessorios = new HashSet<>();
    private Set<ImagemModels> imagem = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Double getNumeroDoChassi() {
        return numeroDoChassi;
    }

    public void setNumeroDoChassi(Double numeroDoChassi) {
        this.numeroDoChassi = numeroDoChassi;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getValorDaDiaria() {
        return valorDaDiaria;
    }

    public void setValorDaDiaria(Double valorDaDiaria) {
        this.valorDaDiaria = valorDaDiaria;
    }

    public Set<AluguerModels> getAluguer() {
        return aluguer;
    }

    public void setAluguer(Set<AluguerModels> aluguer) {
        this.aluguer = aluguer;
    }

    public Set<AcessorioModels> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(Set<AcessorioModels> acessorios) {
        this.acessorios = acessorios;
    }

       public ModeloModels getModelo() {
        return modelo;
    }

    public void setModelo(ModeloModels modelo) {
        this.modelo = modelo;
    }

    public Set<ImagemModels> getImagem() {
        return imagem;
    }

    public void setImagem(Set<ImagemModels> imagem) {
        this.imagem = imagem;
    }
}
