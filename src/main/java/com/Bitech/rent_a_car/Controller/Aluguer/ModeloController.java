package com.Bitech.rent_a_car.Controller.Aluguer;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bitech.rent_a_car.Dtos.Aluguer.ModeloRequestDTO;
import com.Bitech.rent_a_car.Dtos.Aluguer.ModeloResponseDTO;
import com.Bitech.rent_a_car.Models.Aluguer.ModeloModels;
import com.Bitech.rent_a_car.Services.Aluguer.ModeloService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("modelo")
public class ModeloController {
     @Autowired
    private ModeloService modeloService;

    @GetMapping("")
    public ResponseEntity<List<ModeloResponseDTO>> getModelos() {
        List<ModeloModels> modelo = modeloService.getAll();
        List<ModeloResponseDTO> responseDtos = modelo.stream().map(func -> {
            var dto = new ModeloResponseDTO();
            BeanUtils.copyProperties(func, dto);
            return dto;
        }).toList();
        return ResponseEntity.status(200).body(responseDtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneModelo(@PathVariable UUID id) {
        ModeloResponseDTO dto = modeloService.getOne(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PostMapping("")
    public ResponseEntity<Object> createModeleo(@RequestBody @Valid ModeloRequestDTO modeloRequestDTO) throws Exception {
        ModeloResponseDTO carro = modeloService.create(modeloRequestDTO);

        return ResponseEntity.status(200).body(carro);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCarro(@PathVariable UUID id, @RequestBody @Valid ModeloRequestDTO modeloRequestDTO)
            throws Exception {
        ModeloResponseDTO modelo = modeloService.update(id, modeloRequestDTO);

        return ResponseEntity.status(200).body(modelo);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCarro(@PathVariable UUID id) throws Exception{
        modeloService.delete(id);
        return ResponseEntity.status(200).body("Modelo Eliminado com suucesso!");
    } 

}

