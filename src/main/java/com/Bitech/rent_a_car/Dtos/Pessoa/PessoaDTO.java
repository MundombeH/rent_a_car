package com.Bitech.rent_a_car.Dtos.Pessoa;

import java.time.LocalDate;
import java.util.UUID;
import com.Bitech.rent_a_car.Models.Pessoa.GeneroEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PessoaDTO {

    private UUID id;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O sobrenome é obrigatório.")
    private String sobreNome;

    @NotBlank(message = "O BI é obrigatório.")
    @NotNull
    @Pattern(regexp = "^[0-9]{9}[A-Z]{2}[0-9]{3}$")
    @Size(max = 14, min = 14)
    private String bi;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @Past(message = "A data de nascimento deve estar no passado.")
    private LocalDate dataDeNascimento;

    // private int idade;
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    // public void setIDade(LocalDate dataDeNascimento) {
    // int idade = Period.between(dataDeNascimento, LocalDate.now()).getYears();

    // this.idade = idade;
    // }

    // public int getIdade() {
    // return idade;
    // }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }
}
