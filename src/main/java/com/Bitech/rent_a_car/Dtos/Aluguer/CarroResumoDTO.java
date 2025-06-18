package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.util.UUID;

public class CarroResumoDTO {

    private UUID id;
    private String matricula;
    private double numeroDoChassi;
    private double valorDaDiaria;
    private String cor;
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
    public double getNumeroDoChassi() {
        return numeroDoChassi;
    }
    public void setNumeroDoChassi(double numeroDoChassi) {
        this.numeroDoChassi = numeroDoChassi;
    }
    public double getValorDaDiaria() {
        return valorDaDiaria;
    }
    public void setValorDaDiaria(double valorDaDiaria) {
        this.valorDaDiaria = valorDaDiaria;
    }
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
}
