package com.Bitech.rent_a_car.Models.Aluguer;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "APOLICE_DE_SEGURO")
public class ApoliceDeSeguroModels implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Double valorDaFranquia;
    private Boolean proteccaoTerceiros, causasNaturais;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(mappedBy = "apolice")
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

    public AluguerModels getAluguer() {
        return aluguer;
    }

    public void setAluguer(AluguerModels aluguer) {
        this.aluguer = aluguer;
    }

}