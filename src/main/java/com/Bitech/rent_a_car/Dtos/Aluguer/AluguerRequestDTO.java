package com.Bitech.rent_a_car.Dtos.Aluguer;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class AluguerRequestDTO {
    private UUID id;

    // @PastOrPresent
    private LocalDate dataDoPedido;

    @FutureOrPresent
    private LocalDate dataDeEntrega;

    @FutureOrPresent
    private LocalDate dataDeDevolucao;

    // @Positive
    // @NotNull
    private Double valorTotal;

    @NotNull
    private UUID carro;

    @NotNull
    private UUID apolice;

    
    private UUID motorista;

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

    public UUID getCarro() {
        return carro;
    }

    public void setCarro(UUID carro) {
        this.carro = carro;
    }

    public UUID getApolice() {
        return apolice;
    }

    public void setApolice(UUID apolice) {
        this.apolice = apolice;
    }

    public UUID getMotorista() {
        return motorista;
    }

    public void setMotorista(UUID motorista) {
        this.motorista = motorista;
    }
}
