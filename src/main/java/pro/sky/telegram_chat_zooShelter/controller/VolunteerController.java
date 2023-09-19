package pro.sky.telegram_chat_zooShelter.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegram_chat_zooShelter.model.Volunteer;
import pro.sky.telegram_chat_zooShelter.services.VolunteerService;

import java.util.List;

@RestController
@RequestMapping("/volunteer")
@Tag(name = "\uD83D\uDC68\u200D⚕️ Volunteer", description = "")
public class VolunteerController {
    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Operation(
            summary = "Поиск всех волонтеров из базы данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found volunteers",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = List.class)
                            )
                    ),

            }
    )
    @GetMapping
    public ResponseEntity<List<Volunteer>> findAllVolunteers(){
        return ResponseEntity.ok(volunteerService.findAllVolunteers());
    }

    @Operation(
            summary = " Поиск волонтера по его ID в базе данных ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Volunteer JSON",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> findVolunteerById(@PathVariable long id) {
        Volunteer volunteer = volunteerService.findVolunteerById(id);
        if (volunteer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(volunteer);
    }
    
    @Operation(
            summary = "Создать волотера и добавить его в базу данных ",
            responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Volunteer JSON",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer){
        Volunteer createVolunteer = volunteerService.createVolunteer(volunteer);
        return ResponseEntity.ok(createVolunteer);
    }
    
    @Operation(
            summary = "Редактор волонтера",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Volunteer JSON",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            })
    @PutMapping
    public ResponseEntity<Volunteer> updateVolunteer(@RequestBody Volunteer volunteer){
        Volunteer update = volunteerService.updateVolunteer(volunteer);
        if (update == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update);
    }
    
    @Operation(
            summary = "Удаление волонтера по его ID в базе данных .(Таблица  - Волонтер)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Volunteer JSON",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Volunteer> deleteVolunteer(@PathVariable long id) {
        Volunteer volunteer = volunteerService.deleteVolunteer(id);
        if (volunteer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(volunteer);
    }

}

