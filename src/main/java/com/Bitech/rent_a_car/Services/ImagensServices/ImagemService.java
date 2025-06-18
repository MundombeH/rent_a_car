package com.Bitech.rent_a_car.Services.ImagensServices;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Bitech.rent_a_car.Dtos.Aluguer.CarroResumoDTO;
import com.Bitech.rent_a_car.Dtos.Imagens.ImagemReponseDTO;
import com.Bitech.rent_a_car.Dtos.Imagens.ImagemRequestDTO;
import com.Bitech.rent_a_car.Models.Aluguer.CarroModels;
import com.Bitech.rent_a_car.Models.ImagensModel.ImagemModels;
import com.Bitech.rent_a_car.Repository.Aluguer.CarroRepository;
import com.Bitech.rent_a_car.Repository.ImagensRepository.ImagemRepository;

@Service
public class ImagemService {
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/imagens/";

    private final ImagemRepository imagemRepository;
    private final CarroRepository carroRepository;

    public ImagemService(ImagemRepository imagemRepository, CarroRepository carroRepository) {
        this.imagemRepository = imagemRepository;
        this.carroRepository = carroRepository;
    }

    public List<ImagemReponseDTO> getAll() {
        List<ImagemModels> imagens = imagemRepository.findAll(); // <-- busca do banco

        return imagens.stream().map(imagem -> {
            ImagemReponseDTO dto = new ImagemReponseDTO();
            BeanUtils.copyProperties(imagem, dto);

            CarroModels carro = imagem.getCarro(); // <-- pega o carro da imagem

            if (carro != null) {
                CarroResumoDTO carroDTO = new CarroResumoDTO();
                carroDTO.setId(carro.getId());
                carroDTO.setCor(carro.getCor());
                carroDTO.setMatricula(carro.getMatricula());
                carroDTO.setNumeroDoChassi(carro.getNumeroDoChassi());
                carroDTO.setValorDaDiaria(carro.getValorDaDiaria() == null ? 0.00 : carro.getValorDaDiaria());
                dto.setCarro(carroDTO);

            }

            return dto;
        }).toList();
    }

    public List<ImagemReponseDTO> getImagensDeVeiculos(@Param(value = "CarroId") UUID id) {
        CarroModels carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        List<ImagemModels> imagens = imagemRepository.findByCarro(carro);

        List<ImagemReponseDTO> responseDtos = imagens.stream().map(imagem -> {
            ImagemReponseDTO dto = new ImagemReponseDTO();
            BeanUtils.copyProperties(imagem, dto);

            CarroResumoDTO carroDTO = new CarroResumoDTO();
            carroDTO.setId(carro.getId());
            carroDTO.setCor(carro.getCor());
            carroDTO.setMatricula(carro.getMatricula());
            carroDTO.setNumeroDoChassi(carro.getNumeroDoChassi());
            carroDTO.setValorDaDiaria(carro.getValorDaDiaria() == null ? 0.00 : carro.getValorDaDiaria());

            dto.setCarro(carroDTO);
            return dto;
        }).toList();

        return responseDtos;
    }

    public ImagemReponseDTO create(MultipartFile file, ImagemRequestDTO imagemDTO) throws IOException {
        ImagemModels imagem = new ImagemModels();

        CarroModels carro = carroRepository.findById(imagemDTO.getCarro())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        // Gerar nome único
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        System.out.println("Caminho absoluto COMPLETO onde a imagem será salva: "
                + Paths.get(UPLOAD_DIR).resolve(uniqueFileName).toAbsolutePath().toString());
        // Garantir que o diretório existe
        Path uploadPath = Paths.get(UPLOAD_DIR);
        Files.createDirectories(uploadPath);

        // Salvar imagem
        Path filePath = uploadPath.resolve(uniqueFileName);
        file.transferTo(filePath.toFile());

        // URL pública (ajuste conforme seu domínio real)
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/imagens/")
                .path(uniqueFileName)
                .toUriString();

        imagem.setUrl(fileDownloadUri);
        imagem.setCarro(carro);
        imagem.setImagem(originalFilename);
        imagem.setNomeUnico(uniqueFileName);

        ImagemModels save = imagemRepository.save(imagem);

        // Montar DTO de resposta
        CarroResumoDTO carroDTO = new CarroResumoDTO();
        carroDTO.setId(carro.getId());
        carroDTO.setCor(carro.getCor());
        carroDTO.setMatricula(carro.getMatricula());
        carroDTO.setNumeroDoChassi(carro.getNumeroDoChassi());
        carroDTO.setValorDaDiaria(carro.getValorDaDiaria() == null ? 0.00 : carro.getValorDaDiaria());

        ImagemReponseDTO dto = new ImagemReponseDTO();
        dto.setId(save.getId());
        dto.setImagem(save.getImagem());
        dto.setUrl(save.getUrl());
        dto.setNomeUnico(save.getNomeUnico());
        dto.setCarro(carroDTO);

        return dto;
    }

    @Transactional
    public void delete(UUID id) throws IOException {
        ImagemModels imagem = imagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagem não encontrada"));

        // Caminho do arquivo
        Path imagePath = Paths.get(UPLOAD_DIR).resolve(imagem.getNomeUnico());

        // Deletar fisicamente
        Files.deleteIfExists(imagePath);

        // Deletar no banco
        imagemRepository.deleteById(id);
    }

}
