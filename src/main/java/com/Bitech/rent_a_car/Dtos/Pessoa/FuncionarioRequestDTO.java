package com.Bitech.rent_a_car.Dtos.Pessoa;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class FuncionarioRequestDTO extends PessoaDTO {

    @NotNull(message = "A data de início é obrigatória.")
    @PastOrPresent(message = "A data de início não pode ser no futuro.")
    private LocalDate dataDeInicio;

    public LocalDate getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(LocalDate dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }
}
