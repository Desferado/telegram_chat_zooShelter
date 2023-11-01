package pro.sky.telegram_chat_zooShelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhotoPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private String fileName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_pets")
    private Pets pets;
    @OneToOne
    @JoinColumn(name = "id_report")
    private Report report;


    public void setId(Long id) {
        this.id = id;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoPet photoPet = (PhotoPet) o;
        return fileSize == photoPet.fileSize && id.equals(photoPet.id) && filePath.equals(photoPet.filePath) && mediaType.equals(photoPet.mediaType);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (int) (fileSize ^ (fileSize >>> 32));
        result = 31 * result + (mediaType != null ? mediaType.hashCode() : 0);
        result = 31 * result + (pets != null ? pets.hashCode() : 0);
        return result;
    }
}