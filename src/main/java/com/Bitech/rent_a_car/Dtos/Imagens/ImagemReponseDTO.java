package com.Bitech.rent_a_car.Dtos.Imagens;

import java.util.UUID;
import com.Bitech.rent_a_car.Dtos.Aluguer.CarroResumoDTO;
public class ImagemReponseDTO {

    private UUID id;
    private String imagem;
    private String url;
    private CarroResumoDTO carro;
    private String nomeUnico;

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
    public CarroResumoDTO getCarro() {
        return carro;
    }
    public void setCarro(CarroResumoDTO carro) {
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
