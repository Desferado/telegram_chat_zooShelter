package pro.sky.telegram_chat_zooShelter.services;

import pro.sky.telegram_chat_zooShelter.PhotoPetDTO;
import pro.sky.telegram_chat_zooShelter.model.PhotoPet;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoPetInterface {
    void uploadPhotoPet(Long petId, MultipartFile avatarFile);

    PhotoPet findPhotoPet(Long petId);
    List<PhotoPetDTO> getAllPhotoPet(Integer pageNumber, Integer pageSize);
}
