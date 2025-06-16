package com.Bitech.rent_a_car.Services.Aluguer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Bitech.rent_a_car.Dtos.Aluguer.ApoliceDeSeguroRequestDTO;
import com.Bitech.rent_a_car.Dtos.Aluguer.ApoliceDeSeguroResponseDTO;
import com.Bitech.rent_a_car.Models.Aluguer.ApoliceDeSeguroModels;
import com.Bitech.rent_a_car.Repository.Aluguer.AluguerRepository;
import com.Bitech.rent_a_car.Repository.Aluguer.ApoliceDeSeguroRepository;

import jakarta.transaction.Transactional;

@Service
public class ApoliceDeSeguroService {

    private final ApoliceDeSeguroRepository apoliceDeSeguroRepository;
    private final AluguerRepository aluguerRepository;


    public ApoliceDeSeguroService(ApoliceDeSeguroRepository apoliceDeSeguroRepository,
            AluguerRepository aluguerRepository) {
        this.apoliceDeSeguroRepository = apoliceDeSeguroRepository;
        this.aluguerRepository = aluguerRepository;
    }

    public List<ApoliceDeSeguroModels> getAll() {
        return apoliceDeSeguroRepository.findAll();
    }

    public ApoliceDeSeguroResponseDTO getOne(UUID id) {
        ApoliceDeSeguroModels apolice = apoliceDeSeguroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apolice de seguro não encontrada"));

        var dto = new ApoliceDeSeguroResponseDTO();
        dto.setId(apolice.getId());
        dto.setCausasNaturais(apolice.getCausasNaturais());
        dto.setProteccaoTerceiros(apolice.getProteccaoTerceiros());
        dto.setValorDaFranquia(apolice.getValorDaFranquia());
        dto.setAluguer(apolice.getAluguer());
        return dto;
    }

    @Transactional
    public ApoliceDeSeguroResponseDTO create(ApoliceDeSeguroRequestDTO apoliceDeSeguroRequestDTO) throws Exception {
        ApoliceDeSeguroModels apolice = new ApoliceDeSeguroModels();
        if (apoliceDeSeguroRequestDTO.getCausasNaturais() == null) {
            // throw new Exception("A ")
            apolice.setCausasNaturais(false);

        }
        if (apoliceDeSeguroRequestDTO.getProteccaoTerceiros() == null) {
            apolice.setProteccaoTerceiros(false);
        }
        if (apoliceDeSeguroRequestDTO.getValorDaFranquia() == null) {
            throw new Exception("O valor da franquia não pode ser vazio!");
        }
        if (Double.isNaN(apoliceDeSeguroRequestDTO.getValorDaFranquia())) {
            throw new Exception("O valor da franquia Tem de ser um numero!");

        }        
        apolice.setCausasNaturais(apoliceDeSeguroRequestDTO.getCausasNaturais());
        apolice.setProteccaoTerceiros(apoliceDeSeguroRequestDTO.getProteccaoTerceiros());
        apolice.setValorDaFranquia(apoliceDeSeguroRequestDTO.getValorDaFranquia());

        ApoliceDeSeguroModels salvar = apoliceDeSeguroRepository.save(apolice);

        var dto = new ApoliceDeSeguroResponseDTO();
        dto.setId(salvar.getId());
        dto.setCausasNaturais(salvar.getCausasNaturais());
        dto.setProteccaoTerceiros(salvar.getProteccaoTerceiros());
        dto.setValorDaFranquia(salvar.getValorDaFranquia());
        return dto;

    }

    @Transactional
    public ApoliceDeSeguroResponseDTO update(UUID id, ApoliceDeSeguroRequestDTO apoliceDeSeguroRequestDTO)
            throws Exception {
        ApoliceDeSeguroModels apolice = apoliceDeSeguroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apolice de seguro não encontrada"));
        if (apoliceDeSeguroRequestDTO.getCausasNaturais() == null) {
            // throw new Exception("A ")
            apolice.setCausasNaturais(false);

        }
        if (apoliceDeSeguroRequestDTO.getProteccaoTerceiros() == null) {
            apolice.setProteccaoTerceiros(false);
        }
        if (apoliceDeSeguroRequestDTO.getValorDaFranquia() == null) {
            throw new Exception("O valor da franquia não pode ser vazio!");
        }
        if (Double.isNaN(apoliceDeSeguroRequestDTO.getValorDaFranquia())) {
            throw new Exception("O valor da franquia Tem de ser um numero!");

        }
        apolice.setCausasNaturais(apoliceDeSeguroRequestDTO.getCausasNaturais());
        apolice.setProteccaoTerceiros(apoliceDeSeguroRequestDTO.getProteccaoTerceiros());
        apolice.setValorDaFranquia(apoliceDeSeguroRequestDTO.getValorDaFranquia());

        ApoliceDeSeguroModels salvar = apoliceDeSeguroRepository.save(apolice);
        var dto = new ApoliceDeSeguroResponseDTO();
        dto.setId(salvar.getId());
        dto.setCausasNaturais(salvar.getCausasNaturais());
        dto.setProteccaoTerceiros(salvar.getProteccaoTerceiros());
        dto.setValorDaFranquia(salvar.getValorDaFranquia());
        return dto;
    }

    @Transactional
    public void delete(UUID id) throws Exception {
        Optional<ApoliceDeSeguroModels> apolice = apoliceDeSeguroRepository.findById(id);
        if (!apolice.isPresent()) {
            throw new Exception("Apolice de seguro do ceiculo não foi encontrada!");
        }
        apoliceDeSeguroRepository.deleteById(id);
    }

}