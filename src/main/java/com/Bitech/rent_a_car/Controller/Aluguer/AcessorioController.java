package com.Bitech.rent_a_car.Controller.Aluguer;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bitech.rent_a_car.Dtos.Aluguer.AcessorioRequestDTO;
import com.Bitech.rent_a_car.Dtos.Aluguer.AcessorioResponseDTO;
import com.Bitech.rent_a_car.Models.Aluguer.AcessorioModels;
import com.Bitech.rent_a_car.Services.Aluguer.AcessorioService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/acessorio")
public class AcessorioController {
 @Autowired
    private AcessorioService acessorioService;
    @GetMapping("")
    public ResponseEntity<List<AcessorioResponseDTO>> getAcessorio() {
        List<AcessorioModels> acessorio = acessorioService.getAll();
        List<AcessorioResponseDTO> dtos = acessorio.stream().map(func -> {
            var dto = new AcessorioResponseDTO();
            BeanUtils.copyProperties(func, dto);
            return dto;
        }).toList();
        return ResponseEntity.status(200).body(dtos);

    }

    @GetMapping("carro")
    public ResponseEntity<Object> getAcessorioPorCarros(@Param(value = "carro") UUID carro) {
        List<AcessorioResponseDTO> dto = acessorioService.getPorCarro(carro);
        return ResponseEntity.status(200).body(dto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAcessorio(@PathVariable UUID id) {
        AcessorioResponseDTO dto = acessorioService.getOne(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PostMapping("")
    public ResponseEntity<Object> createFabricante(@RequestBody @Valid AcessorioRequestDTO acessorioRequestDTO)
            throws Exception {
        AcessorioResponseDTO dto = acessorioService.create(acessorioRequestDTO);

        return ResponseEntity.status(200).body(dto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCarro(@PathVariable UUID id,
            @RequestBody @Valid AcessorioRequestDTO acessorioRequestDTO)
            throws Exception {
        AcessorioResponseDTO dto = acessorioService.update(id, acessorioRequestDTO);

        return ResponseEntity.status(200).body(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCarro(@PathVariable UUID id) throws Exception {
        acessorioService.delete(id);
        return ResponseEntity.status(200).body("Acessorio do carro  Eliminado com suucesso!");
    }

}

