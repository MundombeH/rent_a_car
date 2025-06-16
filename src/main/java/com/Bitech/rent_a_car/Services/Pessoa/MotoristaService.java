package com.Bitech.rent_a_car.Services.Pessoa;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.Bitech.rent_a_car.Dtos.Pessoa.MotoristaRequestDTO;
import com.Bitech.rent_a_car.Dtos.Pessoa.MotoristaResponseDTO;
import com.Bitech.rent_a_car.Models.Aluguer.AluguerModels;
import com.Bitech.rent_a_car.Models.Pessoa.MotoristaModels;
import com.Bitech.rent_a_car.Repository.Aluguer.AluguerRepository;
import com.Bitech.rent_a_car.Repository.Aluguer.ApoliceDeSeguroRepository;
import com.Bitech.rent_a_car.Repository.Pessoa.MotoristaRepository;

import jakarta.transaction.Transactional;

@Service
public class MotoristaService {
    private final MotoristaRepository motoristaRepository;

    private final AluguerRepository aluguerRepository;


    public MotoristaService(MotoristaRepository motoristaRepository, AluguerRepository aluguerRepository,
            ApoliceDeSeguroRepository apoliceDeSeguroRepository) {
        this.motoristaRepository = motoristaRepository;
        this.aluguerRepository = aluguerRepository;
    }

    public List<MotoristaModels> getAll() {
        return motoristaRepository.findAll();
    }

    // Metodo para buscar pelo Id e
    public MotoristaResponseDTO getOne(UUID id) {
        MotoristaModels motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado"));

        MotoristaResponseDTO response = new MotoristaResponseDTO();
        response.setId(motorista.getId());
        response.setNome(motorista.getNome());
        response.setSobreNome(motorista.getSobreNome());
        response.setBi(motorista.getBi());
        response.setDataDeNascimento(motorista.getDataDeNascimento());
        response.setCartaDeConducao(motorista.getCartaDeConducao());
        response.setGenero(motorista.getGenero());
        response.setAluguer(motorista.getAluguer());

        return response;
    }

    // Metodo para cadastrar'
    @Transactional
    public MotoristaResponseDTO create(MotoristaRequestDTO motoristaRequestDTO) {

        if (motoristaRequestDTO.getNome() == null || motoristaRequestDTO.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        if (motoristaRequestDTO.getSobreNome() == null || motoristaRequestDTO.getSobreNome().isBlank()) {
            throw new IllegalArgumentException("O sobre nome não pode ser vazio");
        }
        if (motoristaRequestDTO.getBi() == null || motoristaRequestDTO.getBi().isBlank()) {
            throw new IllegalArgumentException("O Bilhete de identidade não pode ser vazio");
        }
        if (!motoristaRequestDTO.getBi().matches("^[0-9]{9}[A-Z]{2}[0-9]{3}$")) {
            throw new IllegalArgumentException("Formato de BI inválido (ex: 004748583LA044)");
        }
        if (motoristaRequestDTO.getBi().length() != 14) {
            throw new IllegalArgumentException("O Bilhete de identidade deve conter 14 caracteres!");
        }
        if (motoristaRequestDTO.getDataDeNascimento() == null) {
            throw new IllegalArgumentException("A data de nascimento é obrigatória");
        }

        if (motoristaRequestDTO.getDataDeNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode estar no futuro");
        }

        int idade = Period.between(motoristaRequestDTO.getDataDeNascimento(), LocalDate.now()).getYears();
        if (idade < 18) {
            throw new IllegalArgumentException("O motorista deve ter no mínimo 18 anos");
        }

        if (motoristaRequestDTO.getCartaDeConducao() == null) {
            throw new IllegalArgumentException("A Carta Condução é obrigatória");
        }

        Set<AluguerModels> alugueres = new HashSet<>(aluguerRepository.findAllById(motoristaRequestDTO.getAluguer())).stream().collect(Collectors.toSet());
        MotoristaModels motoristaModels = new MotoristaModels();

        motoristaModels.setNome(motoristaRequestDTO.getNome());
        motoristaModels.setSobreNome(motoristaRequestDTO.getSobreNome());
        motoristaModels.setBi(motoristaRequestDTO.getBi());
        motoristaModels.setDataDeNascimento(motoristaRequestDTO.getDataDeNascimento());
        motoristaModels.setCartaDeConducao(motoristaRequestDTO.getCartaDeConducao());
        motoristaModels.setAluguer(alugueres);
        motoristaModels.setGenero(motoristaRequestDTO.getGenero());

        MotoristaModels save = motoristaRepository.save(motoristaModels);

        MotoristaResponseDTO response = new MotoristaResponseDTO();
        response.setId(save.getId());
        response.setNome(save.getNome());
        response.setSobreNome(save.getSobreNome());
        response.setBi(save.getBi());
        response.setDataDeNascimento(save.getDataDeNascimento());
        response.setCartaDeConducao(save.getCartaDeConducao());
        response.setGenero(save.getGenero());
        response.setAluguer(save.getAluguer());
        System.out.println("Aluguer Dados 1: " + response.getAluguer());
        System.out.println("Aluguer Dados 2: " + save.getAluguer());
        return response;

    }

    @Transactional
    public MotoristaResponseDTO update(UUID id,
            MotoristaRequestDTO motoristaRequestDTO) {
        // Busca o motorista existente
        MotoristaModels motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("motorista não encontrado"));

        // Validação simples (exemplo)
        if (motoristaRequestDTO.getNome() == null || motoristaRequestDTO.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        if (motoristaRequestDTO.getSobreNome() == null || motoristaRequestDTO.getSobreNome().isBlank()) {
            throw new IllegalArgumentException("O sobre nome não pode ser vazio");
        }
        if (motoristaRequestDTO.getBi() == null || motoristaRequestDTO.getBi().isBlank()) {
            throw new IllegalArgumentException("O Bilhete de identidade não pode ser vazio");
        }
        if (!motoristaRequestDTO.getBi().matches("^[0-9]{9}[A-Z]{2}[0-9]{3}$")) {
            throw new IllegalArgumentException("Formato de BI inválido (ex: 004748583LA044)");
        }
        if (motoristaRequestDTO.getBi().length() != 14) {
            throw new IllegalArgumentException("O Bilhete de identidade deve conter 14 caracteres!");
        }
        if (motoristaRequestDTO.getDataDeNascimento() == null) {
            throw new IllegalArgumentException("A data de nascimento é obrigatória");
        }

        if (motoristaRequestDTO.getDataDeNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode estar no futuro");
        }

        int idade = Period.between(motoristaRequestDTO.getDataDeNascimento(), LocalDate.now()).getYears();
        if (idade < 18) {
            throw new IllegalArgumentException("O motorista deve ter no mínimo 18 anos");
        }

        if (motoristaRequestDTO.getCartaDeConducao() == null) {
            throw new IllegalArgumentException("A Carta de condução é obrigatória");
        }

        // Atualiza os campos
        motorista.setId(id);
        motorista.setNome(motoristaRequestDTO.getNome());
        motorista.setSobreNome(motoristaRequestDTO.getSobreNome());
        motorista.setBi(motoristaRequestDTO.getBi());
        motorista.setDataDeNascimento(motoristaRequestDTO.getDataDeNascimento());
        motorista.setCartaDeConducao(motoristaRequestDTO.getCartaDeConducao());
        // motorista.set

        // Salva no banco
        MotoristaModels save = motoristaRepository.save(motorista);

        // Mapeia para Response DTO (você pode usar MapStruct para isso)
        MotoristaResponseDTO response = new MotoristaResponseDTO();
        response.setId(save.getId());
        response.setNome(save.getNome());
        response.setSobreNome(save.getSobreNome());
        response.setBi(save.getBi());
        response.setDataDeNascimento(save.getDataDeNascimento());
        response.setCartaDeConducao(save.getCartaDeConducao());

        return response;
    }

    @Transactional
    public void deleteMotorista(UUID id) throws Exception {

        Optional<MotoristaModels> motoristaModel = motoristaRepository.findById(id);
        if (motoristaModel.isEmpty()) {
            throw new Exception("O Motorista não existe");
        }
        motoristaRepository.deleteById(id);
        // return
    }

}
