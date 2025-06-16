package com.Bitech.rent_a_car.Controller.Pessoa;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.Bitech.rent_a_car.Dtos.Pessoa.MotoristaRequestDTO;
import com.Bitech.rent_a_car.Dtos.Pessoa.MotoristaResponseDTO;
import com.Bitech.rent_a_car.Models.Pessoa.MotoristaModels;
import com.Bitech.rent_a_car.Services.Pessoa.MotoristaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @GetMapping("")
    public ResponseEntity<List<MotoristaResponseDTO>> getMotoristas() {
        List<MotoristaModels> motoristas = motoristaService.getAll();
        List<MotoristaResponseDTO> responseDtos = motoristas.stream().map(func -> {
            var dto = new MotoristaResponseDTO();
            BeanUtils.copyProperties(func, dto);
            return dto;
        }).toList();
        return ResponseEntity.status(200).body(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneMotorista(@PathVariable UUID id) {
        MotoristaResponseDTO dto = motoristaService.getOne(id);

        return ResponseEntity.status(200).body(dto);
    }

    @PostMapping("")
    public ResponseEntity<Object> postMotorista(@RequestBody @Valid MotoristaRequestDTO motoristaRequestDTO) {
        MotoristaResponseDTO addMotorista = motoristaService.create(motoristaRequestDTO);

        MotoristaResponseDTO responseDto = new MotoristaResponseDTO();
        BeanUtils.copyProperties(addMotorista, responseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletMotorista(@PathVariable  UUID id) throws Exception {

        motoristaService.deleteMotorista(id);
        return ResponseEntity.status(200).body("O Motorista Foi Eliminado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMotorista(@PathVariable UUID id, @RequestBody @Valid MotoristaRequestDTO motoristaRequestDTO ) throws Exception{
        MotoristaResponseDTO motorista = motoristaService.update(id, motoristaRequestDTO);

        MotoristaRequestDTO responseDto = new MotoristaRequestDTO();
        BeanUtils.copyProperties(motorista, responseDto);
        return ResponseEntity.status(201).body(responseDto);
    }

}
