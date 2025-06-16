package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.time.LocalDate;
import java.util.UUID;

import com.Bitech.rent_a_car.Models.Aluguer.ApoliceDeSeguroModels;
import com.Bitech.rent_a_car.Models.Aluguer.CarroModels;
import com.Bitech.rent_a_car.Models.Pessoa.MotoristaModels;

public class AluguerResponseDTO {

    private UUID id;
    private LocalDate dataDoPedido;
    private LocalDate dataDeEntrega;
    private LocalDate dataDeDevolucao;
    private Double valorTotal;
    private CarroModels carro;

    private ApoliceDeSeguroModels apolice;

    private MotoristaModels motorista;
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public LocalDate getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public void setDataDeDevolucao(LocalDate dataDeDevolucao) {
        this.dataDeDevolucao = dataDeDevolucao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
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
