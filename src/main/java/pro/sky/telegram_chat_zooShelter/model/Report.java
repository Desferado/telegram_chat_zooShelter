package pro.sky.telegram_chat_zooShelter.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Report { // Таблица: Отчет (Report) (о питомце)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // уникальный id
    private String petReport; // отчет текстовый: рацион, самочувствие, поведение питомца
    private LocalDateTime date; // дата сдачи отчета

    @ManyToOne
    @JoinColumn(name = "id_pets")
    private Pets pets; // id питомца (из таблицы Pets) (one-to-

    @OneToOne
    PhotoPet photoPet;

}
