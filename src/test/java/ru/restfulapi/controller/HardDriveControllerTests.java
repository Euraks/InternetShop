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
import ru.restfulapi.model.HardDrive;
import ru.restfulapi.repository.HardDriveRepository;
import ru.restfulapi.service.impl.HardDriveServiceInterfaceImpl;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HardDriveControllerTests {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    HardDriveServiceInterfaceImpl hardDriveService;

    @MockBean
    HardDriveRepository hardDriveRepository;


    @Test
    @DisplayName("POST /v01/HardDrives")
    void testCreateHardDrives() throws Exception {
        HardDrive hardDrive = new HardDrive(1L, "SER01-001", "Intel", 10000, 1, 14);
        doReturn(hardDrive).when(hardDriveService).create(any());
        mockMvc.perform(post("/v01/HardDrives")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(hardDrive)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber", is("SER01-001")));
    }

    @Test
    @DisplayName("GET /v01/HardDrives")
    void testGetHardDrivesSuccess() throws Exception {
        HardDrive hardDrive = new HardDrive(1L, "SER01-001", "Intel", 10000, 1, 14);
        HardDrive hardDriveOne = new HardDrive(2L, "SER01-002", "Mac", 20000, 1, 13);
        HardDrive hardDriveTwo = new HardDrive(3L, "SER01-003", "Mac Pro", 30000, 1, 17);

        doReturn(Lists.newArrayList(hardDrive, hardDriveOne, hardDriveTwo)).when(hardDriveService).readAll();

        mockMvc.perform(get("/v01/HardDrives"))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].serialNumber", is("SER01-001")))
                .andExpect(jsonPath("$[0].company", is("Intel")))
                .andExpect(jsonPath("$[0].price", is(10000)))
                .andExpect(jsonPath("$[0].quantity", is(1)))
                .andExpect(jsonPath("$[0].capacity", is(14)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].serialNumber", is("SER01-002")))
                .andExpect(jsonPath("$[1].company", is("Mac")))
                .andExpect(jsonPath("$[1].price", is(20000)))
                .andExpect(jsonPath("$[1].quantity", is(1)))
                .andExpect(jsonPath("$[1].capacity", is(13)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].serialNumber", is("SER01-003")))
                .andExpect(jsonPath("$[2].company", is("Mac Pro")))
                .andExpect(jsonPath("$[2].price", is(30000)))
                .andExpect(jsonPath("$[2].quantity", is(1)))
                .andExpect(jsonPath("$[2].capacity", is(17)));
    }

    @Test
    @DisplayName("GET /v01/HardDrives/1")
    void testGetHardDrivesById() throws Exception {
        HardDrive hardDrive = new HardDrive(1L, "SER01-001", "Intel", 10000, 1, 150);

        doReturn(hardDrive).when(hardDriveRepository).save(hardDrive);
        doReturn(Optional.of(hardDrive)).when(hardDriveService).read(1L);


        mockMvc.perform(get("/v01/HardDrives/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.serialNumber", is("SER01-001")))
                .andExpect(jsonPath("$.company", is("Intel")))
                .andExpect(jsonPath("$.price", is(10000)))
                .andExpect(jsonPath("$.quantity", is(1)))
                .andExpect(jsonPath("$.capacity", is(150)));
    }

    @Test
    @DisplayName("GET /v01/HardDrives/1 - Not Found")
    void testGetHardDrivesByIdNotFound() throws Exception {

        doReturn(Optional.empty()).when(hardDriveService).read(1L);

        mockMvc.perform(get("/v01/HardDrives/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /v01/HardDrives/1 ")
    void testUpdateHardDrives() throws Exception {
        HardDrive HardDriveToPut = new HardDrive(1L, "SER01-001", "Intel", 10000, 1, 14);
        HardDrive HardDriveToReturnSave = new HardDrive(1L, "NewSER01-001", "NewCompany", 10000, 1, 14);

        doReturn(HardDriveToPut).when(hardDriveRepository).save(HardDriveToPut);
        doReturn(true).when(hardDriveService).update(HardDriveToReturnSave, 1L);

        mockMvc.perform(put("/v01/HardDrives/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(HardDriveToReturnSave)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.serialNumber", is("NewSER01-001")))
                .andExpect(jsonPath("$.company", is("NewCompany")))
                .andExpect(jsonPath("$.price", is(10000)))
                .andExpect(jsonPath("$.quantity", is(1)))
                .andExpect(jsonPath("$.capacity", is(14)));

    }

    @Test
    @DisplayName("PUT /v01/HardDrives/1 -- NotFound")
    void testUpdateHardDrivesNotFound() throws Exception {
        HardDrive HardDriveToPut = new HardDrive(1L, "SER01-001", "Intel", 10000, 1, 14);
        HardDrive HardDriveToReturnSave = new HardDrive(1L, "NewSER01-001", "NewCompany", 10000, 1, 14);

        doReturn(HardDriveToPut).when(hardDriveRepository).save(HardDriveToPut);
        doReturn(false).when(hardDriveService).update(HardDriveToReturnSave, 2L);

        mockMvc.perform(put("/v01/HardDrives/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(HardDriveToReturnSave)))

                .andExpect(status().isNotModified());

    }

    @Test
    @DisplayName("DELETE /v01/HardDrives/1 ")
    void testDeleteHardDrives() throws Exception {
        HardDrive HardDriveToPut = new HardDrive(1L, "SER01-001", "Intel", 10000, 1, 14);
        doReturn(HardDriveToPut).when(hardDriveRepository).save(HardDriveToPut);
        doReturn(true).when(hardDriveService).delete(1L);

        mockMvc.perform(delete("/v01/HardDrives/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /v01/PersonalComputers/1  - Not Modified")
    void testDeleteHardDrivesNotModified() throws Exception {
        HardDrive HardDriveToPut = new HardDrive(1L, "SER01-001", "Intel", 10000, 1, 14);
        doReturn(HardDriveToPut).when(hardDriveRepository).save(HardDriveToPut);
        doReturn(true).when(hardDriveService).delete(1L);

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
