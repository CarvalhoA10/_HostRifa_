package com.host.hostRifas.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.host.hostRifas.helpers.requests.RaffleRequest;
import com.host.hostRifas.services.raffle.RaffleService;

@RestController
@RequestMapping("raffle")
public class RaffleController {

    @Value("${upload.dir:uploads}")
    private String uploadDir;

    private RaffleService raffleService;

    public RaffleController(RaffleService raffleService){
        this.raffleService = raffleService;
    }
    
    @PostMapping("create")
    public ResponseEntity<String> createRaffle(@RequestPart("raffle") RaffleRequest request, @RequestPart("raffleImage") MultipartFile raffleImage, @RequestPart("prizeImage") MultipartFile prizeImage, @AuthenticationPrincipal UserDetails userDetails) throws IOException{
        
        Files.createDirectories(Paths.get(uploadDir));

        String raffleImageName = UUID.randomUUID() + "_" + StringUtils.cleanPath(raffleImage.getOriginalFilename());
        String prizeImageName = UUID.randomUUID() + "_" + StringUtils.cleanPath(prizeImage.getOriginalFilename());

        Path raffleImagePath = Paths.get(uploadDir).resolve(raffleImageName);
        Path prizeImagePath = Paths.get(uploadDir).resolve(prizeImageName);

        Files.copy(raffleImage.getInputStream(), raffleImagePath, StandardCopyOption.REPLACE_EXISTING);
        Files.copy(prizeImage.getInputStream(), prizeImagePath, StandardCopyOption.REPLACE_EXISTING);

        request.setRaffleImagePath(raffleImagePath.toString());
        request.setPrizeImagePath(prizeImagePath.toString());

        // Falta implementar os metodo de service para salvar no banco de dados
        String username = userDetails.getUsername();

        this.raffleService.insertRaffle(request, username, raffleImagePath.toString(), prizeImagePath.toString());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Criado com sucesso");
    }

    @GetMapping("/imagem")
    public ResponseEntity<byte[]> getImagem(@RequestParam String path) throws IOException {
        File arquivo = new File(path);

        if (!arquivo.exists()) {
            return ResponseEntity.notFound().build();
        }

        byte[] imagemBytes = Files.readAllBytes(arquivo.toPath());

        String contentType = Files.probeContentType(arquivo.toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(imagemBytes);
    }

}
