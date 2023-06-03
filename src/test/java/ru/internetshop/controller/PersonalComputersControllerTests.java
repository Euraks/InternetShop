package ru.internetshop.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.internetshop.model.PersonalComputer;
import ru.internetshop.service.impl.PersonalComputerServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonalComputersControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PersonalComputerServiceImpl computerService;

    PersonalComputer personalComputer = new PersonalComputer("SER01-001", "Intel", 10000, 1, "PC");

    @Test
    @DisplayName("POST /v01/PersonalComputers")
    void testCreateWidget() throws Exception {

        doReturn(personalComputer).when(computerService).create(any());
        mockMvc.perform(post("/v01/PersonalComputers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(personalComputer)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber", is("SER01-001")));
    }
    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
