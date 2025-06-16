package com.Bitech.rent_a_car.Controller.Aluguer;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bitech.rent_a_car.Dtos.Aluguer.CarroRequestDto;
import com.Bitech.rent_a_car.Dtos.Aluguer.CarroResponseDTO;
import com.Bitech.rent_a_car.Models.Aluguer.CarroModels;
import com.Bitech.rent_a_car.Services.Aluguer.CarroService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping("")
    public ResponseEntity<List<CarroResponseDTO>> getCarros() {
        List<CarroModels> carro = carroService.getAll();
        List<CarroResponseDTO> responseDtos = carro.stream().map(func -> {
            var dto = new CarroResponseDTO();
            BeanUtils.copyProperties(func, dto);
            return dto;
        }).toList();
        return ResponseEntity.status(200).body(responseDtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCarro(@PathVariable UUID id) {
        CarroResponseDTO dto = carroService.getOne(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PostMapping("")
    public ResponseEntity<Object> createCarro(@RequestBody @Valid CarroRequestDto carroRequestDto) throws Exception {
        CarroResponseDTO carro = carroService.postCarro(carroRequestDto);

        return ResponseEntity.status(200).body(carro);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCarro(@PathVariable UUID id, @RequestBody @Valid CarroRequestDto carroRequestDto)
            throws Exception {
        CarroResponseDTO carro = carroService.updateCarro(id, carroRequestDto);

        return ResponseEntity.status(200).body(carro);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCarro(@PathVariable UUID id) throws Exception{
        carroService.deleteCarro(id);
        return ResponseEntity.status(200).body("Carro Eliminado com suucesso!");
    } 

}
