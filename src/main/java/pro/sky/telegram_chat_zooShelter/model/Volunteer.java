package pro.sky.telegram_chat_zooShelter.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Table(name = "volunteer")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String surname;
    private String name;
    private String secondName;
    private String phone;
    private String sex;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "volunteer_shelter",
            joinColumns = @JoinColumn(name = "volunteer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shelter_id", referencedColumnName = "id"))
    private Set<Shelters> shelters = new HashSet<>();


    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, surname, name, secondName, phone, sex, shelters);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Volunteer volunteer = (Volunteer) o;
        return id != null && Objects.equals(id, volunteer.id);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
