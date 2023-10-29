package pro.sky.telegram_chat_zooShelter.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.telegram_chat_zooShelter.model.Shelters;
import pro.sky.telegram_chat_zooShelter.repository.ShelterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ShelterService.class})
@ExtendWith(SpringExtension.class)
class ShelterServiceTest {
    @MockBean
    private ShelterRepository shelterRepository;

    @Autowired
    private ShelterService shelterService;

    /**
     * Method under test: {@link ShelterService#findAll()}
     */
    @Test
    void testFindAll() {
        ArrayList<Shelters> sheltersList = new ArrayList<>();
        when(shelterRepository.findAll()).thenReturn(sheltersList);
        List<Shelters> actualFindAllResult = shelterService.findAll();
        verify(shelterRepository).findAll();
        assertTrue(actualFindAllResult.isEmpty());
        assertSame(sheltersList, actualFindAllResult);
    }

    /**
     * Method under test: {@link ShelterService#findById(Long)}
     */
    @Test
    void testFindById() {
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");
        Optional<Shelters> ofResult = Optional.of(shelters);
        when(shelterRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Shelters actualFindByIdResult = shelterService.findById(1L);
        verify(shelterRepository).findById(Mockito.<Long>any());
        assertSame(shelters, actualFindByIdResult);
    }

    /**
     * Method under test: {@link ShelterService#create(Shelters)}
     */
    @Test
    void testCreate() {
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");
        when(shelterRepository.save(Mockito.<Shelters>any())).thenReturn(shelters);

        Shelters shelters2 = new Shelters();
        shelters2.setAdress("Adress");
        shelters2.setId(1L);
        shelters2.setLocation("Location");
        shelters2.setName("Name");
        Shelters actualCreateResult = shelterService.create(shelters2);
        verify(shelterRepository).save(Mockito.<Shelters>any());
        assertSame(shelters, actualCreateResult);
    }

    /**
     * Method under test: {@link ShelterService#update(Shelters)}
     */
    @Test
    void testUpdate() {
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");
        Optional<Shelters> ofResult = Optional.of(shelters);

        Shelters shelters2 = new Shelters();
        shelters2.setAdress("Adress");
        shelters2.setId(1L);
        shelters2.setLocation("Location");
        shelters2.setName("Name");
        when(shelterRepository.save(Mockito.<Shelters>any())).thenReturn(shelters2);
        when(shelterRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Shelters shelters3 = new Shelters();
        shelters3.setAdress("Adress");
        shelters3.setId(1L);
        shelters3.setLocation("Location");
        shelters3.setName("Name");
        Shelters actualUpdateResult = shelterService.update(shelters3);
        verify(shelterRepository).findById(Mockito.<Long>any());
        verify(shelterRepository).save(Mockito.<Shelters>any());
        assertSame(shelters2, actualUpdateResult);
    }

    /**
     * Method under test: {@link ShelterService#update(Shelters)}
     */
    @Test
    void testUpdate2() {
        Optional<Shelters> emptyResult = Optional.empty();
        when(shelterRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");
        Shelters actualUpdateResult = shelterService.update(shelters);
        verify(shelterRepository).findById(Mockito.<Long>any());
        assertNull(actualUpdateResult);
    }

    /**
     * Method under test: {@link ShelterService#delete(Long)}
     */
    @Test
    void testDelete() {
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");
        Optional<Shelters> ofResult = Optional.of(shelters);
        doNothing().when(shelterRepository).delete(Mockito.<Shelters>any());
        when(shelterRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Shelters actualDeleteResult = shelterService.delete(1L);
        verify(shelterRepository).delete(Mockito.<Shelters>any());
        verify(shelterRepository).findById(Mockito.<Long>any());
        assertSame(shelters, actualDeleteResult);
    }

    /**
     * Method under test: {@link ShelterService#delete(Long)}
     */
    @Test
    void testDelete2() {
        Optional<Shelters> emptyResult = Optional.empty();
        when(shelterRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        Shelters actualDeleteResult = shelterService.delete(1L);
        verify(shelterRepository).findById(Mockito.<Long>any());
        assertNull(actualDeleteResult);
    }
}

