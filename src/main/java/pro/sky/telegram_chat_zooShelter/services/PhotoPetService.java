package pro.sky.telegram_chat_zooShelter.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegram_chat_zooShelter.model.Pets;
import pro.sky.telegram_chat_zooShelter.model.PhotoPet;
import pro.sky.telegram_chat_zooShelter.repository.PhotoPetRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class PhotoPetService {

    @Value("${path.to.avatars.folder}")
    private String coversDir;

    private final PetsService petsService;
    private final PhotoPetRepository photoPetRepository;

    public PhotoPetService(PetsService petsService, PhotoPetRepository photoPetRepository) {
        this.petsService = petsService;
        this.photoPetRepository = photoPetRepository;
    }

    public void uploadPhotoPet(Long petId, MultipartFile file) throws IOException {
        Pets pets = petsService.findPetById(petId);

        Path filePath = Path.of(coversDir, petId + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        PhotoPet photoPet = findPhotoPet(petId);
        photoPetRepository.save(photoPet);
        photoPet.setPets(pets);
        photoPet.setFilePath(filePath.toString());
        photoPet.setFileSize(file.getSize());
        photoPet.setMediaType(file.getContentType());

        photoPetRepository.save(photoPet);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public PhotoPet findPhotoPet(Long petId) {
        return photoPetRepository.findPhotoPetById(petId).orElse(new PhotoPet());
    }

    public PhotoPet savePhotoReport(PhotoPet photoPet) {
        return photoPetRepository.save(photoPet);
    }
}
