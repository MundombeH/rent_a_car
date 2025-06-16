package com.Bitech.rent_a_car.Services.Aluguer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Bitech.rent_a_car.Dtos.Aluguer.FabricanteRequestDTO;
import com.Bitech.rent_a_car.Dtos.Aluguer.FabricanteResponseDTO;
import com.Bitech.rent_a_car.Models.Aluguer.FabricanteModels;
import com.Bitech.rent_a_car.Models.Aluguer.ModeloModels;
import com.Bitech.rent_a_car.Repository.Aluguer.FabricanteRepository;
import com.Bitech.rent_a_car.Repository.Aluguer.ModeloRepository;

@Service
public class FabricanteService {
    
    private final FabricanteRepository fabricanteRepository;
    private final ModeloRepository modeloRepository;

    public FabricanteService(FabricanteRepository fabricanteRepository, ModeloRepository modeloRepository) {
        this.fabricanteRepository = fabricanteRepository;
        this.modeloRepository = modeloRepository;
    }

    public List<FabricanteModels> getAll() {
        return fabricanteRepository.findAll();
    }

    public FabricanteResponseDTO getOne(UUID id) {
        FabricanteModels modelo = fabricanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));

        var dto = new FabricanteResponseDTO();
        dto.setId(modelo.getId());
        dto.setNome(modelo.getNome());
        return dto;

    }

    @Transactional
    public FabricanteResponseDTO create(FabricanteRequestDTO fabricanteRequestDTO) throws Exception {
        FabricanteModels modelo = new FabricanteModels();
        if (fabricanteRequestDTO.getNome() == null) {
            throw new Exception("O nome do Fabricante não pode ser nulo");

        }
        if (fabricanteRequestDTO.getNome().isBlank()) {
            throw new Exception("O nome do Fabricante não pode ser vazio");

        }

        modelo.setNome(fabricanteRequestDTO.getNome());

        FabricanteModels salvar = fabricanteRepository.save(modelo);
        var dto = new FabricanteResponseDTO();

        dto.setId(salvar.getId());
        dto.setNome(salvar.getNome());

        return dto;
    }

    public FabricanteResponseDTO update(UUID id, FabricanteRequestDTO fabricanteRequestDTO) throws Exception {
        FabricanteModels modelo = fabricanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));

        if (fabricanteRequestDTO.getNome() == null) {
            throw new Exception("O nome do Fabricante não pode ser nulo");

        }
        if (fabricanteRequestDTO.getNome().isBlank()) {
            throw new Exception("O nome do Fabricante não pode ser vazio");

        }
        Set<ModeloModels> modelos = new HashSet<>(modeloRepository.findAllById(fabricanteRequestDTO.getModelo()));

        modelo.setNome(fabricanteRequestDTO.getNome());
        modelo.setModelo(modelos);
        FabricanteModels salvar = fabricanteRepository.save(modelo);
        var dto = new FabricanteResponseDTO();

        dto.setId(salvar.getId());
        dto.setNome(salvar.getNome());
        dto.setModelo(salvar.getModelo());

        return dto;

    }

    public void delete(UUID id) {
        FabricanteModels modelo = fabricanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));
        fabricanteRepository.delete(modelo);

    }

}
