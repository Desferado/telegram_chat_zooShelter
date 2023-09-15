package pro.sky.telegram_chat_zooShelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegram_chat_zooShelter.model.Shelters;
import pro.sky.telegram_chat_zooShelter.services.ShelterService;

import java.util.Collection;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }
    @Operation(
            summary = "Получение списка всех приютов",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получение списка всех приютов",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Shelters.class))
                            )
                    )
            })
    @GetMapping
    public ResponseEntity<Collection<Shelters>> findAllShelters() {
        Collection<Shelters> shelters = shelterService.findAll();
        return ResponseEntity.ok(shelters);
    }
    @Operation(
            summary = "Поиск приюта по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Поиск приюта по id",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelters.class)
                            )
                    )
            })
    @GetMapping("{id}")
    public ResponseEntity<Shelters> findShelterById(
            @Parameter(description = "Поиск приюта с данным id")
            @RequestParam (required = false, name = "номер приюта") Long id) {
        Shelters shelters = shelterService.findById(id);
        return ResponseEntity.ok(shelters);
    }
    @Operation(
            summary = "Добавление приюта в базу",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Добавление приюта в базу",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelters.class)
                            )
                    )
            })
    @PostMapping
    public ResponseEntity<Shelters> createShelter(@RequestBody Shelters shelters) {
        Shelters createdShelter = shelterService.create(shelters);
        return ResponseEntity.ok(createdShelter);
    }
    @Operation(
            summary = "Изменение приюта в базе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Изменение приюта в базе",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelters.class)
                            )
                    )
            })
    @PutMapping
    public ResponseEntity<Shelters> updateShelter(@RequestBody Shelters shelters) {
        Shelters updatedShelter = shelterService.update(shelters);
        return ResponseEntity.ok(updatedShelter);
    }
    @Operation(
            summary = "Удаление приюта из базы",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удаление приюта из базы",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Shelters.class)
                            )
                    )
            })
    @DeleteMapping("{id}")
    public void deleteShelter(
            @Parameter(description = "Удаление приюта с данным id")
            @RequestParam (required = false, name = "номер приюта") Long id) {
        shelterService.delete(id);
    }


}