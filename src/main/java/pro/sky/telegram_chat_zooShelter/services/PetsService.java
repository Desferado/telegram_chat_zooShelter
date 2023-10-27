package pro.sky.telegram_chat_zooShelter.services;

import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.model.Pets;
import pro.sky.telegram_chat_zooShelter.repository.PetsRepository;

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
     * @param customer record Customer
     * @return list of "Pet" where customer field is <b>{@code customer}</b>
     * @see PetsRepository#findPetsByCustomer(Customer)
     */
    public List<Pets> findPetsByCustomer(Customer customer) {
        return petsRepository.findPetsByCustomer(customer);
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
        return pet;
    }
    public Pets deletePetProbation(Long id) {
        Pets pet = findPetById(id);
        pet.setDecisionDate(null);
        return pet;
    }

}

