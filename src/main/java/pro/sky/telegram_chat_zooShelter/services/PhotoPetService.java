package pro.sky.telegram_chat_zooShelter.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegram_chat_zooShelter.PhotoPetDTO;
import pro.sky.telegram_chat_zooShelter.component.PhotoPetReportMapper;
import pro.sky.telegram_chat_zooShelter.model.Pets;
import pro.sky.telegram_chat_zooShelter.model.PhotoPet;
import pro.sky.telegram_chat_zooShelter.repository.PhotoPetRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class PhotoPetService implements PhotoPetInterface{

    @Value("${path.to.avatars.folder}")
    private String coversDir;

    private final PetsService petsService;

    private final PhotoPetRepository photoPetRepository;
    private final PhotoPetReportMapper photoPetReportMapper;


    public PhotoPetService(PetsService petsService, PhotoPetRepository photoPetRepository, PhotoPetReportMapper photoPetReportMapper) {
        this.petsService = petsService;
        this.photoPetRepository = photoPetRepository;
        this.photoPetReportMapper = photoPetReportMapper;

    }

    public void uploadPhotoPet(Long petId, MultipartFile file) throws IOException {
        Pets pets = petsService.findPetById(petId);
        String nameFile = LocalDate.now() + " Фото питомца с id = " + petId
                + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Path.of(coversDir, nameFile);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

        PhotoPet photoPet = findPhotoPet(petId);
        photoPetRepository.save(photoPet);
        photoPet.setPets(pets);
        photoPet.setFilePath(filePath.toString());
        photoPet.setFileSize(file.getSize());
        photoPet.setMediaType(file.getContentType());
        photoPet.setFileName(nameFile);
        photoPetRepository.save(photoPet);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public PhotoPet findPhotoPet(Long petId) {
        return photoPetRepository.findPhotoPetByPets_Id(petId);
    }
    public List<PhotoPet> findAllByPets(Pets pets) {
        return photoPetRepository.findAllByPets(pets);
    }
    public PhotoPet findPhotoPetByReport_Id(Long reportId) {
        return photoPetRepository.findPhotoPetById(reportId).orElse(new PhotoPet());
    }

    @Override
    public List<PhotoPetDTO> getAllPhotoPet(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
        return photoPetRepository.findAll(pageRequest).getContent()
                .stream()
                .map(photoPetReportMapper::mappedToDTo)
                .collect(Collectors.toList());
    }

    public PhotoPet savePhotoReport(PhotoPet photoPet) {
        return photoPetRepository.save(photoPet);
    }
}
