package pro.sky.telegram_chat_zooShelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegram_chat_zooShelter.model.Pets;
import pro.sky.telegram_chat_zooShelter.repository.PetsRepository;
import pro.sky.telegram_chat_zooShelter.services.NotificationService;
import pro.sky.telegram_chat_zooShelter.services.PetsService;

import java.util.List;

@RequestMapping("/pet")
@RestController
@Tag(name = "\uD83D\uDC36 Pet ")

public class PetsController {
    private final PetsService petsService;

    public PetsController(PetsService petsService, NotificationService notificationService, PetsRepository petsRepository) {
        this.petsService = petsService;
    }
    @Operation(
            summary = "Получение списка всех животных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получение списка всех животных",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Pets.class))
                            )
                    )
            })
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/get-pets")
    public ResponseEntity<List<Pets>> getAllPets() {
        return ResponseEntity.ok(petsService.getPets());
    }
    @Operation(
            summary = "Поиск животного по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Поиск животного по id",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pets.class)
                            )
                    )
            })
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("{id}")
    public ResponseEntity <Pets> getPetById(
            @Parameter(description = "Поиск животного с данным id")
            @RequestParam(required = false, name = "номер животного") Long id) {
        Pets pet = petsService.findPetById(id);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(pet);
        }
    }

    @Operation(
            summary = "Заведение животного в базу",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Заведение животного в базу",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pets.class)
                            )
                    )
            })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity <Pets> createPet(@RequestBody (required = false) Pets pet){
        return ResponseEntity.ok(petsService.createPet(pet));
    }
    @Operation(
            summary = "Изменение животного в базе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Измененеие животного в базе",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pets.class)
                            )
                    )
            })
    @PutMapping
    public ResponseEntity <Pets> updatePet(@RequestBody Pets newPet) {
        Pets pet = petsService.updatePet(newPet);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(pet);
        }
    }

    @Operation(
            summary = "Удаление животного из базы",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удаление животного из базы",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pets.class)
                            )
                    )
            })
    @DeleteMapping("{id}")
    public ResponseEntity <Pets> removePet(
            @Parameter(description = "Удаление животного с данным id")
            @RequestParam (required = false, name = "номер животного") Long id) {
        Pets pet = petsService.deletePetById(id);
        if (pet == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(pet);
    }

}
