package pro.sky.telegram_chat_zooShelter.services;

import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.model.Pets;
import pro.sky.telegram_chat_zooShelter.repository.PetsRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    /**
     * @param status record String
     * @return list of "Pet" where status field is <b>{@code status}</b>
     * @see PetsRepository#findAllByProbationStatusContainsIgnoreCase(String status)
     */
    public List<Pets> findAllByProbationStatusContainsIgnoreCase(String status) {
        return petsRepository.findAllByProbationStatusContainsIgnoreCase(status);
    }
    /**
     * Список питомцев, для которых назначен усыновитель
     *
     * @return список питомцев
     */
    public List<Pets> findPetsWithCustomer() {
        return petsRepository.findPetsByCustomerNotNull();
    }
    public Pets setPetProbation(Long id) {
        Pets pet = findPetById(id);
        pet.setDecisionDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        pet.setProbationStatus("в процессе");
        updatePet(pet);
        return pet;
    }
    public Pets deletePetProbation(Long id) {
        Pets pet = findPetById(id);
        pet.setDecisionDate(null);
        pet.setProbationStatus(null);
        pet.setLimit_probation(null);
        updatePet(pet);
        return pet;
    }
    public LocalDate getDateProbation(Long id){
        return findPetById(id).getDecisionDate();

    }
    public String addPetProbation (Long id){
        return findPetById(id).getProbationStatus();
    }
    public Pets setAddDays(int addDays, Long id){
        Pets pet = findPetById(id);
        if (pet.getLimit_probation() == null){
            pet.setLimit_probation(0);
        }
        Integer limit = pet.getLimit_probation();
        limit = limit + addDays;
        pet.setLimit_probation(limit);
        updatePet(pet);
        return pet;
    }
}

