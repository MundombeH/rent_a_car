package com.Bitech.rent_a_car.Models.Aluguer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import com.Bitech.rent_a_car.Models.Pessoa.MotoristaModels;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ALUGUER")
public class AluguerModels implements Serializable {
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate dataDoPedido;
    private LocalDate dataDeEntrega;
    private LocalDate dataDeDevolucao;
    private Double valorTotal;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "carro_id")
    private CarroModels carro;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @JoinColumn(name = "apolice_id")
    private ApoliceDeSeguroModels apolice;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private MotoristaModels motorista;

    public LocalDate getDataDoPedido() {
        return dataDoPedido;
    }

    public void setDataDoPedido(LocalDate dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    public LocalDate getDataDeEntrega() {
        return dataDeEntrega;
    }

    public void setDataDeEntrega(LocalDate dataDeEntrega) {
        this.dataDeEntrega = dataDeEntrega;
    }

    public LocalDate getDataDeDEvolicao() {
        return dataDeDevolucao;
    }

    public void setDataDeDEvolucao(LocalDate dataDeDevolucao) {
        this.dataDeDevolucao = dataDeDevolucao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public void setDataDeDevolucao(LocalDate dataDeDevolucao) {
        this.dataDeDevolucao = dataDeDevolucao;
    }

    public CarroModels getCarro() {
        return carro;
    }

    public void setCarro(CarroModels carro) {
        this.carro = carro;
    }

    public ApoliceDeSeguroModels getApolice() {
        return apolice;
    }

    public void setApolice(ApoliceDeSeguroModels apolice) {
        this.apolice = apolice;
    }

    public MotoristaModels getMotorista() {
        return motorista;
    }

    public void setMotorista(MotoristaModels motorista) {
        this.motorista = motorista;
    }

}