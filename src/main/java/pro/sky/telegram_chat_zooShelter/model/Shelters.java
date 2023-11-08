package pro.sky.telegram_chat_zooShelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;


import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Shelters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    private String name;
    private String adress;
    private String location;
    @JsonIgnore
    @OneToMany(mappedBy =  "shelters")
    List<Pets> petsList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Shelters shelters = (Shelters) o;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, adress, location);
    }

    @Override
    public String toString() {
        return "Shelters{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + adress + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

}
