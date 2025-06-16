package com.Bitech.rent_a_car.Services.Aluguer;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Bitech.rent_a_car.Dtos.Aluguer.AcessorioRequestDTO;
import com.Bitech.rent_a_car.Dtos.Aluguer.AcessorioResponseDTO;
import com.Bitech.rent_a_car.Models.Aluguer.AcessorioModels;
import com.Bitech.rent_a_car.Models.Aluguer.CarroModels;
import com.Bitech.rent_a_car.Repository.Aluguer.AcessorioRepository;
import com.Bitech.rent_a_car.Repository.Aluguer.CarroRepository;

@Service
public class AcessorioService {

    private final AcessorioRepository acessorioRepository;
    private final CarroRepository carroRepository;

    public AcessorioService(AcessorioRepository acessorioRepository, CarroRepository carroRepository) {
        this.acessorioRepository = acessorioRepository;
        this.carroRepository = carroRepository;
    }

    public List<AcessorioModels> getAll() {
        return acessorioRepository.findAll();
    }

    public AcessorioResponseDTO getOne(UUID id) {
        AcessorioModels acessorio = acessorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Acessorio não encontrado"));

        AcessorioResponseDTO dto = new AcessorioResponseDTO();
        dto.setId(acessorio.getId());
        dto.setCarro(acessorio.getCarro());
        dto.setDescricao(acessorio.getDescricao());
        dto.setCarro(acessorio.getCarro());
        return dto;

    }

    @Transactional
    public AcessorioResponseDTO create(AcessorioRequestDTO acessorioRequestDTO) throws Exception {
        AcessorioModels acessorio = new AcessorioModels();
        if (acessorioRequestDTO.getDescricao() == null) {
            throw new Exception("O nome do acessorio não pode ser nulo");

        }
        if (acessorioRequestDTO.getDescricao().isBlank()) {
            throw new Exception("O nome do acessorio não pode ser vazio");

        }
        if (acessorioRequestDTO.getCarro() == null) {
            throw new Exception("O ID do veíclulo não pode ser vazio");

        }
        CarroModels carro = carroRepository.findById(acessorioRequestDTO.getCarro())
                .orElseThrow(() -> new RuntimeException("veíclulo não encontrado"));
        acessorio.setDescricao(acessorioRequestDTO.getDescricao());
        acessorio.setCarro(carro);

        AcessorioModels salvar = acessorioRepository.save(acessorio);

        var dto = new AcessorioResponseDTO();
        dto.setCarro(carro);
        dto.setId(salvar.getId());
        dto.setDescricao(salvar.getDescricao());

        return dto;
    }

    @Transactional
    public AcessorioResponseDTO update(UUID id, AcessorioRequestDTO acessorioRequestDTO) throws Exception {
        AcessorioModels acessorio = acessorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Acessorio do veíclulo não encontrado"));

        if (acessorioRequestDTO.getDescricao() == null) {
            throw new Exception("O nome do acessorio não pode ser nulo");

        }
        if (acessorioRequestDTO.getDescricao().isBlank()) {
            throw new Exception("O nome do acessorio não pode ser vazio");

        }
        if (acessorioRequestDTO.getCarro() == null) {
            throw new Exception("O ID do veíclulo não pode ser vazio");

        }

        CarroModels carro = carroRepository.findById(acessorioRequestDTO.getCarro())
                .orElseThrow(() -> new RuntimeException("veíclulo não encontrado"));
        acessorio.setDescricao(acessorioRequestDTO.getDescricao());
        acessorio.setCarro(carro);

        AcessorioModels salvar = acessorioRepository.save(acessorio);

        var dto = new AcessorioResponseDTO();
        dto.setCarro(carro);
        dto.setId(salvar.getId());
        dto.setDescricao(salvar.getDescricao());

        return dto;
    }

    public void delete(UUID id) {
        AcessorioModels acessorio = acessorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Acessorio não encontrado"));
        acessorioRepository.delete(acessorio);

    }

}
