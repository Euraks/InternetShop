package ru.restfulapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.restfulapi.model.PersonalComputer;
import ru.restfulapi.repository.PersonalComputerRepository;
import ru.restfulapi.service.impl.PersonalComputerServiceImpl;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonalComputersControllerTests {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonalComputerServiceImpl computerService;

    @MockBean
    PersonalComputerRepository personalComputerRepository;


    @Test
    @DisplayName("POST /v01/PersonalComputers")
    void testCreatePersonalComputer() throws Exception {
        PersonalComputer personalComputer = new PersonalComputer(1L, "SER01-001", "Intel", 10000, 1, "PC");
        doReturn(personalComputer).when(computerService).create(any());
        mockMvc.perform(post("/v01/PersonalComputers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(personalComputer)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber", is("SER01-001")));
    }

    @Test
    @DisplayName("GET /v01/PersonalComputers")
    void testGetPersonalComputerSuccess() throws Exception {

        PersonalComputer personalComputer = new PersonalComputer(1L, "SER01-001", "Intel", 10000, 1, "PC");
        PersonalComputer personalComputerOne = new PersonalComputer(2L, "SER01-002", "Spectrum", 20000, 1, "PC");
        PersonalComputer personalComputerTwo = new PersonalComputer(3L, "SER01-003", "Mac", 30000, 1, "PC");

        doReturn(Lists.newArrayList(personalComputer, personalComputerOne, personalComputerTwo)).when(computerService).readAll();

        mockMvc.perform(get("/v01/PersonalComputers"))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].serialNumber", is("SER01-001")))
                .andExpect(jsonPath("$[0].company", is("Intel")))
                .andExpect(jsonPath("$[0].price", is(10000)))
                .andExpect(jsonPath("$[0].quantity", is(1)))
                .andExpect(jsonPath("$[0].formFactor", is("PC")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].serialNumber", is("SER01-002")))
                .andExpect(jsonPath("$[1].company", is("Spectrum")))
                .andExpect(jsonPath("$[1].price", is(20000)))
                .andExpect(jsonPath("$[1].quantity", is(1)))
                .andExpect(jsonPath("$[1].formFactor", is("PC")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].serialNumber", is("SER01-003")))
                .andExpect(jsonPath("$[2].company", is("Mac")))
                .andExpect(jsonPath("$[2].price", is(30000)))
                .andExpect(jsonPath("$[2].quantity", is(1)))
                .andExpect(jsonPath("$[2].formFactor", is("PC")));
    }

    @Test
    @DisplayName("GET /v01/PersonalComputers/1")
    void testGetWidgetById() throws Exception {
        PersonalComputer personalComputer = new PersonalComputer(1L, "SER01-001", "Intel", 10000, 1, "PC");

        doReturn(personalComputer).when(personalComputerRepository).save(personalComputer);
        doReturn(Optional.of(personalComputer)).when(computerService).read(1L);


        mockMvc.perform(get("/v01/PersonalComputers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.serialNumber", is("SER01-001")))
                .andExpect(jsonPath("$.company", is("Intel")))
                .andExpect(jsonPath("$.price", is(10000)))
                .andExpect(jsonPath("$.quantity", is(1)))
                .andExpect(jsonPath("$.formFactor", is("PC")));
    }

    @Test
    @DisplayName("GET /v01/PersonalComputers/1 - Not Found")
    void testGetPersonalComputerByIdNotFound() throws Exception {

        doReturn(Optional.empty()).when(computerService).read(1L);

        mockMvc.perform(get("/v01/PersonalComputers/{id}", 1L))
                // Validate the response code
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /v01/PersonalComputers/1")
    void testUpdatePersonalComputer() throws Exception {
        PersonalComputer personalComputerToPut = new PersonalComputer(1L, "SER01-001", "Intel", 10000, 1, "PC");
        PersonalComputer personalComputerToReturnSave = new PersonalComputer(1L, "NewSER01-001", "NewCompany", 10000, 1, "NewFormFactor");

        doReturn(personalComputerToPut).when(personalComputerRepository).save(personalComputerToPut);
        doReturn(true).when(computerService).update(personalComputerToReturnSave, 1L);

        mockMvc.perform(put("/v01/PersonalComputers/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(personalComputerToReturnSave)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.serialNumber", is("NewSER01-001")))
                .andExpect(jsonPath("$.company", is("NewCompany")))
                .andExpect(jsonPath("$.price", is(10000)))
                .andExpect(jsonPath("$.quantity", is(1)))
                .andExpect(jsonPath("$.formFactor", is("NewFormFactor")));

    }

    @Test
    @DisplayName("PUT /v01/PersonalComputers/1 -- NotFound")
    void testUpdatePersonalComputerNotFound() throws Exception {
        PersonalComputer personalComputerToPut = new PersonalComputer(1L, "SER01-001", "Intel", 10000, 1, "PC");
        PersonalComputer personalComputerToReturnSave = new PersonalComputer(1L, "NewSER01-001", "NewCompany", 10000, 1, "NewFormFactor");

        doReturn(personalComputerToPut).when(personalComputerRepository).save(personalComputerToPut);
        doReturn(true).when(computerService).update(personalComputerToReturnSave, 1L);

        mockMvc.perform(put("/v01/PersonalComputers/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(personalComputerToReturnSave)))

                .andExpect(status().isNotModified());

    }

    @Test
    @DisplayName("DELETE /v01/PersonalComputers/1 ")
    void testDeletePersonalComputer() throws Exception {
        PersonalComputer personalComputerToPut = new PersonalComputer(1L, "SER01-001", "Intel", 10000, 1, "PC");
        doReturn(personalComputerToPut).when(personalComputerRepository).save(personalComputerToPut);
        doReturn(true).when(computerService).delete(1L);

        mockMvc.perform(delete("/v01/PersonalComputers/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /v01/PersonalComputers/1  - Not Modified")
    void testDeletePersonalComputerNotModified() throws Exception {
        PersonalComputer personalComputerToPut = new PersonalComputer(1L, "SER01-001", "Intel", 10000, 1, "PC");
        doReturn(personalComputerToPut).when(personalComputerRepository).save(personalComputerToPut);
        doReturn(true).when(computerService).delete(1L);

        mockMvc.perform(delete("/v01/PersonalComputers/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isNotModified());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
