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
import pro.sky.telegram_chat_zooShelter.services.PetsService;

import java.time.LocalDate;


@RequestMapping("/probation")
@RestController
@Tag(name = "\uD83D\uDC36 probation ")
public class ProbationController {
    private final PetsService petsService;

    public ProbationController(PetsService petsService) {
        this.petsService = petsService;
    }

    @Operation(
            summary = "Ввывод даты начала испытательного срока животного по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ввывод даты начала испытательного срока животного по id",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pets.class)
                            )
                    )
            })
    @GetMapping("{id}")
    public ResponseEntity <LocalDate> getDateProbationById(
            @Parameter(description = "Ввывод даты начала испытательного срока животного по id")
           @RequestParam(required = false, name = "номер животного") Long id) {
            LocalDate localDate = petsService.getDateProbation(id);
            if (localDate == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(localDate);
            }
        }
    @Operation(
            summary = "Установка испытательного срока для владельца животного",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Установка испытательного срока для владельца животного",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pets.class)
                            )
                    )
            })
    @PutMapping("{id}")
    public ResponseEntity<Pets> setPetProbation(
            @Parameter(description = "Установка испытательного срока для владельца животного по его id")
            @RequestParam(required = false, name = "номер животного") Long id) {
        Pets pet = petsService.setPetProbation(id);
        petsService.setAddDays(30, id);
        if (pet.getDecisionDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(pet);
    }
    @Operation(
            summary = "Увеличение испытательного срока для владельца животного",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Увеличение испытательного срока для владельца животного",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pets.class)
                            )
                    )
            })
    @PutMapping("/addDays/{id}")
    public ResponseEntity<Pets> addPetProbation(
            @Parameter(description = "Установка испытательного срока для владельца животного по его id")
            @RequestParam(required = false, name = "номер животного") Long id,
            @RequestParam(required = false, name = "количество добавляемых дней 14 или 30") int days) {
        Pets pet = petsService.setAddDays(days, id);
        if (pet.getDecisionDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(pet);
    }
    @Operation(
            summary = "Удаление испытательного срока для владельца животного",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удаление испытательного срока для владельца животного",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pets.class)
                            )
                    )
            })
    @DeleteMapping("{id}")
    public ResponseEntity <Pets> deletePetProbation(
            @Parameter(description = "Удаление испытательного срока для владельца животного по его id")
            @RequestParam (required = false, name = "номер животного") Long id) {
        Pets pet = petsService.deletePetProbation(id);
        if (pet.getDecisionDate() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(pet);
    }


}
