package com.Bitech.rent_a_car.Services.Aluguer;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Bitech.rent_a_car.Dtos.Aluguer.ModeloRequestDTO;
import com.Bitech.rent_a_car.Dtos.Aluguer.ModeloResponseDTO;
import com.Bitech.rent_a_car.Models.Aluguer.FabricanteModels;
import com.Bitech.rent_a_car.Models.Aluguer.ModeloModels;
import com.Bitech.rent_a_car.Repository.Aluguer.FabricanteRepository;
import com.Bitech.rent_a_car.Repository.Aluguer.ModeloRepository;

@Service
public class ModeloService {

    private final FabricanteRepository fabricanteRepository;
    @Autowired
    private ModeloRepository modeloRepository;

    ModeloService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    public List<ModeloModels> getAll() {
        return modeloRepository.findAll();
    }

    public ModeloResponseDTO getOne(UUID id) {
        ModeloModels modelo = modeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        var dto = new ModeloResponseDTO();
        dto.setId(modelo.getId());
        dto.setCategoria(modelo.getCategoria());
        dto.setDescricao(modelo.getDescricao());
        dto.setFabricante(modelo.getFabricante());
        return dto;

    }

    @Transactional
    public ModeloResponseDTO create(ModeloRequestDTO modeloRequestDTO) throws Exception {
        ModeloModels modelo = new ModeloModels();

        if (modeloRequestDTO.getCategoria() == null) {
            throw new Exception("A categória do modelo do Veículo não pode ser nula");
        }
        // if (!modeloRequestDTO.getCategoria().equals(CategoriaEnum.LIGEIRO)
        // || !modeloRequestDTO.getCategoria().equals(CategoriaEnum.PADRAO)
        // || !modeloRequestDTO.getCategoria().equals(CategoriaEnum.PESADO)) {

        // throw new Exception("categoria:"+ modeloRequestDTO.getCategoria() +" não
        // disponivel. Opções disponiveis: " + CategoriaEnum.LIGEIRO + " "
        // + CategoriaEnum.PESADO + " " + CategoriaEnum.PADRAO);
        // }
        System.out.println("Categoria:" + modeloRequestDTO.getCategoria());
        if (modeloRequestDTO.getDescricao() == null) {
            throw new Exception("A Descrição do modelo do Veículo não pode ser nula");

        }
        if (modeloRequestDTO.getDescricao().isEmpty()) {
            throw new Exception("A Descrição do modelo do Veículo não pode ser vazia");

        }
        FabricanteModels fabricante = fabricanteRepository.findById(modeloRequestDTO.getFabricante()).orElseThrow();

        modelo.setDescricao(modeloRequestDTO.getDescricao());
        modelo.setCategoria(modeloRequestDTO.getCategoria());
        modelo.setFabricante(fabricante);

        ModeloModels salvar = modeloRepository.save(modelo);
        var dto = new ModeloResponseDTO();

        dto.setId(salvar.getId());
        dto.setCategoria(salvar.getCategoria());
        dto.setDescricao(salvar.getDescricao());
        dto.setFabricante(salvar.getFabricante());

        return dto;
    }

    public ModeloResponseDTO update(UUID id, ModeloRequestDTO modeloRequestDTO) throws Exception {
        ModeloModels modelo = modeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modelo do Carro não encontrado"));

        if (modeloRequestDTO.getCategoria() == null) {
            throw new Exception("A categória do modelo do carro não pode ser nula");
        }
        // if (!modeloRequestDTO.getCategoria().equals(CategoriaEnum.LIGEIRO)
        // || !modeloRequestDTO.getCategoria().equals(CategoriaEnum.PADRAO)
        // || !modeloRequestDTO.getCategoria().equals(CategoriaEnum.PESADO)) {

        // throw new Exception("categoria não disponivel./nOpções disponiveis: " +
        // CategoriaEnum.LIGEIRO + "/n"
        // + CategoriaEnum.PESADO + "/n" + CategoriaEnum.PADRAO);
        // }
        if (modeloRequestDTO.getDescricao() == null) {
            throw new Exception("A Descrição do modelo do carro não pode ser nula");

        }
        if (modeloRequestDTO.getDescricao().isEmpty()) {
            throw new Exception("A Descrição do modelo do carro não pode ser vazia");

        }

        FabricanteModels fabricante = fabricanteRepository.findById(modeloRequestDTO.getFabricante()).orElseThrow();
        modelo.setDescricao(modeloRequestDTO.getDescricao());
        modelo.setCategoria(modeloRequestDTO.getCategoria());
        modelo.setFabricante(fabricante);

        ModeloModels salvar = modeloRepository.save(modelo);
        var dto = new ModeloResponseDTO();

        dto.setId(salvar.getId());
        dto.setCategoria(salvar.getCategoria());
        dto.setDescricao(salvar.getDescricao());
        dto.setFabricante(salvar.getFabricante());

        return dto;

    }

    public void delete(UUID id) {
        ModeloModels modelo = modeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        modeloRepository.delete(modelo);

    }

}
