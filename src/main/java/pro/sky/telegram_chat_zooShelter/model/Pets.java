package pro.sky.telegram_chat_zooShelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int age;
    private String name;
    private String type_pets;
    private String breed;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_shelter")
    private Shelters shelters;

    private LocalDateTime decisionDate; // дата принятия решения по усыновлению


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Pets pets = (Pets) o;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name, type_pets, breed, customer, shelters);
    }
}
