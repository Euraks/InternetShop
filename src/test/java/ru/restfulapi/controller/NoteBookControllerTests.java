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
import ru.restfulapi.model.NoteBook;
import ru.restfulapi.repository.NoteBookRepository;
import ru.restfulapi.service.impl.NoteBookServiceInterfaceImpl;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteBookControllerTests {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    NoteBookServiceInterfaceImpl noteBookService;

    @MockBean
    NoteBookRepository noteBookRepository;


    @Test
    @DisplayName("POST /v01/NoteBooks")
    void testCreateNoteBooks() throws Exception {
        NoteBook noteBook = new NoteBook(1L, "SER01-001", "Intel", 10000, 1, 14);
        doReturn(noteBook).when(noteBookService).create(any());
        mockMvc.perform(post("/v01/NoteBooks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteBook)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber", is("SER01-001")));
    }

    @Test
    @DisplayName("GET /v01/NoteBooks")
    void testGetNoteBooksSuccess() throws Exception {
        NoteBook noteBook = new NoteBook(1L, "SER01-001", "Intel", 10000, 1, 14);
        NoteBook noteBookOne = new NoteBook(2L, "SER01-002", "Mac", 20000, 1, 13);
        NoteBook noteBookTwo = new NoteBook(3L, "SER01-003", "Mac Pro", 30000, 1, 17);

        doReturn(Lists.newArrayList(noteBook, noteBookOne, noteBookTwo)).when(noteBookService).readAll();

        mockMvc.perform(get("/v01/NoteBooks"))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].serialNumber", is("SER01-001")))
                .andExpect(jsonPath("$[0].company", is("Intel")))
                .andExpect(jsonPath("$[0].price", is(10000)))
                .andExpect(jsonPath("$[0].quantity", is(1)))
                .andExpect(jsonPath("$[0].size", is(14)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].serialNumber", is("SER01-002")))
                .andExpect(jsonPath("$[1].company", is("Mac")))
                .andExpect(jsonPath("$[1].price", is(20000)))
                .andExpect(jsonPath("$[1].quantity", is(1)))
                .andExpect(jsonPath("$[1].size", is(13)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].serialNumber", is("SER01-003")))
                .andExpect(jsonPath("$[2].company", is("Mac Pro")))
                .andExpect(jsonPath("$[2].price", is(30000)))
                .andExpect(jsonPath("$[2].quantity", is(1)))
                .andExpect(jsonPath("$[2].size", is(17)));
    }

    @Test
    @DisplayName("GET /v01/NoteBooks/1")
    void testGetNoteBooksById() throws Exception {
        NoteBook noteBook = new NoteBook(1L, "SER01-001", "Intel", 10000, 1, 14);

        doReturn(noteBook).when(noteBookRepository).save(noteBook);
        doReturn(Optional.of(noteBook)).when(noteBookService).read(1L);


        mockMvc.perform(get("/v01/NoteBooks/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.serialNumber", is("SER01-001")))
                .andExpect(jsonPath("$.company", is("Intel")))
                .andExpect(jsonPath("$.price", is(10000)))
                .andExpect(jsonPath("$.quantity", is(1)))
                .andExpect(jsonPath("$.size", is(14)));
    }

    @Test
    @DisplayName("GET /v01/NoteBooks/1 - Not Found")
    void testGetNoteBooksByIdNotFound() throws Exception {

        doReturn(Optional.empty()).when(noteBookService).read(1L);

        mockMvc.perform(get("/v01/NoteBooks/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /v01/NoteBooks/1 ")
    void testUpdateNoteBooks() throws Exception {
        NoteBook noteBookToPut = new NoteBook(1L, "SER01-001", "Intel", 10000, 1, 14);
        NoteBook noteBookToReturnSave = new NoteBook(1L, "NewSER01-001", "NewCompany", 10000, 1, 14);

        doReturn(noteBookToPut).when(noteBookRepository).save(noteBookToPut);
        doReturn(true).when(noteBookService).update(noteBookToReturnSave, 1L);

        mockMvc.perform(put("/v01/NoteBooks/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteBookToReturnSave)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.serialNumber", is("NewSER01-001")))
                .andExpect(jsonPath("$.company", is("NewCompany")))
                .andExpect(jsonPath("$.price", is(10000)))
                .andExpect(jsonPath("$.quantity", is(1)))
                .andExpect(jsonPath("$.size", is(14)));

    }

    @Test
    @DisplayName("PUT /v01/NoteBooks/1 -- NotFound")
    void testUpdateNoteBooksNotFound() throws Exception {
        NoteBook noteBookToPut = new NoteBook(1L, "SER01-001", "Intel", 10000, 1, 14);
        NoteBook noteBookToReturnSave = new NoteBook(1L, "NewSER01-001", "NewCompany", 10000, 1, 14);

        doReturn(noteBookToPut).when(noteBookRepository).save(noteBookToPut);
        doReturn(false).when(noteBookService).update(noteBookToReturnSave, 2L);

        mockMvc.perform(put("/v01/NoteBooks/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(noteBookToReturnSave)))

                .andExpect(status().isNotModified());

    }

    @Test
    @DisplayName("DELETE /v01/NoteBooks/1 ")
    void testDeleteNoteBooks() throws Exception {
        NoteBook noteBookToPut = new NoteBook(1L, "SER01-001", "Intel", 10000, 1, 14);
        doReturn(noteBookToPut).when(noteBookRepository).save(noteBookToPut);
        doReturn(true).when(noteBookService).delete(1L);

        mockMvc.perform(delete("/v01/NoteBooks/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /v01/PersonalComputers/1  - Not Modified")
    void testDeleteNoteBooksNotModified() throws Exception {
        NoteBook noteBookToPut = new NoteBook(1L, "SER01-001", "Intel", 10000, 1, 14);
        doReturn(noteBookToPut).when(noteBookRepository).save(noteBookToPut);
        doReturn(true).when(noteBookService).delete(1L);

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
