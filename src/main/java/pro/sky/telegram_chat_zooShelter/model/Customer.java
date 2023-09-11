package pro.sky.telegram_chat_zooShelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.Hibernate;
import lombok.*;
import java.util.Objects;


// Таблица: Пользователь (Customer) в БД

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // уникальный id
    private long chatId; // id Telegram чата
    private String surname; // фамилия
    private String name; // имя
    private String secondName; // отчество
    private String phone; // тлф формата +70000000000
    private String address; // адрес
    private String email; //почта
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

