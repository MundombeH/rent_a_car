package com.Bitech.rent_a_car.Dtos.Pessoa;

import java.time.LocalDate;
import java.time.Period;

public class FuncionarioResponseDTO extends PessoaDTO {

    private LocalDate dataDeInicio;
    private int idade;

    // Getter e Setter
    public LocalDate getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(LocalDate dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }
      public void setIDade(LocalDate dataDeNascimento) {
        int idade = Period.between(dataDeNascimento, LocalDate.now()).getYears();

        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }


}
