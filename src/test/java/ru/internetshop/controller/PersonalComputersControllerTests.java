package ru.internetshop.controller;


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
import ru.internetshop.model.PersonalComputer;
import ru.internetshop.service.impl.PersonalComputerServiceImpl;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonalComputersControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PersonalComputerServiceImpl computerService;

    PersonalComputer personalComputer = new PersonalComputer("SER01-001", "Intel", 10000, 1, "PC");
    PersonalComputer personalComputerOne = new PersonalComputer("SER01-002", "Spectrum", 20000, 1, "PC");
    PersonalComputer personalComputerTwo = new PersonalComputer("SER01-003", "Mac", 30000, 1, "PC");

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

    @Test
    @DisplayName("GET /v01/PersonalComputers")
    void testGetWidgetsSuccess() throws Exception {
        doReturn(Lists.newArrayList(personalComputer, personalComputerOne, personalComputerTwo)).when(computerService).readAll();
        mockMvc.perform(get("/v01/PersonalComputers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].serialNumber", is("SER01-001")))
                .andExpect(jsonPath("$[0].company", is("Intel")))
                .andExpect(jsonPath("$[0].price", is(10000)))
                .andExpect(jsonPath("$[0].quantity", is(1)))
                .andExpect(jsonPath("$[0].formFactor", is("PC")))
                .andExpect(jsonPath("$[1].serialNumber", is("SER01-002")))
                .andExpect(jsonPath("$[1].company", is("Spectrum")))
                .andExpect(jsonPath("$[1].price", is(20000)))
                .andExpect(jsonPath("$[1].quantity", is(1)))
                .andExpect(jsonPath("$[1].formFactor", is("PC")))
                .andExpect(jsonPath("$[2].serialNumber", is("SER01-003")))
                .andExpect(jsonPath("$[2].company", is("Mac")))
                .andExpect(jsonPath("$[2].price", is(30000)))
                .andExpect(jsonPath("$[2].quantity", is(1)))
                .andExpect(jsonPath("$[2].formFactor", is("PC")));
//
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
