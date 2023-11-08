package pro.sky.telegram_chat_zooShelter.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegram_chat_zooShelter.PhotoPetDTO;
import pro.sky.telegram_chat_zooShelter.model.PhotoPet;
import pro.sky.telegram_chat_zooShelter.repository.PhotoPetRepository;
import pro.sky.telegram_chat_zooShelter.services.PhotoPetService;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;

@RestController
@RequestMapping("photo_pet")
public class PhotoPetController {

    private final PhotoPetService photoPetService;
    @Getter
    private final PhotoPetRepository photoPetRepository;

    public PhotoPetController(PhotoPetService photoPetService, PhotoPetRepository photoPetRepository) {
        this.photoPetService = photoPetService;
        this.photoPetRepository = photoPetRepository;
    }
    @Operation(
            summary = "Загрузка фотографии в таблицу",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Загрузка фотографии в таблицу",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PhotoPet.class)
                            )
                    )
            })
    @PostMapping(value = "report/{petId}/photoPet", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> savePhoto(@PathVariable Long petId,
                                               @RequestParam MultipartFile photoPet)  {
        if (photoPet == null || photoPet.getSize() > 1024 * 1024){
            return ResponseEntity.badRequest().body("File is not correct.");
        }
        photoPetService.uploadPhotoPet(petId, photoPet);
        return ResponseEntity.ok().build();
    }
@Operation(
        summary = "Получение фотографии из базы",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Получение фотографии из базы",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = PhotoPet.class)
                        )
                )
        })
    @GetMapping(value = "/{reportId}/photoPet")
    public void downloadPhotoPet(@PathVariable Long reportId, HttpServletResponse response) throws IOException{
        PhotoPet photoPet = photoPetService.findPhotoPetByReport_Id(reportId);
        Path path = Path.of(photoPet.getFilePath());
        try(InputStream is = Files.newInputStream(path);
            OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(photoPet.getMediaType());
            response.setContentLength((int) photoPet.getFileSize());
            is.transferTo(os);
        }
    }

    @GetMapping
    public ResponseEntity<List<PhotoPetDTO>> getAllPhotoPet(
            @RequestParam("page") Integer pageNumber,
            @RequestParam("size") Integer pageSize ){
        List<PhotoPetDTO> avatars = photoPetService.getAllPhotoPet(pageNumber,pageSize);
        return ResponseEntity.ok(avatars);
    }
}