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
        ArrayList<Pets> petsList = new ArrayList<>();
        when(petsRepository.findAll()).thenReturn(petsList);
        List<Pets> actualPets = petsService.getPets();
        verify(petsRepository).findAll();
        assertTrue(actualPets.isEmpty());
        assertSame(petsList, actualPets);
    }

    /**
     * Method under test: {@link PetsService#findPetById(Long)}
     */
    @Test
    void testFindPetById() {
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
        Optional<Pets> ofResult = Optional.of(pets);
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Pets actualFindPetByIdResult = petsService.findPetById(1L);
        verify(petsRepository).findById(Mockito.<Long>any());
        assertSame(pets, actualFindPetByIdResult);
    }

    /**
     * Method under test: {@link PetsService#createPet(Pets)}
     */
    @Test
    void testCreatePet() {
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
        when(petsRepository.save(Mockito.<Pets>any())).thenReturn(pets);

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
        Pets actualCreatePetResult = petsService.createPet(pet);
        verify(petsRepository).save(Mockito.<Pets>any());
        assertSame(pets, actualCreatePetResult);
    }

    /**
     * Method under test: {@link PetsService#updatePet(Pets)}
     */
    @Test
    void testUpdatePet() {
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
        Optional<Pets> ofResult = Optional.of(pets);

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
        when(petsRepository.save(Mockito.<Pets>any())).thenReturn(pets2);
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

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
        Pets actualUpdatePetResult = petsService.updatePet(pet);
        verify(petsRepository).findById(Mockito.<Long>any());
        verify(petsRepository).save(Mockito.<Pets>any());
        assertSame(pets2, actualUpdatePetResult);
    }

    /**
     * Method under test: {@link PetsService#deletePetById(Long)}
     */
    @Test
    void testDeletePetById() {
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
        Optional<Pets> ofResult = Optional.of(pets);
        doNothing().when(petsRepository).deleteById(Mockito.<Long>any());
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Pets actualDeletePetByIdResult = petsService.deletePetById(1L);
        verify(petsRepository).deleteById(Mockito.<Long>any());
        verify(petsRepository).findById(Mockito.<Long>any());
        assertSame(pets, actualDeletePetByIdResult);
    }

    /**
     * Method under test: {@link PetsService#deletePetById(Long)}
     */
    @Test
    void testDeletePetById2() {
        Optional<Pets> emptyResult = Optional.empty();
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        Pets actualDeletePetByIdResult = petsService.deletePetById(1L);
        verify(petsRepository).findById(Mockito.<Long>any());
        assertNull(actualDeletePetByIdResult);
    }

    /**
     * Method under test: {@link PetsService#findPetsByCustomer(Customer)}
     */
    @Test
    void testFindPetsByCustomer() {
        ArrayList<Pets> petsList = new ArrayList<>();
        when(petsRepository.findPetsByCustomer(Mockito.<Customer>any())).thenReturn(petsList);

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");
        List<Pets> actualFindPetsByCustomerResult = petsService.findPetsByCustomer(customer);
        verify(petsRepository).findPetsByCustomer(Mockito.<Customer>any());
        assertTrue(actualFindPetsByCustomerResult.isEmpty());
        assertSame(petsList, actualFindPetsByCustomerResult);
    }

    /**
     * Method under test: {@link PetsService#findPetsWithCustomer()}
     */
    @Test
    void testFindPetsWithCustomer() {
        ArrayList<Pets> petsList = new ArrayList<>();
        when(petsRepository.findPetsByCustomerNotNull()).thenReturn(petsList);
        List<Pets> actualFindPetsWithCustomerResult = petsService.findPetsWithCustomer();
        verify(petsRepository).findPetsByCustomerNotNull();
        assertTrue(actualFindPetsWithCustomerResult.isEmpty());
        assertSame(petsList, actualFindPetsWithCustomerResult);
    }

    /**
     * Method under test: {@link PetsService#setPetProbation(Long)}
     */
    @Test
    void testSetPetProbation() {
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
        Optional<Pets> ofResult = Optional.of(pets);

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
        when(petsRepository.save(Mockito.<Pets>any())).thenReturn(pets2);
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Pets actualSetPetProbationResult = petsService.setPetProbation(1L);
        verify(petsRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(petsRepository).save(Mockito.<Pets>any());
        assertEquals("в процессе", actualSetPetProbationResult.getProbationStatus());
        assertSame(pets, actualSetPetProbationResult);
    }

    /**
     * Method under test: {@link PetsService#deletePetProbation(Long)}
     */
    @Test
    void testDeletePetProbation() {
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
        Optional<Pets> ofResult = Optional.of(pets);

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
        when(petsRepository.save(Mockito.<Pets>any())).thenReturn(pets2);
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Pets actualDeletePetProbationResult = petsService.deletePetProbation(1L);
        verify(petsRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(petsRepository).save(Mockito.<Pets>any());
        assertNull(actualDeletePetProbationResult.getLimit_probation());
        assertNull(actualDeletePetProbationResult.getProbationStatus());
        assertNull(actualDeletePetProbationResult.getDecisionDate());
        assertSame(pets, actualDeletePetProbationResult);
    }

    /**
     * Method under test: {@link PetsService#getDateProbation(Long)}
     */
    @Test
    void testGetDateProbation() {
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
        LocalDate decisionDate = LocalDate.of(1970, 1, 1);
        pets.setDecisionDate(decisionDate);
        pets.setId(1L);
        pets.setLimit_probation(1L);
        pets.setName("Bella");
        pets.setProbationStatus("Probation Status");
        pets.setShelters(shelters);
        pets.setType_pets("Bella");
        Optional<Pets> ofResult = Optional.of(pets);
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        LocalDate actualDateProbation = petsService.getDateProbation(1L);
        verify(petsRepository).findById(Mockito.<Long>any());
        assertEquals("1970-01-01", actualDateProbation.toString());
        assertSame(decisionDate, actualDateProbation);
    }

    /**
     * Method under test: {@link PetsService#getProbationStatus(Long)}
     */
    @Test
    void testGetProbationStatus() {
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
        Optional<Pets> ofResult = Optional.of(pets);
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualProbationStatus = petsService.getProbationStatus(1L);
        verify(petsRepository).findById(Mockito.<Long>any());
        assertEquals("Probation Status", actualProbationStatus);
    }

    /**
     * Method under test: {@link PetsService#setAddDays(int, Long)}
     */
    @Test
    void testSetAddDays() {
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
        when(petsRepository.save(Mockito.<Pets>any())).thenReturn(pets2);
        when(petsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Long actualSetAddDaysResult = petsService.setAddDays(3, 1L);
        verify(petsRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(petsRepository).save(Mockito.<Pets>any());
        assertEquals(8L, actualSetAddDaysResult.longValue());
    }
}

