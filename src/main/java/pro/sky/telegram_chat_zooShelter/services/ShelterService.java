package pro.sky.telegram_chat_zooShelter.services;

import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.model.Shelters;
import pro.sky.telegram_chat_zooShelter.repository.ShelterRepository;

import java.util.List;

@Service
public class ShelterService {
    private final ShelterRepository shelterRepository;

    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    public List<Shelters> findAll() {
        return shelterRepository.findAll();
    }

    public Shelters findById(Long id) {
        return shelterRepository.findById(id).orElse(null);
    }

    public Shelters create(Shelters shelters) {
        return shelterRepository.save(shelters);
    }

    public Shelters update(Shelters shelters) {
        return (findById(shelters.getId()) != null) ? shelterRepository.save(shelters) : null;
    }

    public Shelters delete(Long id) {
        Shelters shelters = findById(id);
        if (shelters != null) {
            shelterRepository.delete(shelters);
        }
        return shelters;
    }
}
