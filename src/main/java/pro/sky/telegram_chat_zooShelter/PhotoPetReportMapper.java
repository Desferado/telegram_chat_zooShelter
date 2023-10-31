package pro.sky.telegram_chat_zooShelter;

import org.springframework.stereotype.Component;
import pro.sky.telegram_chat_zooShelter.model.PhotoPet;

@Component
public class PhotoPetReportMapper {
    public PhotoPetDTO mappedToDTo(PhotoPet photoPet){
        return new PhotoPetDTO(
                photoPet.getFilePath(),
                photoPet.getFileSize(),
                photoPet.getMediaType(),
                photoPet.getReport().getId()
        );
    }
}
