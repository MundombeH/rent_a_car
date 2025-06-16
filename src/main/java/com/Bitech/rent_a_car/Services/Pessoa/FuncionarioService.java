package com.Bitech.rent_a_car.Services.Pessoa;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.Bitech.rent_a_car.Dtos.Pessoa.FuncionarioRequestDTO;
import com.Bitech.rent_a_car.Dtos.Pessoa.FuncionarioResponseDTO;
import com.Bitech.rent_a_car.Models.Pessoa.FuncionarioModels;
import com.Bitech.rent_a_car.Repository.Pessoa.FuncionarioRepository;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<FuncionarioModels> getAll() {
        return funcionarioRepository.findAll();
    }

    // Metodo para buscar pelo Id e
    public FuncionarioResponseDTO getOne(UUID id) {
        FuncionarioModels funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        var dto = new FuncionarioResponseDTO();
        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setSobreNome(funcionario.getSobreNome());
        dto.setDataDeInicio(funcionario.getDataDeInicio());
        dto.setDataDeNascimento(funcionario.getDataDeNascimento());
        dto.setBi(funcionario.getBi());
        dto.setIDade(funcionario.getDataDeNascimento());
        return dto;
    }

    // Metodo para cadastrar'
    @Transactional
    public FuncionarioResponseDTO create(FuncionarioRequestDTO funcionarioRequestDTO) {

        if (funcionarioRequestDTO.getNome() == null || funcionarioRequestDTO.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        if (funcionarioRequestDTO.getSobreNome() == null || funcionarioRequestDTO.getSobreNome().isBlank()) {
            throw new IllegalArgumentException("O sobre nome não pode ser vazio");
        }
        if (funcionarioRequestDTO.getBi() == null || funcionarioRequestDTO.getBi().isBlank()) {
            throw new IllegalArgumentException("O Bilhete de identidade não pode ser vazio");
        }
        if (!funcionarioRequestDTO.getBi().matches("^[0-9]{9}[A-Z]{2}[0-9]{3}$")) {
            throw new IllegalArgumentException("Formato de BI inválido (ex: 004748583LA044)");
        }
        if (funcionarioRequestDTO.getBi().length() != 14) {
            throw new IllegalArgumentException("O Bilhete de identidade deve conter 14 caracteres!");
        }
        if (funcionarioRequestDTO.getDataDeNascimento() == null) {
            throw new IllegalArgumentException("A data de nascimento é obrigatória");
        }

        if (funcionarioRequestDTO.getDataDeNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode estar no futuro");
        }

        int idade = Period.between(funcionarioRequestDTO.getDataDeNascimento(), LocalDate.now()).getYears();
        if (idade < 18) {
            throw new IllegalArgumentException("O funcionário deve ter no mínimo 18 anos");
        }

        if (funcionarioRequestDTO.getDataDeInicio() == null) {
            throw new IllegalArgumentException("A data de início é obrigatória");
        }

        if (funcionarioRequestDTO.getDataDeInicio().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de início não pode estar no futuro");
        }

        FuncionarioModels funcionarioModels = new FuncionarioModels();
        funcionarioModels.setNome(funcionarioRequestDTO.getNome());
        funcionarioModels.setSobreNome(funcionarioRequestDTO.getSobreNome());
        funcionarioModels.setBi(funcionarioRequestDTO.getBi());
        funcionarioModels.setDataDeNascimento(funcionarioRequestDTO.getDataDeNascimento());
        funcionarioModels.setDataDeInicio(funcionarioRequestDTO.getDataDeInicio());

        FuncionarioModels save = funcionarioRepository.save(funcionarioModels);

        FuncionarioResponseDTO response = new FuncionarioResponseDTO();
        response.setId(save.getId());
        response.setNome(save.getNome());
        response.setSobreNome(save.getSobreNome());
        response.setBi(save.getBi());
        response.setDataDeNascimento(save.getDataDeNascimento());
        response.setDataDeInicio(save.getDataDeInicio());

        return response;

    }

    @Transactional
    public FuncionarioResponseDTO updateFuncionario(UUID id,
            FuncionarioRequestDTO funcionarioRequestDTO) {
        // Busca o funcionário existente
        FuncionarioModels funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        // Validação simples (exemplo)
        if (funcionarioRequestDTO.getNome() == null || funcionarioRequestDTO.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        if (funcionarioRequestDTO.getSobreNome() == null || funcionarioRequestDTO.getSobreNome().isBlank()) {
            throw new IllegalArgumentException("O sobre nome não pode ser vazio");
        }
        if (funcionarioRequestDTO.getBi() == null || funcionarioRequestDTO.getBi().isBlank()) {
            throw new IllegalArgumentException("O Bilhete de identidade não pode ser vazio");
        }
        if (!funcionarioRequestDTO.getBi().matches("^[0-9]{9}[A-Z]{2}[0-9]{3}$")) {
            throw new IllegalArgumentException("Formato de BI inválido (ex: 004748583LA044)");
        }
        if (funcionarioRequestDTO.getBi().length() != 14) {
            throw new IllegalArgumentException("O Bilhete de identidade deve conter 14 caracteres!");
        }
        if (funcionarioRequestDTO.getDataDeNascimento() == null) {
            throw new IllegalArgumentException("A data de nascimento é obrigatória");
        }

        if (funcionarioRequestDTO.getDataDeNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode estar no futuro");
        }

        int idade = Period.between(funcionarioRequestDTO.getDataDeNascimento(), LocalDate.now()).getYears();
        if (idade < 18) {
            throw new IllegalArgumentException("O funcionário deve ter no mínimo 18 anos");
        }

        if (funcionarioRequestDTO.getDataDeInicio() == null) {
            throw new IllegalArgumentException("A data de início é obrigatória");
        }

        if (funcionarioRequestDTO.getDataDeInicio().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de início não pode estar no futuro");
        }
        // Atualiza os campos
        funcionario.setId(id);
        funcionario.setNome(funcionarioRequestDTO.getNome());
        funcionario.setSobreNome(funcionarioRequestDTO.getSobreNome());
        funcionario.setBi(funcionarioRequestDTO.getBi());
        funcionario.setDataDeNascimento(funcionarioRequestDTO.getDataDeNascimento());
        funcionario.setDataDeInicio(funcionarioRequestDTO.getDataDeInicio());

        // Salva no banco
        FuncionarioModels save = funcionarioRepository.save(funcionario);

        // Mapeia para Response DTO (você pode usar MapStruct para isso)
        FuncionarioResponseDTO response = new FuncionarioResponseDTO();
        response.setId(save.getId());
        response.setNome(save.getNome());
        response.setSobreNome(save.getSobreNome());
        response.setBi(save.getBi());
        response.setDataDeNascimento(save.getDataDeNascimento());
        response.setDataDeInicio(save.getDataDeInicio());

        return response;
    }

    @Transactional
    public void deleteFuncionario(UUID id) throws Exception {

        Optional<FuncionarioModels> funcionarioModels = funcionarioRepository.findById(id);
        if (funcionarioModels.isEmpty()) {
            throw new Exception("O Funcionario não existe");
        }
        funcionarioRepository.deleteById(id);
        // return
    }

}