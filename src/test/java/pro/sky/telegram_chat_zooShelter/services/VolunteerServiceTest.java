package pro.sky.telegram_chat_zooShelter.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.telegram_chat_zooShelter.model.Shelters;
import pro.sky.telegram_chat_zooShelter.model.Volunteer;
import pro.sky.telegram_chat_zooShelter.repository.VolunteerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {VolunteerService.class})
@ExtendWith(SpringExtension.class)
class VolunteerServiceTest {
    @MockBean
    private VolunteerRepository volunteerRepository;

    @Autowired
    private VolunteerService volunteerService;

    /**
     * Method under test: {@link VolunteerService#findAllVolunteers()}
     */
    @Test
    void testFindAllVolunteers() {
        // Создаем пустой список волонтеров (volunteerList), который будет использоваться для мокирования репозитория.
        ArrayList<Volunteer> volunteerList = new ArrayList<>();

        // Настраиваем мокирование метода findAll() для репозитория волонтеров (volunteerRepository),
        // так чтобы он возвращал пустой список волонтеров.
        when(volunteerRepository.findAll()).thenReturn(volunteerList);

        // Вызываем метод findAllVolunteers() сервиса волонтеров (volunteerService) для получения всех волонтеров.
        List<Volunteer> actualFindAllVolunteersResult = volunteerService.findAllVolunteers();

        // Проверяем, что метод findAll() репозитория был вызван, а также проверяем, что результат пустой список волонтеров.
        verify(volunteerRepository).findAll();
        assertTrue(actualFindAllVolunteersResult.isEmpty());
        assertSame(volunteerList, actualFindAllVolunteersResult);
    }


    /**
     * Method under test: {@link VolunteerService#findVolunteerById(long)}
     */
    @Test
    void testFindVolunteerById() {
        // Создаем объект приюта (shelters) с заданными данными, который будет использоваться для создания волонтера.
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // Создаем объект волонтера (volunteer) с заданными данными.
        Volunteer volunteer = new Volunteer();
        volunteer.setChatId(1L);
        volunteer.setId(1L);
        volunteer.setName("Name");
        volunteer.setPhone("6625550144");
        volunteer.setSecondName("Second Name");
        volunteer.setSex("Sex");
        volunteer.setShelters(shelters);
        volunteer.setSurname("Doe");

        // Создаем Optional объект (ofResult) и помещаем в него объект волонтера (volunteer).
        Optional<Volunteer> ofResult = Optional.of(volunteer);

        // Настраиваем мокирование метода findById() для репозитория волонтеров (volunteerRepository),
        // так чтобы он возвращал Optional объект с объектом волонтера (volunteer).
        when(volunteerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Вызываем метод findVolunteerById() сервиса волонтеров (volunteerService) для поиска волонтера по ID.
        Volunteer actualFindVolunteerByIdResult = volunteerService.findVolunteerById(1L);

        // Проверяем, что метод findById() репозитория был вызван, и результат совпадает с объектом волонтера (volunteer).
        verify(volunteerRepository).findById(Mockito.<Long>any());
        assertSame(volunteer, actualFindVolunteerByIdResult);
    }


    /**
     * Method under test: {@link VolunteerService#createVolunteer(Volunteer)}
     */
    @Test
    void testCreateVolunteer() {

        // Создаем объект приюта (shelters) с заданными данными, который будет использоваться для создания волонтера.
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // Создаем объект волонтера (volunteer) с заданными данными.
        Volunteer volunteer = new Volunteer();
        volunteer.setChatId(1L);
        volunteer.setId(1L);
        volunteer.setName("Name");
        volunteer.setPhone("6625550144");
        volunteer.setSecondName("Second Name");
        volunteer.setSex("Sex");
        volunteer.setShelters(shelters);
        volunteer.setSurname("Doe");

        // Настраиваем мокирование метода save() для репозитория волонтеров (volunteerRepository),
        // так чтобы он возвращал созданный волонтер (volunteer).
        when(volunteerRepository.save(Mockito.<Volunteer>any())).thenReturn(volunteer);

        // Создаем второй объект приюта (shelters2) с теми же данными, затем создаем второй объект волонтера (volunteer2).
        Shelters shelters2 = new Shelters();
        shelters2.setAdress("Adress");
        shelters2.setId(1L);
        shelters2.setLocation("Location");
        shelters2.setName("Name");

        Volunteer volunteer2 = new Volunteer();
        volunteer2.setChatId(1L);
        volunteer2.setId(1L);
        volunteer2.setName("Name");
        volunteer2.setPhone("6625550144");
        volunteer2.setSecondName("Second Name");
        volunteer2.setSex("Sex");
        volunteer2.setShelters(shelters2);
        volunteer2.setSurname("Doe");

        // Затем вызываем метод createVolunteer() сервиса волонтеров (volunteerService) для создания волонтера.
        Volunteer actualCreateVolunteerResult = volunteerService.createVolunteer(volunteer2);

        // Проверяем, что метод save() репозитория был вызван, и результат совпадает с созданным волонтером (volunteer).
        verify(volunteerRepository).save(Mockito.<Volunteer>any());
        assertSame(volunteer, actualCreateVolunteerResult);
    }

    /**
     * Method under test: {@link VolunteerService#updateVolunteer(Volunteer)}
     */
    @Test
    void testUpdateVolunteer() {

        // Создаем объект приюта (shelters) с заданными данными, который будет использоваться для обновления волонтера.
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");


        // Создаем объект волонтера (volunteer) с заданными данными.
        Volunteer volunteer = new Volunteer();
        volunteer.setChatId(1L);
        volunteer.setId(1L);
        volunteer.setName("Name");
        volunteer.setPhone("6625550144");
        volunteer.setSecondName("Second Name");
        volunteer.setSex("Sex");
        volunteer.setShelters(shelters);
        volunteer.setSurname("Doe");
        Optional<Volunteer> ofResult = Optional.of(volunteer);

        Shelters shelters2 = new Shelters();
        shelters2.setAdress("Adress");
        shelters2.setId(1L);
        shelters2.setLocation("Location");
        shelters2.setName("Name");

        Volunteer volunteer2 = new Volunteer();
        volunteer2.setChatId(1L);
        volunteer2.setId(1L);
        volunteer2.setName("Name");
        volunteer2.setPhone("6625550144");
        volunteer2.setSecondName("Second Name");
        volunteer2.setSex("Sex");
        volunteer2.setShelters(shelters2);
        volunteer2.setSurname("Doe");

        // Настраиваем мокирование метода save() для репозитория волонтеров (volunteerRepository),
        // так чтобы он возвращал обновленного волонтера (volunteer2).
        when(volunteerRepository.save(Mockito.<Volunteer>any())).thenReturn(volunteer2);

        // Настраиваем мокирование метода findById() для репозитория волонтеров (volunteerRepository),
        // так чтобы он возвращал Optional объект с объектом волонтера (volunteer).
        when(volunteerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Shelters shelters3 = new Shelters();
        shelters3.setAdress("Adress");
        shelters3.setId(1L);
        shelters3.setLocation("Location");
        shelters3.setName("Name");


        // Создаем третий объект приюта (shelters3) с теми же данными, затем создаем третий объект волонтера (volunteer3).
        Volunteer volunteer3 = new Volunteer();
        volunteer3.setChatId(1L);
        volunteer3.setId(1L);
        volunteer3.setName("Name");
        volunteer3.setPhone("6625550144");
        volunteer3.setSecondName("Second Name");
        volunteer3.setSex("Sex");
        volunteer3.setShelters(shelters3);
        volunteer3.setSurname("Doe");

        // Затем вызываем метод updateVolunteer() сервиса волонтеров (volunteerService) для обновления волонтера.
        Volunteer actualUpdateVolunteerResult = volunteerService.updateVolunteer(volunteer3);

        // Проверяем, что метод findById() репозитория был вызван, затем метод save() был вызван, и результат совпадает с обновленным волонтером (volunteer2).
        verify(volunteerRepository).findById(Mockito.<Long>any());
        verify(volunteerRepository).save(Mockito.<Volunteer>any());
        assertSame(volunteer2, actualUpdateVolunteerResult);
    }

    /**
     * Method under test: {@link VolunteerService#updateVolunteer(Volunteer)}
     */
    @Test
    void testUpdateVolunteer2() {

        // Создаем пустой Optional объект (emptyResult), который будет возвращаться при поиске волонтера.
        Optional<Volunteer> emptyResult = Optional.empty();

        // Настраиваем мокирование метода findById() для репозитория волонтеров (volunteerRepository),
        // так чтобы он возвращал пустой результат (пустой Optional).
        when(volunteerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        Volunteer volunteer = new Volunteer();
        volunteer.setChatId(1L);
        volunteer.setId(1L);
        volunteer.setName("Name");
        volunteer.setPhone("6625550144");
        volunteer.setSecondName("Second Name");
        volunteer.setSex("Sex");
        volunteer.setShelters(shelters);
        volunteer.setSurname("Doe");

        // Создаем объект приюта (shelters) с заданными данными и объект волонтера (volunteer) с заданными данными,
        // затем вызываем метод updateVolunteer() сервиса волонтеров (volunteerService) для обновления волонтера.
        Volunteer actualUpdateVolunteerResult = volunteerService.updateVolunteer(volunteer);

        // Проверяем, что метод findById() репозитория был вызван и вернул пустой результат, а обновленного волонтера не существует (null).
        verify(volunteerRepository).findById(Mockito.<Long>any());
        assertNull(actualUpdateVolunteerResult);
    }

    /**
     * Method under test: {@link VolunteerService#deleteVolunteer(long)}
     */
    @Test
    void testDeleteVolunteer() {
        // Создаем объект приюта (shelters) с заданными данными.
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // Создаем объект волонтера (volunteer) с заданными данными и связываем его с приютом.
        Volunteer volunteer = new Volunteer();
        volunteer.setChatId(1L);
        volunteer.setId(1L);
        volunteer.setName("Name");
        volunteer.setPhone("6625550144");
        volunteer.setSecondName("Second Name");
        volunteer.setSex("Sex");
        volunteer.setShelters(shelters);
        volunteer.setSurname("Doe");

        // Создаем Optional объект (ofResult) и помещаем в него объект волонтера (volunteer).
        Optional<Volunteer> ofResult = Optional.of(volunteer);

        // Настраиваем мокирование методов delete() и findById() для репозитория волонтеров (volunteerRepository).
        // Метод delete() ничего не возвращает, поэтому используется doNothing().
        doNothing().when(volunteerRepository).delete(Mockito.<Volunteer>any());

        // Мокируем метод findById() так, чтобы он возвращал Optional объект с объектом волонтера (volunteer).
        when(volunteerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Вызываем метод deleteVolunteer() сервиса волонтеров (volunteerService) для удаления волонтера с ID=1.
        // Метод должен вернуть удаленного волонтера.
        Volunteer actualDeleteVolunteerResult = volunteerService.deleteVolunteer(1L);

        // Проверяем, что метод delete() и findById() репозитория были вызваны и вернули ожидаемый результат,
        // а также проверяем, что объект удаленного волонтера совпадает с ожидаемым объектом волонтера (volunteer).
        verify(volunteerRepository).delete(Mockito.<Volunteer>any());
        verify(volunteerRepository).findById(Mockito.<Long>any());
        assertSame(volunteer, actualDeleteVolunteerResult);
    }


    /**
     * Method under test: {@link VolunteerService#deleteVolunteer(long)}
     */
    @Test
    void testDeleteVolunteer2() {
        // Создаем пустой Optional объект (emptyResult), который будет возвращаться при поиске волонтера.
        Optional<Volunteer> emptyResult = Optional.empty();

        // Настраиваем мокирование метода findById() для репозитория волонтеров (volunteerRepository),
        // так чтобы он возвращал пустой результат (пустой Optional).
        when(volunteerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Вызываем метод deleteVolunteer() сервиса волонтеров (volunteerService) для удаления волонтера с ID=1.
        // Метод должен вернуть null, так как волонтер не найден.
        Volunteer actualDeleteVolunteerResult = volunteerService.deleteVolunteer(1L);

        // Проверяем, что метод findById() репозитория был вызван и вернул пустой результат, а удаленного волонтера не существует (null).
        verify(volunteerRepository).findById(Mockito.<Long>any());
        assertNull(actualDeleteVolunteerResult);

    }
}

