package pro.sky.telegram_chat_zooShelter;

import lombok.Getter;

import java.util.Objects;

@Getter
public class PhotoPetDTO {
    private final String filePath;
    private final long fileSize;
    private final String mediaType;
    private final long reortId;

    public PhotoPetDTO(String filePath, long fileSize, String mediaType, long reortId) {
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.reortId = reortId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoPetDTO that)) return false;
        return fileSize == that.fileSize && reortId == that.reortId && Objects.equals(filePath, that.filePath) && Objects.equals(mediaType, that.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filePath, fileSize, mediaType, reortId);
    }
}
