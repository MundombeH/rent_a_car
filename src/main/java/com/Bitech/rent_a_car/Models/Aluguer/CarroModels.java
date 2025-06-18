package com.Bitech.rent_a_car.Models.Aluguer;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.Bitech.rent_a_car.Models.ImagensModel.ImagemModels;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARRO")
public class CarroModels implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;
    private String matricula;
    private Double numeroDoChassi;
    private String cor;
    private Double valorDaDiaria;

    @OneToMany(mappedBy = "carro")
    @JsonIgnore
    private Set<AluguerModels> aluguer = new HashSet<>();

    @OneToMany(mappedBy = "carro")
    private Set<AcessorioModels> acessorios = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "Modelo_id")
    // private Set<ModeloModels> modelo = new HashSet<>();
    private ModeloModels modelo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "carro", fetch = FetchType.LAZY)
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Set<ImagemModels> getImagem() {
        return imagem;
    }

    public void setImagem(Set<ImagemModels> imagem) {
        this.imagem = imagem;
    }

    // public Set<ModeloModels> getModelo() {
    // return modelo;
    // }

    // public void setModelo(Set<ModeloModels> modelo) {
    // this.modelo = modelo;
    // }

}