package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.util.UUID;

import com.Bitech.rent_a_car.Models.Aluguer.AluguerModels;
import com.Bitech.rent_a_car.Models.Pessoa.MotoristaModels;


public class ApoliceDeSeguroResponseDTO {
    private UUID id;

    private Double valorDaFranquia;
    private Boolean proteccaoTerceiros, causasNaturais;

    private MotoristaModels motorista;
    private AluguerModels aluguer;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getValorDaFranquia() {
        return valorDaFranquia;
    }

    public void setValorDaFranquia(Double valorDaFranquia) {
        this.valorDaFranquia = valorDaFranquia;
    }

    public Boolean getProteccaoTerceiros() {
        return proteccaoTerceiros;
    }

    public void setProteccaoTerceiros(Boolean proteccaoTerceiros) {
        this.proteccaoTerceiros = proteccaoTerceiros;
    }

    public Boolean getCausasNaturais() {
        return causasNaturais;
    }

    public void setCausasNaturais(Boolean causasNaturais) {
        this.causasNaturais = causasNaturais;
    }

    public MotoristaModels getMotorista() {
        return motorista;
    }

    public void setMotorista(MotoristaModels motorista) {
        this.motorista = motorista;
    }

    public AluguerModels getAluguer() {
        return aluguer;
    }

    public void setAluguer(AluguerModels aluguer) {
        this.aluguer = aluguer;
    }

}
