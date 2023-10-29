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
        // Создаем пустой список приютов (sheltersList).
        ArrayList<Shelters> sheltersList = new ArrayList();

        // Настраиваем мокирование метода findAll() для репозитория приютов (shelterRepository),
        // так чтобы он возвращал созданный пустой список (sheltersList).
        when(shelterRepository.findAll()).thenReturn(sheltersList);

        // Вызываем метод findAll() сервиса приютов (shelterService) и сохраняем его результат в actualFindAllResult.
        List<Shelters> actualFindAllResult = shelterService.findAll();

        // Проверяем, что метод shelterRepository.findAll() был вызван, результат является пустым списком,
        // и ссылка на возвращенный список совпадает с sheltersList.
        verify(shelterRepository).findAll();
        assertTrue(actualFindAllResult.isEmpty());
        assertSame(sheltersList, actualFindAllResult);
    }


    /**
     * Method under test: {@link ShelterService#findById(Long)}
     */
    @Test
    void testFindById() {
        // Создаем объект приюта (shelters) с заданными данными.
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // Создаем Optional, содержащий созданный объект приюта.
        Optional<Shelters> ofResult = Optional.of(shelters);

        // Настраиваем мокирование метода findById() для репозитория приютов (shelterRepository),
        // так чтобы он возвращал созданный Optional (ofResult).
        when(shelterRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Вызываем метод findById() сервиса приютов (shelterService) и сохраняем его результат в actualFindByIdResult.
        Shelters actualFindByIdResult = shelterService.findById(1L);

        // Проверяем, что метод shelterRepository.findById() был вызван, результат не является пустым Optional,
        // и ссылка на возвращенный объект совпадает с созданным объектом приюта (shelters).
        verify(shelterRepository).findById(Mockito.<Long>any());
        assertSame(shelters, actualFindByIdResult);
    }


    /**
     * Method under test: {@link ShelterService#create(Shelters)}
     */
    @Test
    void testCreate() {
        // Создаем объект приюта (shelters) с заданными данными.
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // Настраиваем мокирование метода save() для репозитория приютов (shelterRepository),
        // так чтобы он возвращал созданный объект приюта (shelters).
        when(shelterRepository.save(Mockito.<Shelters>any())).thenReturn(shelters);

        // Создаем второй объект приюта (shelters2) с теми же данными, затем вызываем метод create() сервиса приютов (shelterService).
        // Этот метод должен сохранить приют и вернуть результат.
        Shelters shelters2 = new Shelters();
        shelters2.setAdress("Adress");
        shelters2.setId(1L);
        shelters2.setLocation("Location");
        shelters2.setName("Name");
        Shelters actualCreateResult = shelterService.create(shelters2);

        // Проверяем, что метод shelterRepository.save() был вызван, и результат совпадает с созданным объектом приюта (shelters).
        verify(shelterRepository).save(Mockito.<Shelters>any());
        assertSame(shelters, actualCreateResult);
    }


    /**
     * Method under test: {@link ShelterService#update(Shelters)}
     */
    @Test
    void testUpdate() {

        // Создаем объект приюта (shelters) с заданными данными.
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

        // Настраиваем мокирование метода save() для репозитория приютов (shelterRepository),
        // так чтобы он возвращал другой объект приюта (shelters2).
        when(shelterRepository.save(Mockito.<Shelters>any())).thenReturn(shelters2);

        // Настраиваем мокирование метода findById() для репозитория приютов (shelterRepository),
        // так чтобы он возвращал Optional объект с объектом приюта (shelters).
        when(shelterRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Создаем третий объект приюта (shelters3) с теми же данными, затем вызываем метод update() сервиса приютов (shelterService).
        // Этот метод должен обновить приют и вернуть результат.
        Shelters shelters3 = new Shelters();
        shelters3.setAdress("Adress");
        shelters3.setId(1L);
        shelters3.setLocation("Location");
        shelters3.setName("Name");
        Shelters actualUpdateResult = shelterService.update(shelters3);

        // Проверяем, что метод shelterRepository.findById() был вызван, затем метод save() был вызван, и результат совпадает с объектом приюта (shelters2).
        verify(shelterRepository).findById(Mockito.<Long>any());
        verify(shelterRepository).save(Mockito.<Shelters>any());
        assertSame(shelters2, actualUpdateResult);
    }

    /**
     * Method under test: {@link ShelterService#update(Shelters)}
     */
    @Test
    void testUpdate2() {
        // Настраиваем мокирование метода findById() для репозитория приютов (shelterRepository),
        // так чтобы он возвращал пустой Optional (emptyResult), что означает отсутствие приюта с указанным ID.
        Optional<Shelters> emptyResult = Optional.empty();
        when(shelterRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Создаем объект приюта (shelters) с заданными данными и вызываем метод update() сервиса приютов (shelterService).
        // В данном случае, метод должен вернуть null, так как приюта с указанным ID не существует.
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");
        Shelters actualUpdateResult = shelterService.update(shelters);

        // Проверяем, что метод shelterRepository.findById() был вызван и вернул пустой результат (отсутствие приюта).
        // Также проверяем, что результат равен null.
        verify(shelterRepository).findById(Mockito.<Long>any());
        assertNull(actualUpdateResult);
    }


    /**
     * Method under test: {@link ShelterService#delete(Long)}
     */
    @Test
    void testDelete() {
        // Создаем объект приюта (shelters) с заданными данными.
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // Создаем Optional объект (ofResult) и помещаем в него объект приюта (shelters).
        Optional<Shelters> ofResult = Optional.of(shelters);

        // Настраиваем мокирование метода delete() для репозитория приютов (shelterRepository).
        // Метод doNothing() указывает, что при вызове delete() ничего не должно происходить.
        doNothing().when(shelterRepository).delete(Mockito.<Shelters>any());

        // Настраиваем мокирование метода findById() для репозитория приютов (shelterRepository),
        // так чтобы он возвращал Optional объект с объектом приюта (shelters).
        when(shelterRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Вызываем метод delete() сервиса приютов (shelterService) с указанным ID.
        Shelters actualDeleteResult = shelterService.delete(1L);

        // Проверяем, что методы shelterRepository.delete() и shelterRepository.findById() были вызваны,
        // и результат удаления равен объекту приюта (shelters).
        verify(shelterRepository).delete(Mockito.<Shelters>any());
        verify(shelterRepository).findById(Mockito.<Long>any());
        assertSame(shelters, actualDeleteResult);
    }


    /**
     * Method under test: {@link ShelterService#delete(Long)}
     */
    @Test
    void testDelete2() {
        // Создаем Optional объект (emptyResult), представляющий собой пустой результат, что означает,
        // что приют с указанным ID не существует.
        Optional<Shelters> emptyResult = Optional.empty();

        // Настраиваем мокирование метода findById() для репозитория приютов (shelterRepository),
        // так чтобы он возвращал пустой результат (отсутствие приюта с указанным ID).
        when(shelterRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Вызываем метод delete() сервиса приютов (shelterService) с указанным ID, который не существует.
        Shelters actualDeleteResult = shelterService.delete(1L);

        // Проверяем, что метод findById() был вызван и что результат удаления равен null, так как приют не найден.
        verify(shelterRepository).findById(Mockito.<Long>any());
        assertNull(actualDeleteResult);
    }

}

