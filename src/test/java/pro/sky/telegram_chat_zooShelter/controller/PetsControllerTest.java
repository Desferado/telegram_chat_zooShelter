package pro.sky.telegram_chat_zooShelter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegram_chat_zooShelter.model.Pets;
import pro.sky.telegram_chat_zooShelter.services.PetsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PetsController.class)
public class PetsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetsService petsService;

    private List<Pets> petsList;
    private static Object deserializeActualWithTypeAsExpected(String content,
                                                              Object expected) throws JsonProcessingException {
        return new ObjectMapper().readValue(content, expected.getClass());
    }

    private static List<?> deserializeActualWithTypeAsExpected(String content, Class objClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, objClass));
    }

    private static ResultMatcher fieldMatcher(Pets pet) {
        return ResultMatcher.matchAll(
                jsonPath("$.id").value(pet.getId()),
                jsonPath("$.age").value(pet.getAge()),
                jsonPath("$.name").value(pet.getName()),
                jsonPath("$.type_pets").value(pet.getAge()),
                jsonPath("$.breed").value(pet.getAge())
        );
    }

//    @BeforeEach
//    public void setUp() {
//        petsList = new ArrayList<>(
//                Arrays.asList(
//                        new Pets(1L,4,"Барсик","Кошка","Сиамская",null,null),
//                        new Pets (2L,3,"Дружок","Собака","Пудель",null,null)
//                )
//        );
//    }

    @Test
    public void getAllPetsTest() throws Exception {
        when(petsService.getPets()).thenReturn(petsList);

        mockMvc.perform(get("/pet/get-pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(petsList.size()));

        verify(petsService).getPets();
    }

    // Тест для получения животного по id
    @Test
    public void getPetByIdTest() throws Exception {
        Pets pet = petsList.get(0);
        Long petId = pet.getId();

        when(petsService.findPetById(petId)).thenReturn(pet);

        mockMvc.perform(get("/pet/{id}", petId)
                        .param("номер животного", String.valueOf(petId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(petId));

        verify(petsService).findPetById(petId);
    }

    // Тест для создания животного
//    @Test
//    public void createPetTest() throws Exception {
//        Pets newPet = new Pets(3L,5,"Пушок","Кошка","Британская",null,null);
//
//        when(petsService.createPet(any(Pets.class))).thenReturn(newPet);
//
//        String response = mockMvc.perform(MockMvcRequestBuilders
//                        .post("/pet")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{" +
//                                "\"id\":3," +
//                                "\"возраст\":5," +
//                                "\"имя\":\"Пушок\"," +
//                                "\"тип\":\"Кошка\"," +
//                                "\"порода\":\"Британская\"," +
//                                "\"клиент\":\"null\"," +
//                                "\"приют\":\"null\","
//                                + "}")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(HttpStatus.OK.value()))
//                .andExpect(fieldMatcher(newPet))
//                .andReturn().getResponse().getContentAsString();
//        Pets actual = (Pets) deserializeActualWithTypeAsExpected(response, newPet);
//        assertEquals(newPet, actual);
//    }

    // Тест для обновления животного
    @Test
    public void updatePetTest() throws Exception {
        Pets updatedPet = petsList.get(0);
        updatedPet.setName("Обновить имя питомца");

        when(petsService.updatePet(any(Pets.class))).thenReturn(updatedPet);

        mockMvc.perform(put("/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"id\":1L," +
                                "\"возраст\":5," +
                                "\"имя\":\"Ричард\"," +
                                "\"тип\":\"Собака\"," +
                                "\"порода\":\"Хаски\"," +
                                "\"клиент\":\"null\"," +
                                "\"приют\":\"null\"" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedPet.getId()));

        verify(petsService).updatePet(any(Pets.class));
    }

    // Тест для удаления животного
    @Test
    public void removePetTest() throws Exception {
        Pets deletedPet = petsList.get(0);
        Long petId = deletedPet.getId();

        when(petsService.deletePetById(petId)).thenReturn(deletedPet);

        mockMvc.perform(delete("/pet/{id}", petId)
                        .param("номер животного", String.valueOf(petId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(petId));

        verify(petsService).deletePetById(petId);
    }
}