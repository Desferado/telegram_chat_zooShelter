package pro.sky.telegram_chat_zooShelter.controller;

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

    @GetMapping
    public ResponseEntity<Collection<Shelters>> findAll() {
        Collection<Shelters> shelters = shelterService.findAll();
        return ResponseEntity.ok(shelters);
    }

    @GetMapping("{id}")
    public ResponseEntity<Shelters> findById(@PathVariable Long id) {
        Shelters shelters = shelterService.findById(id);
        return ResponseEntity.ok(shelters);
    }

    @PostMapping
    public ResponseEntity<Shelters> create(@RequestBody Shelters shelters) {
        Shelters createdShelter = shelterService.create(shelters);
        return ResponseEntity.ok(createdShelter);
    }

    @PutMapping
    public ResponseEntity<Shelters> update(@RequestBody Shelters shelters) {
        Shelters updatedShelter = shelterService.update(shelters);
        return ResponseEntity.ok(updatedShelter);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        shelterService.delete(id);
    }


}