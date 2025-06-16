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

import com.Bitech.rent_a_car.Dtos.Aluguer.AluguerRequestDTO;
import com.Bitech.rent_a_car.Dtos.Aluguer.AluguerResponseDTO;
import com.Bitech.rent_a_car.Models.Aluguer.AluguerModels;
import com.Bitech.rent_a_car.Services.Aluguer.AluguerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("aluguer")
public class AlugerController {

    @Autowired
    private AluguerService aluguerService;

    @GetMapping("")
    public ResponseEntity<List<AluguerResponseDTO>> getAluguer() {
        List<AluguerModels> aluguer = aluguerService.getAll();
        List<AluguerResponseDTO> dtos = aluguer.stream().map(func -> {
            var dto = new AluguerResponseDTO();
            BeanUtils.copyProperties(func, dto);
            return dto;
        }).toList();
        return ResponseEntity.status(200).body(dtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAluguer(@PathVariable UUID id) {
        AluguerResponseDTO dto = aluguerService.getOne(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PostMapping("")
    public ResponseEntity<Object> createAluguer(@RequestBody @Valid AluguerRequestDTO aluguerRequestDTO)
            throws Exception {
        AluguerResponseDTO dto = aluguerService.create(aluguerRequestDTO);

        return ResponseEntity.status(200).body(dto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAluguer(@PathVariable UUID id,
            @RequestBody @Valid AluguerRequestDTO aluguerRequestDTO)
            throws Exception {
        AluguerResponseDTO dto = aluguerService.update(id, aluguerRequestDTO);

        return ResponseEntity.status(200).body(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAluguer(@PathVariable UUID id) throws Exception {
        aluguerService.delete(id);
        return ResponseEntity.status(200).body("Aluguer do veiculo Eliminado com suucesso!");
    }

}
