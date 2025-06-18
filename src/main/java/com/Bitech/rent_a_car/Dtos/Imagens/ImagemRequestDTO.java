package com.Bitech.rent_a_car.Dtos.Imagens;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class ImagemRequestDTO {
    private UUID id;

    @NotBlank
    private String imagem;
    @NotNull
    private UUID carro;
    
    private String nomeUnico;
    
    private String url;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    public UUID getCarro() {
        return carro;
    }
    public void setCarro(UUID carro) {
        this.carro = carro;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getNomeUnico() {
        return nomeUnico;
    }
    public void setNomeUnico(String nomeUnico) {
        this.nomeUnico = nomeUnico;
    }
}
