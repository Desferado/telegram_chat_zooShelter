package pro.sky.telegram_chat_zooShelter.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.model.Pets;
import pro.sky.telegram_chat_zooShelter.model.Shelters;
import pro.sky.telegram_chat_zooShelter.repository.PetsRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PetsService.class})
@ExtendWith(SpringExtension.class)
class PetsServiceTest {
    @MockBean
    private PetsRepository petsRepository;

    @Autowired
    private PetsService petsService;

    /**
     * Method under test: {@link PetsService#getPets()}
     */
    @Test
    void testGetPets() {
        // 1. Создание пустого списка petsList
        ArrayList<Pets> petsList = new ArrayList<>();

        // 2. Настройка мокирования метода findAll репозитория
        when(petsRepository.findAll()).thenReturn(petsList);

        // 3. Вызов метода getPets сервиса
        List<Pets> actualPets = petsService.getPets();

        // 4. Проверка вызова метода findAll репозитория
        verify(petsRepository).findAll();

        // 5. Утверждение, что список actualPets пустой
        assertTrue(actualPets.isEmpty());

        // 6. Утверждение, что возвращенный список actualPets совпадает с petsList
        assertSame(petsList, actualPets);
    }


    /**
     * Method under test: {@link PetsService#findPetById(Long)}
     */
    @Test
    void testFindPetById() {
        // 1. Создание объекта Customer
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        // 2. Создание объекта Shelters
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // 3. Создание объекта Pets
        Pets pets = new Pets();
        pets.setAge(1);
        pets.setBreed("Breed");
        pets.setCustomer(customer);
        pets.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets.setId(1L);
        pets.setLimit_probation(1L);
        pets.setName("Bella");
        pets.setProbationStatus("Probation Status");
        pets.setShelters(shelters);
        pets.setType_pets("Bella");

        // 4. Создание Optional с ожидаемым результатом
        Optional<Pets> ofResult = Optional.of(pets);

        // 5. Настройка мокирования метода findById репозитория
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // 6. Вызов метода findPetById сервиса
        Pets actualFindPetByIdResult = petsService.findPetById(1L);

        // 7. Проверка вызова метода findById репозитория
        verify(petsRepository).findById(Mockito.<Long>any());

        // 8. Утверждение, что возвращенный результат совпадает с ожидаемым питомцем
        assertSame(pets, actualFindPetByIdResult);
    }


    /**
     * Method under test: {@link PetsService#createPet(Pets)}
     */
    @Test
    void testCreatePet() {
        // 1. Создание объекта customer
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        // 2. Создание объекта shelters
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // 3. Создание объекта pets
        Pets pets = new Pets();
        pets.setAge(1);
        pets.setBreed("Breed");
        pets.setCustomer(customer);
        pets.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets.setId(1L);
        pets.setLimit_probation(1L);
        pets.setName("Bella");
        pets.setProbationStatus("Probation Status");
        pets.setShelters(shelters);
        pets.setType_pets("Bella");

        // 4. Настройка мокирования метода save репозитория
        when(petsRepository.save(Mockito.<Pets>any())).thenReturn(pets);

        // 5. Создание второго объекта customer2
        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setChatId(1L);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPhone("6625550144");
        customer2.setSecondName("Second Name");
        customer2.setSurname("Doe");

        // 6. Создание второго объекта shelters2
        Shelters shelters2 = new Shelters();
        shelters2.setAdress("Adress");
        shelters2.setId(1L);
        shelters2.setLocation("Location");
        shelters2.setName("Name");

        // 7. Создание второго объекта pet
        Pets pet = new Pets();
        pet.setAge(1);
        pet.setBreed("Breed");
        pet.setCustomer(customer2);
        pet.setDecisionDate(LocalDate.of(1970, 1, 1));
        pet.setId(1L);
        pet.setLimit_probation(1L);
        pet.setName("Bella");
        pet.setProbationStatus("Probation Status");
        pet.setShelters(shelters2);
        pet.setType_pets("Bella");

        // 8. Вызов метода createPet сервиса
        Pets actualCreatePetResult = petsService.createPet(pet);

        // 9. Проверка вызова метода save репозитория
        verify(petsRepository).save(Mockito.<Pets>any());

        // 10. Утверждение, что возвращенный объект actualCreatePetResult совпадает с pets
        assertSame(pets, actualCreatePetResult);
    }


    /**
     * Method under test: {@link PetsService#updatePet(Pets)}
     */
    @Test
    void testUpdatePet() {
        // Создаем объекты Customer, Shelters и Pets для тестирования

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        Pets pets = new Pets();
        pets.setAge(1);
        pets.setBreed("Breed");
        pets.setCustomer(customer);
        pets.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets.setId(1L);
        pets.setLimit_probation(1L);
        pets.setName("Bella");
        pets.setProbationStatus("Probation Status");
        pets.setShelters(shelters);
        pets.setType_pets("Bella");

        // Конфигурируем поведение petsRepository
        Optional<Pets> ofResult = Optional.of(pets);

        // Создаем второй набор объектов Customer, Shelters и Pets
        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setChatId(1L);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPhone("6625550144");
        customer2.setSecondName("Second Name");
        customer2.setSurname("Doe");

        Shelters shelters2 = new Shelters();
        shelters2.setAdress("Adress");
        shelters2.setId(1L);
        shelters2.setLocation("Location");
        shelters2.setName("Name");

        Pets pets2 = new Pets();
        pets2.setAge(1);
        pets2.setBreed("Breed");
        pets2.setCustomer(customer2);
        pets2.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets2.setId(1L);
        pets2.setLimit_probation(1L);
        pets2.setName("Bella");
        pets2.setProbationStatus("Probation Status");
        pets2.setShelters(shelters2);
        pets2.setType_pets("Bella");

        // Мокируем petsRepository, чтобы возвращать pets2 при сохранении
        when(petsRepository.save(Mockito.<Pets>any())).thenReturn(pets2);

        // Мокируем petsRepository, чтобы возвращать исходного питомца при поиске по ID
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Создаем третий набор объектов Customer, Shelters и Pets
        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setChatId(1L);
        customer3.setEmail("jane.doe@example.org");
        customer3.setId(1L);
        customer3.setName("Name");
        customer3.setPhone("6625550144");
        customer3.setSecondName("Second Name");
        customer3.setSurname("Doe");

        Shelters shelters3 = new Shelters();
        shelters3.setAdress("Adress");
        shelters3.setId(1L);
        shelters3.setLocation("Location");
        shelters3.setName("Name");

        Pets pet = new Pets();
        pet.setAge(1);
        pet.setBreed("Breed");
        pet.setCustomer(customer3);
        pet.setDecisionDate(LocalDate.of(1970, 1, 1));
        pet.setId(1L);
        pet.setLimit_probation(1L);
        pet.setName("Bella");
        pet.setProbationStatus("Probation Status");
        pet.setShelters(shelters3);
        pet.setType_pets("Bella");

        // Вызываем метод updatePet для обновления информации о питомце
        Pets actualUpdatePetResult = petsService.updatePet(pet);

        // Проверяем, что методы findById и save репозитория вызываются
        verify(petsRepository).findById(Mockito.<Long>any());
        verify(petsRepository).save(Mockito.<Pets>any());

        // Проверяем, что обновленный питомец, возвращенный методом updatePet, соответствует pets2
        assertSame(pets2, actualUpdatePetResult);
    }


    /**
     * Method under test: {@link PetsService#deletePetById(Long)}
     */
    @Test
    void testDeletePetById() {
        // Создаем объекты Customer, Shelters и Pets для тестирования
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        Pets pets = new Pets();
        pets.setAge(1);
        pets.setBreed("Breed");
        pets.setCustomer(customer);
        pets.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets.setId(1L);
        pets.setLimit_probation(1L);
        pets.setName("Bella");
        pets.setProbationStatus("Probation Status");
        pets.setShelters(shelters);
        pets.setType_pets("Bella");

        // Определяем ожидаемый результат: возвращаем Optional с созданным объектом Pets
        Optional<Pets> ofResult = Optional.of(pets);

        // Мокируем метод deleteById в petsRepository, чтобы он ничего не делал
        doNothing().when(petsRepository).deleteById(Mockito.<Long>any());

        // Мокируем метод findById в petsRepository, чтобы он возвращал ofResult при поиске по любому ID
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Вызываем метод deletePetById для удаления питомца
        Pets actualDeletePetByIdResult = petsService.deletePetById(1L);

        // Проверяем, что методы deleteById и findById репозитория вызываются как ожидалось
        verify(petsRepository).deleteById(Mockito.<Long>any());
        verify(petsRepository).findById(Mockito.<Long>any());

        // Проверяем, что удаленный питомец, возвращенный методом deletePetById, соответствует исходному питомцу
        assertSame(pets, actualDeletePetByIdResult);
    }


    /**
     * Method under test: {@link PetsService#deletePetById(Long)}
     */
    @Test
    void testDeletePetById2() {
        // Создаем пустой Optional<Pets> для имитации отсутствия питомца с указанным ID
        Optional<Pets> emptyResult = Optional.empty();

        // Мокируем метод findById в petsRepository, чтобы он возвращал emptyResult при поиске по любому ID
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Вызываем метод deletePetById для удаления питомца
        Pets actualDeletePetByIdResult = petsService.deletePetById(1L);

        // Проверяем, что метод findById репозитория вызывается с указанным ID
        verify(petsRepository).findById(Mockito.<Long>any());

        // Проверяем, что результат удаления питомца равен null, так как питомца с таким ID не существует
        assertNull(actualDeletePetByIdResult);
    }


    /**
     * Method under test: {@link PetsService#findPetsByCustomer(Customer)}
     */
    @Test
    void testFindPetsByCustomer() {
        // Создаем пустой список petsList, чтобы имитировать пустой результат из репозитория
        ArrayList<Pets> petsList = new ArrayList<>();

        // Мокируем метод findPetsByCustomer в petsRepository, чтобы он возвращал petsList при поиске питомцев по любому объекту Customer
        when(petsRepository.findPetsByCustomer(Mockito.<Customer>any())).thenReturn(petsList);

        // Создаем объект Customer, который будет использоваться для поиска связанных питомцев
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        // Вызываем метод findPetsByCustomer для поиска питомцев, связанных с указанным объектом Customer
        List<Pets> actualFindPetsByCustomerResult = petsService.findPetsByCustomer(customer);

        // Проверяем, что метод findPetsByCustomer в репозитории вызывается с указанным объектом Customer
        verify(petsRepository).findPetsByCustomer(Mockito.<Customer>any());

        // Проверяем, что результат поиска питомцев пустой и равен созданному пустому списку petsList
        assertTrue(actualFindPetsByCustomerResult.isEmpty());
        assertSame(petsList, actualFindPetsByCustomerResult);
    }


    /**
     * Method under test: {@link PetsService#findPetsWithCustomer()}
     */
    @Test
    void testFindPetsWithCustomer() {
        // Создаем пустой список petsList, чтобы имитировать пустой результат из репозитория
        ArrayList<Pets> petsList = new ArrayList<>();

        // Мокируем метод findPetsByCustomerNotNull в petsRepository, чтобы он возвращал petsList при поиске питомцев с непустым объектом Customer
        when(petsRepository.findPetsByCustomerNotNull()).thenReturn(petsList);

        // Вызываем метод findPetsWithCustomer для поиска питомцев с непустым объектом Customer
        List<Pets> actualFindPetsWithCustomerResult = petsService.findPetsWithCustomer();

        // Проверяем, что метод findPetsByCustomerNotNull в репозитории вызывается
        verify(petsRepository).findPetsByCustomerNotNull();

        // Проверяем, что результат поиска питомцев с непустым объектом Customer пустой и равен созданному пустому списку petsList
        assertTrue(actualFindPetsWithCustomerResult.isEmpty());
        assertSame(petsList, actualFindPetsWithCustomerResult);
    }


    /**
     * Method under test: {@link PetsService#setPetProbation(Long)}
     */
    @Test
    void testSetPetProbation() {
        // Создаем объекты Customer и Shelters для создания питомца
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // Создаем питомца с указанными параметрами
        Pets pets = new Pets();
        pets.setAge(1);
        pets.setBreed("Breed");
        pets.setCustomer(customer);

        // Устанавливаем дату принятия решения
        LocalDate decisionDate = LocalDate.of(1970, 1, 1);
        pets.setDecisionDate(decisionDate);

        pets.setId(1L);
        pets.setLimit_probation(1L);
        pets.setName("Bella");
        pets.setProbationStatus("Probation Status");
        pets.setShelters(shelters);
        pets.setType_pets("Bella");

        // Создаем Optional<Pets> с ожидаемым результатом
        Optional<Pets> ofResult = Optional.of(pets);

        // Мокируем методы findById и save в репозитории для возвращения ожидаемых результатов
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(petsRepository.save(Mockito.<Pets>any())).thenReturn(pets);

        // Вызываем метод setPetProbation с идентификатором питомца (1L)
        Pets actualSetPetProbationResult = petsService.setPetProbation(1L);

        // Проверяем, что метод findById в репозитории был вызван с ожидаемым идентификатором
        verify(petsRepository, atLeast(1)).findById(Mockito.<Long>any());

        // Проверяем, что метод save в репозитории был вызван для сохранения изменений в питомце
        verify(petsRepository).save(Mockito.<Pets>any());

        // Проверяем, что статус испытательного срока (probationStatus) установлен на "в процессе"
        assertEquals("в процессе", actualSetPetProbationResult.getProbationStatus());

        // Проверяем, что возвращенный питомец соответствует ожидаемому питомцу с обновленным статусом испытательного срока
        assertSame(pets, actualSetPetProbationResult);
    }


    /**
     * Method under test: {@link PetsService#deletePetProbation(Long)}
     */
    @Test
    void testDeletePetProbation() {
        // Создаем объекты Customer и Shelters для создания питомца
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // Создаем питомца с указанными параметрами
        Pets pets = new Pets();
        pets.setAge(1);
        pets.setBreed("Breed");
        pets.setCustomer(customer);

        // Устанавливаем дату принятия решения
        LocalDate decisionDate = LocalDate.of(1970, 1, 1);
        pets.setDecisionDate(decisionDate);

        pets.setId(1L);
        pets.setLimit_probation(1L);
        pets.setName("Bella");
        pets.setProbationStatus("Probation Status");
        pets.setShelters(shelters);
        pets.setType_pets("Bella");

        // Создаем Optional<Pets> с ожидаемым результатом
        Optional<Pets> ofResult = Optional.of(pets);

        // Мокируем методы findById и save в репозитории для возвращения ожидаемых результатов
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(petsRepository.save(Mockito.<Pets>any())).thenReturn(pets);

        // Вызываем метод deletePetProbation с идентификатором питомца (1L)
        Pets actualDeletePetProbationResult = petsService.deletePetProbation(1L);

        // Проверяем, что метод findById в репозитории был вызван с ожидаемым идентификатором
        verify(petsRepository, atLeast(1)).findById(Mockito.<Long>any());

        // Проверяем, что метод save в репозитории был вызван для сохранения изменений в питомце
        verify(petsRepository).save(Mockito.<Pets>any());

        // Проверяем, что испытательный срок (probation) удален
        assertNull(actualDeletePetProbationResult.getLimit_probation());

        // Проверяем, что статус испытательного срока (probationStatus) удален
        assertNull(actualDeletePetProbationResult.getProbationStatus());

        // Проверяем, что дата принятия решения (decisionDate) удалена
        assertNull(actualDeletePetProbationResult.getDecisionDate());

        // Проверяем, что возвращенный питомец соответствует ожидаемому питомцу без испытательного срока
        assertSame(pets, actualDeletePetProbationResult);
    }


    /**
     * Method under test: {@link PetsService#getDateProbation(Long)}
     */
    @Test
    void testGetDateProbation() {
        // Создаем объекты Customer и Shelters для создания питомца
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // Создаем питомца с указанными параметрами
        Pets pets = new Pets();
        pets.setAge(1);
        pets.setBreed("Breed");
        pets.setCustomer(customer);

        // Устанавливаем дату принятия решения
        LocalDate decisionDate = LocalDate.of(1970, 1, 1);
        pets.setDecisionDate(decisionDate);

        pets.setId(1L);
        pets.setLimit_probation(1L);
        pets.setName("Bella");
        pets.setProbationStatus("Probation Status");
        pets.setShelters(shelters);
        pets.setType_pets("Bella");

        Optional<Pets> ofResult = Optional.of(pets);

        // Мокируем метод findById в репозитории для возвращения ожидаемого результата
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Вызываем метод getDateProbation с идентификатором питомца (1L)
        LocalDate actualDateProbation = petsService.getDateProbation(1L);

        // Проверяем, что метод findById в репозитории был вызван с ожидаемым идентификатором
        verify(petsRepository).findById(Mockito.<Long>any());

        // Проверяем, что возвращенная дата соответствует ожидаемой дате принятия решения
        assertEquals("1970-01-01", actualDateProbation.toString());

        // Проверяем, что объект LocalDate, возвращенный из сервиса, совпадает с реальной датой
        assertSame(decisionDate, actualDateProbation);
    }


    /**
     * Method under test: {@link PetsService#getProbationStatus(Long)}
     */
    @Test
    void testGetProbationStatus() {
        // Создаем объекты Customer и Shelters для создания питомца
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // Создаем питомца с указанными параметрами
        Pets pets = new Pets();
        pets.setAge(1);
        pets.setBreed("Breed");
        pets.setCustomer(customer);
        pets.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets.setId(1L);
        pets.setLimit_probation(1L);
        pets.setName("Bella");
        pets.setProbationStatus("Probation Status");
        pets.setShelters(shelters);
        pets.setType_pets("Bella");
        Optional<Pets> ofResult = Optional.of(pets);

        // Мокируем метод findById в репозитории для возвращения ожидаемого результата
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Вызываем метод getProbationStatus с идентификатором питомца (1L)
        String actualProbationStatus = petsService.getProbationStatus(1L);

        // Проверяем, что метод findById в репозитории был вызван с ожидаемым идентификатором
        verify(petsRepository).findById(Mockito.<Long>any());

        // Проверяем, что результат соответствует ожидаемому статусу испытательного срока
        assertEquals("Probation Status", actualProbationStatus);
    }


    /**
     * Method under test: {@link PetsService#setAddDays(int, Long)}
     */
    @Test
    void testSetAddDays() {
        // Создаем объекты Customer и Shelters для создания питомца
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // Создаем питомца с возрастом 3 года и сроком испытательного срока 5 дней
        Pets pets = new Pets();
        pets.setAge(3);
        pets.setBreed("Breed");
        pets.setCustomer(customer);
        pets.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets.setId(1L);
        pets.setLimit_probation(5L);
        pets.setName("Bella");
        pets.setProbationStatus("Probation Status");
        pets.setShelters(shelters);
        pets.setType_pets("Bella");
        Optional<Pets> ofResult = Optional.of(pets);

        // Создаем нового Customer и Shelters (необязательно), и питомца с теми же параметрами
        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setChatId(1L);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPhone("6625550144");
        customer2.setSecondName("Second Name");
        customer2.setSurname("Doe");

        Shelters shelters2 = new Shelters();
        shelters2.setAdress("Adress");
        shelters2.setId(1L);
        shelters2.setLocation("Location");
        shelters2.setName("Name");

        Pets pets2 = new Pets();
        pets2.setAge(3);
        pets2.setBreed("Breed");
        pets2.setCustomer(customer2);
        pets2.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets2.setId(1L);
        pets2.setLimit_probation(5L);
        pets2.setName("Bella");
        pets2.setProbationStatus("Probation Status");
        pets2.setShelters(shelters2);
        pets2.setType_pets("Bella");

        // Мокируем методы в репозитории для возвращения ожидаемого результата
        when(petsRepository.save(Mockito.<Pets>any())).thenReturn(pets2);
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Вызываем метод setAddDays, передавая возраст питомца (3 года) и количество дней (1 день)
        Long actualSetAddDaysResult = petsService.setAddDays(3, 1L);

        // Проверяем, что метод findById в репозитории был вызван хотя бы один раз
        verify(petsRepository, atLeast(1)).findById(Mockito.<Long>any());

        // Проверяем, что метод save в репозитории был вызван для сохранения питомца
        verify(petsRepository).save(Mockito.<Pets>any());

        // Проверяем, что результат (новый срок испытательного срока) равен 8 дням (изначальные 5 дней + 3 дня)
        assertEquals(8L, actualSetAddDaysResult.longValue());
    }

}

