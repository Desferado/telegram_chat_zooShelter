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
        ArrayList<Volunteer> volunteerList = new ArrayList<>();
        when(volunteerRepository.findAll()).thenReturn(volunteerList);
        List<Volunteer> actualFindAllVolunteersResult = volunteerService.findAllVolunteers();
        verify(volunteerRepository).findAll();
        assertTrue(actualFindAllVolunteersResult.isEmpty());
        assertSame(volunteerList, actualFindAllVolunteersResult);
    }

    /**
     * Method under test: {@link VolunteerService#findVolunteerById(long)}
     */
    @Test
    void testFindVolunteerById() {
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
        Optional<Volunteer> ofResult = Optional.of(volunteer);
        when(volunteerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Volunteer actualFindVolunteerByIdResult = volunteerService.findVolunteerById(1L);
        verify(volunteerRepository).findById(Mockito.<Long>any());
        assertSame(volunteer, actualFindVolunteerByIdResult);
    }

    /**
     * Method under test: {@link VolunteerService#createVolunteer(Volunteer)}
     */
    @Test
    void testCreateVolunteer() {
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
        when(volunteerRepository.save(Mockito.<Volunteer>any())).thenReturn(volunteer);

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
        Volunteer actualCreateVolunteerResult = volunteerService.createVolunteer(volunteer2);
        verify(volunteerRepository).save(Mockito.<Volunteer>any());
        assertSame(volunteer, actualCreateVolunteerResult);
    }

    /**
     * Method under test: {@link VolunteerService#updateVolunteer(Volunteer)}
     */
    @Test
    void testUpdateVolunteer() {
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
        when(volunteerRepository.save(Mockito.<Volunteer>any())).thenReturn(volunteer2);
        when(volunteerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Shelters shelters3 = new Shelters();
        shelters3.setAdress("Adress");
        shelters3.setId(1L);
        shelters3.setLocation("Location");
        shelters3.setName("Name");

        Volunteer volunteer3 = new Volunteer();
        volunteer3.setChatId(1L);
        volunteer3.setId(1L);
        volunteer3.setName("Name");
        volunteer3.setPhone("6625550144");
        volunteer3.setSecondName("Second Name");
        volunteer3.setSex("Sex");
        volunteer3.setShelters(shelters3);
        volunteer3.setSurname("Doe");
        Volunteer actualUpdateVolunteerResult = volunteerService.updateVolunteer(volunteer3);
        verify(volunteerRepository).findById(Mockito.<Long>any());
        verify(volunteerRepository).save(Mockito.<Volunteer>any());
        assertSame(volunteer2, actualUpdateVolunteerResult);
    }

    /**
     * Method under test: {@link VolunteerService#updateVolunteer(Volunteer)}
     */
    @Test
    void testUpdateVolunteer2() {
        Optional<Volunteer> emptyResult = Optional.empty();
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
        Volunteer actualUpdateVolunteerResult = volunteerService.updateVolunteer(volunteer);
        verify(volunteerRepository).findById(Mockito.<Long>any());
        assertNull(actualUpdateVolunteerResult);
    }

    /**
     * Method under test: {@link VolunteerService#deleteVolunteer(long)}
     */
    @Test
    void testDeleteVolunteer() {
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
        Optional<Volunteer> ofResult = Optional.of(volunteer);
        doNothing().when(volunteerRepository).delete(Mockito.<Volunteer>any());
        when(volunteerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Volunteer actualDeleteVolunteerResult = volunteerService.deleteVolunteer(1L);
        verify(volunteerRepository).delete(Mockito.<Volunteer>any());
        verify(volunteerRepository).findById(Mockito.<Long>any());
        assertSame(volunteer, actualDeleteVolunteerResult);
    }

    /**
     * Method under test: {@link VolunteerService#deleteVolunteer(long)}
     */
    @Test
    void testDeleteVolunteer2() {
        Optional<Volunteer> emptyResult = Optional.empty();
        when(volunteerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        Volunteer actualDeleteVolunteerResult = volunteerService.deleteVolunteer(1L);
        verify(volunteerRepository).findById(Mockito.<Long>any());
        assertNull(actualDeleteVolunteerResult);
    }
}

