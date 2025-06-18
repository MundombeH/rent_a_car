package com.Bitech.rent_a_car.Services.Aluguer;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.Bitech.rent_a_car.Dtos.Aluguer.AluguerRequestDTO;
import com.Bitech.rent_a_car.Dtos.Aluguer.AluguerResponseDTO;
import com.Bitech.rent_a_car.Models.Aluguer.AluguerModels;
import com.Bitech.rent_a_car.Models.Aluguer.ApoliceDeSeguroModels;
import com.Bitech.rent_a_car.Models.Aluguer.CarroModels;
import com.Bitech.rent_a_car.Models.Pessoa.MotoristaModels;
import com.Bitech.rent_a_car.Repository.Aluguer.AluguerRepository;
import com.Bitech.rent_a_car.Repository.Aluguer.ApoliceDeSeguroRepository;
import com.Bitech.rent_a_car.Repository.Aluguer.CarroRepository;
import com.Bitech.rent_a_car.Repository.Pessoa.MotoristaRepository;
import jakarta.transaction.Transactional;

@Service
public class AluguerService {

    private final MotoristaRepository motoristaRepository;

    private final AluguerRepository aluguerRepository;
    private final ApoliceDeSeguroRepository apoliceDeSeguroRepository;
    private final CarroRepository carroRepository;

    public AluguerService(AluguerRepository aluguerRepository, ApoliceDeSeguroRepository apoliceDeSeguroRepository,
            CarroRepository carroRepository, MotoristaRepository motoristaRepository) {
        this.aluguerRepository = aluguerRepository;
        this.apoliceDeSeguroRepository = apoliceDeSeguroRepository;
        this.carroRepository = carroRepository;
        this.motoristaRepository = motoristaRepository;
    }

    public List<AluguerModels> getAll() {
        return aluguerRepository.findAll();
    }

    public AluguerResponseDTO getOne(UUID id) {
        AluguerModels aluguer = aluguerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguer não encontrado"));

        var dto = new AluguerResponseDTO();
        dto.setId(aluguer.getId());
        dto.setDataDeDevolucao(aluguer.getDataDeDEvolicao());
        dto.setDataDeEntrega(aluguer.getDataDeEntrega());
        dto.setDataDoPedido(aluguer.getDataDoPedido());
        dto.setApolice(aluguer.getApolice());
        dto.setMotorista(aluguer.getMotorista());
        return dto;

    }

    @Transactional
    public AluguerResponseDTO create(AluguerRequestDTO aluguerRequestDTO) throws Exception {
        AluguerModels aluguer = new AluguerModels();

        if (aluguerRequestDTO.getDataDeDevolucao() == null) {
            throw new Exception("A data de Devolução do veiculo não pode se vazia!");
        }
        if (aluguerRequestDTO.getDataDeEntrega() == null) {
            throw new Exception("A data de entrega do veiculo não pode se vazia!");

        }
        if (aluguerRequestDTO.getDataDoPedido() == null) {
            throw new Exception("A data do pedido do veiculo não pode se vazia!");

        }
        long dias = ChronoUnit.DAYS.between(aluguerRequestDTO.getDataDeEntrega(),
                aluguerRequestDTO.getDataDeDevolucao());

        // Garantir que há pelo menos 1 dia de aluguel
        if (dias <= 0) {
            throw new Exception("A data de devolução deve ser após a data de entrega.");
        }
        ApoliceDeSeguroModels apolice = apoliceDeSeguroRepository.findById(aluguerRequestDTO.getApolice())
                .orElseThrow(() -> new Exception("Apolice de seguro nao encontrada!"));
        CarroModels carro = carroRepository.findById(aluguerRequestDTO.getCarro())
                .orElseThrow(() -> new Exception("Carro nao encontrada!"));

        double valorTotal = dias * carro.getValorDaDiaria();
        MotoristaModels motorista = motoristaRepository.findById(aluguerRequestDTO.getMotorista())
                .orElseThrow(() -> new Exception("Motorista não encontrado!"));

        // double valorTotal = (tempo / 86400) * carro.getValorDaDiaria();
        aluguer.setDataDeDEvolucao(aluguerRequestDTO.getDataDeDevolucao());
        aluguer.setDataDeEntrega(aluguerRequestDTO.getDataDeEntrega());
        aluguer.setDataDoPedido(aluguerRequestDTO.getDataDoPedido());
        aluguer.setValorTotal(valorTotal);
        // System.out.println("Valor Total: " + valorTotal);
        aluguer.setApolice(apolice);
        aluguer.setCarro(carro);
        aluguer.setMotorista(motorista);
        AluguerModels salvar = aluguerRepository.save(aluguer);

        var dto = new AluguerResponseDTO();
        dto.setId(salvar.getId());
        dto.setDataDeDevolucao(salvar.getDataDeDEvolicao());
        dto.setDataDeEntrega(salvar.getDataDeEntrega());
        dto.setDataDoPedido(salvar.getDataDoPedido());
        dto.setValorTotal(salvar.getValorTotal());
        dto.setApolice(salvar.getApolice());
        dto.setCarro(salvar.getCarro());
        dto.setMotorista(salvar.getMotorista());

        return dto;
    }

    @Transactional
    public AluguerResponseDTO update(UUID id, AluguerRequestDTO aluguerRequestDTO) throws Exception {
        AluguerModels aluguer = aluguerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguer não encontrado"));

        if (aluguerRequestDTO.getDataDeDevolucao() == null) {
            throw new Exception("A data de Devolução do veiculo não pode se vazia!");
        }
        if (aluguerRequestDTO.getDataDeEntrega() == null) {
            throw new Exception("A data de entrega do veiculo não pode se vazia!");

        }
        if (aluguerRequestDTO.getDataDoPedido() == null) {
            throw new Exception("A data do pedido do veiculo não pode se vazia!");

        }
        long dias = ChronoUnit.DAYS.between(aluguerRequestDTO.getDataDeEntrega(),
                aluguerRequestDTO.getDataDeDevolucao());

        // Garantir que há pelo menos 1 dia de aluguel
        if (dias <= 0) {
            throw new Exception("A data de devolução deve ser após a data de entrega.");
        }
        ApoliceDeSeguroModels apolice = apoliceDeSeguroRepository.findById(aluguerRequestDTO.getApolice())
                .orElseThrow(() -> new Exception("Apolice de seguro nao encontrada!"));
        CarroModels carro = carroRepository.findById(aluguerRequestDTO.getCarro())
                .orElseThrow(() -> new Exception("Carro nao encontrada!"));

        double valorTotal = dias*carro.getValorDaDiaria();
        MotoristaModels motorista = motoristaRepository.findById(aluguerRequestDTO.getMotorista())


                .orElseThrow(() -> new Exception("Motorista não encontrado!"));
        aluguer.setDataDeDEvolucao(aluguerRequestDTO.getDataDeDevolucao());
        aluguer.setDataDeEntrega(aluguerRequestDTO.getDataDeEntrega());
        aluguer.setDataDoPedido(aluguerRequestDTO.getDataDoPedido());
        aluguer.setValorTotal(valorTotal);
        aluguer.setApolice(apolice);
        aluguer.setCarro(carro);
        aluguer.setMotorista(motorista);
        AluguerModels salvar = aluguerRepository.save(aluguer);

        var dto = new AluguerResponseDTO();
        dto.setId(salvar.getId());
        dto.setDataDeDevolucao(salvar.getDataDeDEvolicao());
        dto.setDataDeEntrega(salvar.getDataDeEntrega());
        dto.setDataDoPedido(salvar.getDataDoPedido());
        dto.setValorTotal(salvar.getValorTotal());
        dto.setApolice(salvar.getApolice());
        dto.setCarro(salvar.getCarro());
        dto.setMotorista(salvar.getMotorista());

        return dto;

    }

    @Transactional
    public void delete(UUID id) throws Exception {
        Optional<AluguerModels> aluguer = aluguerRepository.findById(id);
        if (!aluguer.isPresent()) {
            throw new Exception("Aluguer não encontrado!");
        }
        aluguerRepository.deleteById(id);
    }
}
