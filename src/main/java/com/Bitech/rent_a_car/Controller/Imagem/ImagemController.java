package com.Bitech.rent_a_car.Controller.Imagem;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Bitech.rent_a_car.Dtos.Imagens.ImagemReponseDTO;
import com.Bitech.rent_a_car.Dtos.Imagens.ImagemRequestDTO;
import com.Bitech.rent_a_car.Services.ImagensServices.ImagemService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("imagem")
public class ImagemController {
    private final ImagemService imagemService;

    public ImagemController(ImagemService imagemService) {
        this.imagemService = imagemService;
    }

    @GetMapping("")
    public ResponseEntity<List<ImagemReponseDTO>> getImagem() {
        var responseDtos = imagemService.getAll();
        return ResponseEntity.status(200).body(responseDtos);
    }

    @GetMapping("/veiculos")
    public ResponseEntity<List<ImagemReponseDTO>> getPorVeiculos(@Param(value = "CarroId") UUID id) {

        List<ImagemReponseDTO> dto = imagemService.getImagensDeVeiculos(id);

        return ResponseEntity.status(200).body(dto);

    }
    

    @PostMapping("")
    public ResponseEntity<ImagemReponseDTO> uploadImagem(
            @RequestParam("file") MultipartFile file,
            @ModelAttribute ImagemRequestDTO imagemDTO) throws IOException {

        ImagemReponseDTO dto = imagemService.create(file, imagemDTO);
        return ResponseEntity.status(201).body(dto);
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImagem(@PathVariable UUID id) throws IOException {
        imagemService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
