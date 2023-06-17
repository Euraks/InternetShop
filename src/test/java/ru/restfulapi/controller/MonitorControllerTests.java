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
import ru.restfulapi.model.Monitor;
import ru.restfulapi.repository.MonitorRepository;
import ru.restfulapi.service.impl.MonitorServiceInterfaceImpl;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class MonitorControllerTests {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MonitorServiceInterfaceImpl monitorService;

    @MockBean
    MonitorRepository monitorRepository;


    @Test
    @DisplayName("POST /v01/Monitors")
    void testCreateMonitors() throws Exception {
        Monitor monitor = new Monitor(1L, "SER01-001", "Intel", 10000, 1, 14);
        doReturn(monitor).when(monitorService).create(any());
        mockMvc.perform(post("/v01/Monitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(monitor)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber", is("SER01-001")));
    }

    @Test
    @DisplayName("GET /v01/Monitors")
    void testGetMonitorsSuccess() throws Exception {
        Monitor monitor = new Monitor(1L, "SER01-001", "Intel", 10000, 1, 14);
        Monitor monitorOne = new Monitor(2L, "SER01-002", "Mac", 20000, 1, 13);
        Monitor monitorTwo = new Monitor(3L, "SER01-003", "Mac Pro", 30000, 1, 17);

        doReturn(Lists.newArrayList(monitor, monitorOne, monitorTwo)).when(monitorService).readAll();

        mockMvc.perform(get("/v01/Monitors"))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].serialNumber", is("SER01-001")))
                .andExpect(jsonPath("$[0].company", is("Intel")))
                .andExpect(jsonPath("$[0].price", is(10000)))
                .andExpect(jsonPath("$[0].quantity", is(1)))
                .andExpect(jsonPath("$[0].diagonal", is(14)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].serialNumber", is("SER01-002")))
                .andExpect(jsonPath("$[1].company", is("Mac")))
                .andExpect(jsonPath("$[1].price", is(20000)))
                .andExpect(jsonPath("$[1].quantity", is(1)))
                .andExpect(jsonPath("$[1].diagonal", is(13)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].serialNumber", is("SER01-003")))
                .andExpect(jsonPath("$[2].company", is("Mac Pro")))
                .andExpect(jsonPath("$[2].price", is(30000)))
                .andExpect(jsonPath("$[2].quantity", is(1)))
                .andExpect(jsonPath("$[2].diagonal", is(17)));
    }

    @Test
    @DisplayName("GET /v01/Monitors/1")
    void testGetMonitorsById() throws Exception {
        Monitor monitor = new Monitor(1L, "SER01-001", "Intel", 10000, 1, 14);

        doReturn(monitor).when(monitorRepository).save(monitor);
        doReturn(Optional.of(monitor)).when(monitorService).read(1L);


        mockMvc.perform(get("/v01/Monitors/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.serialNumber", is("SER01-001")))
                .andExpect(jsonPath("$.company", is("Intel")))
                .andExpect(jsonPath("$.price", is(10000)))
                .andExpect(jsonPath("$.quantity", is(1)))
                .andExpect(jsonPath("$.diagonal", is(14)));
    }

    @Test
    @DisplayName("GET /v01/Monitors/1 - Not Found")
    void testGetMonitorsByIdNotFound() throws Exception {

        doReturn(Optional.empty()).when(monitorService).read(1L);

        mockMvc.perform(get("/v01/Monitors/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /v01/Monitors/1 ")
    void testUpdateMonitors() throws Exception {
        Monitor monitorToPut = new Monitor(1L, "SER01-001", "Intel", 10000, 1, 14);
        Monitor monitorToReturnSave = new Monitor(1L, "NewSER01-001", "NewCompany", 10000, 1, 14);

        doReturn(monitorToPut).when(monitorRepository).save(monitorToPut);
        doReturn(true).when(monitorService).update(monitorToReturnSave, 1L);

        mockMvc.perform(put("/v01/Monitors/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(monitorToReturnSave)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.serialNumber", is("NewSER01-001")))
                .andExpect(jsonPath("$.company", is("NewCompany")))
                .andExpect(jsonPath("$.price", is(10000)))
                .andExpect(jsonPath("$.quantity", is(1)))
                .andExpect(jsonPath("$.diagonal", is(14)));

    }

    @Test
    @DisplayName("PUT /v01/Monitors/1 -- NotFound")
    void testUpdateMonitorsNotFound() throws Exception {
        Monitor monitorToPut = new Monitor(1L, "SER01-001", "Intel", 10000, 1, 14);
        Monitor monitorToReturnSave = new Monitor(1L, "NewSER01-001", "NewCompany", 10000, 1, 14);

        doReturn(monitorToPut).when(monitorRepository).save(monitorToPut);
        doReturn(false).when(monitorService).update(monitorToReturnSave, 2L);

        mockMvc.perform(put("/v01/Monitors/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(monitorToReturnSave)))

                .andExpect(status().isNotModified());

    }

    @Test
    @DisplayName("DELETE /v01/Monitors/1 ")
    void testDeleteMonitors() throws Exception {
        Monitor monitorToPut = new Monitor(1L, "SER01-001", "Intel", 10000, 1, 14);
        doReturn(monitorToPut).when(monitorRepository).save(monitorToPut);
        doReturn(true).when(monitorService).delete(1L);

        mockMvc.perform(delete("/v01/Monitors/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /v01/PersonalComputers/1  - Not Modified")
    void testDeleteMonitorsNotModified() throws Exception {
        Monitor monitorToPut = new Monitor(1L, "SER01-001", "Intel", 10000, 1, 14);
        doReturn(monitorToPut).when(monitorRepository).save(monitorToPut);
        doReturn(true).when(monitorService).delete(1L);

        mockMvc.perform(delete("/v01/PersonalComputers/{id}", 4L)
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
