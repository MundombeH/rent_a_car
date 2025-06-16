package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CarroRequestDto {

    @NotBlank
    @NotNull
    private String matricula;

    @Positive
    @NotNull
    private Double numeroDoChassi;

    @NotNull
    @NotBlank
    private String cor;

    @NotNull
    @Positive
    private Double valorDaDiaria;

    private Set<UUID> aluguer = new HashSet<>();
    private Set<UUID> acessorios = new HashSet<>();
    private UUID modelo;


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

    public Set<UUID> getAluguer() {
        return aluguer;
    }

    public void setAluguer(Set<UUID> aluguer) {
        this.aluguer = aluguer;
    }

    public Set<UUID> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(Set<UUID> acessorios) {
        this.acessorios = acessorios;
    }

    public UUID getModelo() {
        return modelo;
    }

    public void setModelo(UUID modelo) {
        this.modelo = modelo;
    }

}
