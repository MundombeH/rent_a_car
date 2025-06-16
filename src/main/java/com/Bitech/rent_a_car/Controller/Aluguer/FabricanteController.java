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

import com.Bitech.rent_a_car.Dtos.Aluguer.FabricanteRequestDTO;
import com.Bitech.rent_a_car.Dtos.Aluguer.FabricanteResponseDTO;
import com.Bitech.rent_a_car.Models.Aluguer.FabricanteModels;
import com.Bitech.rent_a_car.Services.Aluguer.FabricanteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("fabricante")
public class FabricanteController {
    @Autowired
    private FabricanteService fabricanteService;

    @GetMapping("")
    public ResponseEntity<List<FabricanteResponseDTO>> getFabricantes() {
        List<FabricanteModels> modelo = fabricanteService.getAll();
        List<FabricanteResponseDTO> dtos = modelo.stream().map(func -> {
            var dto = new FabricanteResponseDTO();
            BeanUtils.copyProperties(func, dto);
            return dto;
        }).toList();
        return ResponseEntity.status(200).body(dtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneFabricante(@PathVariable UUID id) {
        FabricanteResponseDTO dto = fabricanteService.getOne(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PostMapping("")
    public ResponseEntity<Object> createFabricante(@RequestBody @Valid FabricanteRequestDTO fabricanteRequestDTO)
            throws Exception {
        FabricanteResponseDTO dto = fabricanteService.create(fabricanteRequestDTO);

        return ResponseEntity.status(200).body(dto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCarro(@PathVariable UUID id,
            @RequestBody @Valid FabricanteRequestDTO fabricanteRequestDTO)
            throws Exception {
        FabricanteResponseDTO dto = fabricanteService.update(id, fabricanteRequestDTO);

        return ResponseEntity.status(200).body(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCarro(@PathVariable UUID id) throws Exception {
        fabricanteService.delete(id);
        return ResponseEntity.status(200).body("Fabricante Eliminado com suucesso!");
    }

}
