package com.Bitech.rent_a_car.Controller.Pessoa;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.Bitech.rent_a_car.Dtos.Pessoa.FuncionarioRequestDTO;
import com.Bitech.rent_a_car.Dtos.Pessoa.FuncionarioResponseDTO;
import com.Bitech.rent_a_car.Models.Pessoa.FuncionarioModels;
import com.Bitech.rent_a_car.Services.Pessoa.FuncionarioService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("")
    public ResponseEntity<List<FuncionarioResponseDTO>> getFuncionarios() {
        List<FuncionarioModels> funcionarios = funcionarioService.getAll();
        List<FuncionarioResponseDTO> responseDtos = funcionarios.stream().map(func -> {
            var dto = new FuncionarioResponseDTO();
            BeanUtils.copyProperties(func, dto);
            return dto;
        }).toList();
        return ResponseEntity.status(200).body(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneFuncionario(@PathVariable UUID id) {
        FuncionarioResponseDTO dto = funcionarioService.getOne(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);

    }

    @PostMapping("")
    public ResponseEntity<Object> postFuncionario(@RequestBody @Valid FuncionarioRequestDTO funcionarioRequestDTO) {
        FuncionarioResponseDTO addFuncionario = funcionarioService.create(funcionarioRequestDTO);

        FuncionarioRequestDTO responseDto = new FuncionarioRequestDTO();
        BeanUtils.copyProperties(addFuncionario, responseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletFuncionario(@PathVariable(value = "id") UUID id) throws Exception {

        funcionarioService.deleteFuncionario(id);
        return ResponseEntity.status(200).body("O Funcionario Foi Eliminado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFuncionario(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid FuncionarioRequestDTO funcionarioRequestDTO) throws Exception {
        FuncionarioResponseDTO funcionario = funcionarioService.updateFuncionario(id, funcionarioRequestDTO);

        FuncionarioRequestDTO responseDto = new FuncionarioRequestDTO();
        BeanUtils.copyProperties(funcionario, responseDto);
        return ResponseEntity.status(201).body(responseDto);
    }

}
