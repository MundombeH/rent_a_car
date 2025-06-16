package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ApoliceDeSeguroRequestDTO {
    private UUID id;

    @Positive
    @NotNull
    private Double valorDaFranquia;

    @NotNull
    private Boolean proteccaoTerceiros, causasNaturais;

    private UUID motorista;
    private UUID aluguer;

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

    public UUID getMotorista() {
        return motorista;
    }

    public void setMotorista(UUID motorista) {
        this.motorista = motorista;
    }

    public UUID getAluguer() {
        return aluguer;
    }

    public void setAluguer(UUID aluguer) {
        this.aluguer = aluguer;
    }

}
