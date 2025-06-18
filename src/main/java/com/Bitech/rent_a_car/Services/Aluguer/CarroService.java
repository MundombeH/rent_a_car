package com.Bitech.rent_a_car.Services.Aluguer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.Bitech.rent_a_car.Dtos.Aluguer.CarroRequestDto;
import com.Bitech.rent_a_car.Dtos.Aluguer.CarroResponseDTO;
import com.Bitech.rent_a_car.Models.Aluguer.CarroModels;
import com.Bitech.rent_a_car.Models.Aluguer.ModeloModels;
import com.Bitech.rent_a_car.Repository.Aluguer.CarroRepository;
import com.Bitech.rent_a_car.Repository.Aluguer.ModeloRepository;

import jakarta.transaction.Transactional;

@Service
public class CarroService {

    
    private final CarroRepository carroRepository;
    private final ModeloRepository modeloRepository;

    public CarroService(CarroRepository carroRepository, ModeloRepository modeloRepository) {
        this.carroRepository = carroRepository;
        this.modeloRepository = modeloRepository;
    }

    public List<CarroModels> getAll() {
        return carroRepository.findAll();
    }

    public CarroResponseDTO getOne(UUID id) {
        CarroModels carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));

        var dto = new CarroResponseDTO();
        dto.setId(carro.getId());
        dto.setNumeroDoChassi(carro.getNumeroDoChassi());
        dto.setMatricula(carro.getMatricula());
        dto.setValorDaDiaria(carro.getValorDaDiaria());
        dto.setCor(carro.getCor());
        dto.setModelo(carro.getModelo());
        dto.setAcessorios(carro.getAcessorios());
        dto.setAluguer(carro.getAluguer());
        dto.setImagem(carro.getImagem());

        return dto;

    }

    @Transactional
    public CarroResponseDTO postCarro(CarroRequestDto carroRequestDto) throws Exception {
        CarroModels carro = new CarroModels();

        if (carroRequestDto.getNumeroDoChassi() == null) {
            throw new Exception("O número do Veiculo não pode ser vazia");

        }
        if (carroRequestDto.getMatricula() == null) {
            throw new Exception("A placa do Veiculo não pode ser vazia");

        }
        if (carroRequestDto.getValorDaDiaria() < 0) {
            throw new Exception("O valor da diária do Veiculo não pode ser negativo");

        }
        if (Double.isNaN(carroRequestDto.getValorDaDiaria())) {
            throw new Exception("O valor da diária do Veiculo Tem de ser um número! ");

        }
        if (carroRequestDto.getValorDaDiaria() == null) {
            carro.setValorDaDiaria(0.00);
        }
        if (carroRequestDto.getCor() == null) {
            throw new Exception("A cor do Veiculo não pode ser vazia");

        }
        ModeloModels modelo = modeloRepository.findById(carroRequestDto.getModelo()).orElseThrow(() -> new RuntimeException("Modelo não encontrado"));
        carro.setValorDaDiaria(carroRequestDto.getValorDaDiaria());
        carro.setCor(carroRequestDto.getCor());
        carro.setMatricula(carroRequestDto.getMatricula());
        carro.setNumeroDoChassi(carroRequestDto.getNumeroDoChassi());
        carro.setModelo(modelo);
        CarroModels salvar = carroRepository.save(carro);

        var dto = new CarroResponseDTO();
        dto.setId(salvar.getId());
        dto.setNumeroDoChassi(salvar.getNumeroDoChassi());
        dto.setMatricula(salvar.getMatricula());
        dto.setValorDaDiaria(salvar.getValorDaDiaria());
        dto.setModelo(modelo);
        dto.setCor(salvar.getCor());
        System.out.println("Valor da Diaria:" + carroRequestDto.getValorDaDiaria());
        return dto;

    }

    @Transactional
    public CarroResponseDTO updateCarro(UUID id, CarroRequestDto carroRequestDto) throws Exception {

        CarroModels carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        if (carroRequestDto.getNumeroDoChassi() == null) {
            throw new Exception("O número do Veiculo não pode ser vazia");

        }
        if (carroRequestDto.getMatricula() == null) {
            throw new Exception("A placa do Veiculo não pode ser vazia");

        }
        if (carroRequestDto.getValorDaDiaria() < 0) {
            throw new Exception("O valor da diária do Veiculo não pode ser negativo!");

        }

        if (Double.isNaN(carroRequestDto.getValorDaDiaria())) {
            throw new Exception("O valor da diária do Veiculo Tem de ser um número! ");

        }
        if (carroRequestDto.getValorDaDiaria() == null) {
            System.out.println("Valor da diaria");
            carro.setValorDaDiaria(0.00);
        }
        if (carroRequestDto.getCor() == null) {
            throw new Exception("A cor do Veiculo não pode ser vazia");

        }
         ModeloModels modelo = modeloRepository.findById(carroRequestDto.getModelo()).orElseThrow(() -> new RuntimeException("Modelo não encontrado"));
        carro.setValorDaDiaria(carroRequestDto.getValorDaDiaria());
        carro.setCor(carroRequestDto.getCor());
        carro.setMatricula(carroRequestDto.getMatricula());
        carro.setNumeroDoChassi(carroRequestDto.getNumeroDoChassi());
        carro.setModelo(modelo);
        CarroModels salvar = carroRepository.save(carro);

        var dto = new CarroResponseDTO();
        dto.setId(salvar.getId());
        dto.setNumeroDoChassi(salvar.getNumeroDoChassi());
        dto.setMatricula(salvar.getMatricula());
        dto.setValorDaDiaria(salvar.getValorDaDiaria());
        dto.setModelo(modelo);
        dto.setCor(salvar.getCor());
        System.out.println("Valor da Diaria:" + carroRequestDto.getValorDaDiaria());
        return dto;
    }

    @Transactional
    public void deleteCarro(UUID id) throws Exception {
        Optional<CarroModels> carro = carroRepository.findById(id);
        if (!carro.isPresent()) {
            throw new Exception("O Veiculo Selecionado não foi encontrado!");
        }
        carroRepository.deleteById(id);
    }
}
