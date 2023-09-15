package pro.sky.telegram_chat_zooShelter.services;

import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.model.Pets;
import pro.sky.telegram_chat_zooShelter.repository.PetsRepository;

import java.util.List;
@Service
public class PetsService {
    private final PetsRepository petsRepository;

    public PetsService(PetsRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

    public List<Pets> getPets() {
        return petsRepository.findAll();
    }

    public Pets findPetById(Long id) {
        return petsRepository.findById(id).orElse(null);
    }

    public Pets createPet(Pets pet) {
        return petsRepository.save(pet);
    }

    public Pets updatePet(Pets pet) {
        if (findPetById(pet.getId()) == null) {
            return null;
        } else {
            return petsRepository.save(pet);
        }
    }

    public Pets deletePetById(Long id) {
        Pets pet = findPetById(id);
        if (pet == null) {
            return null;
        } else {
            petsRepository.deleteById(id);
            return pet;
        }
    }
}

