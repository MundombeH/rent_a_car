package com.Bitech.rent_a_car.Models.Pessoa;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "FUNCIONARIO")
public class FuncionarioModels extends PessoaModels {
    private static final long serialVersionUID = 1L;

    private LocalDate dataDeInicio;

    public LocalDate getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(LocalDate dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}