package ru.internetshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.internetshop.model.NoteBook;
import ru.internetshop.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v01")
@Tag(name = "NoteBookController", description = "Controller NoteBook APIs")
public class NoteBookController {

    @Autowired
    ServiceInterface<NoteBook> noteBookService;

    @Operation(
            summary = "Add a NoteBook",
            description = "Add a NoteBook object by adding its fields to the body request," +
                    " the JSON format according to the model",
            tags = { "NoteBook", "post" })
    @PostMapping(value = "/NoteBooks")
    public ResponseEntity<?> create(@RequestBody NoteBook noteBook) {
        noteBookService.create(noteBook);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All a NoteBook",
            description = "Get All NoteBook",
            tags = { "Get All NoteBook", "get" })
    @GetMapping(value = "/NoteBooks")
    public ResponseEntity<List<NoteBook>> read() {
        final List<NoteBook> personalComputers = noteBookService.readAll();

        return !personalComputers.isEmpty()
                ? new ResponseEntity<>(personalComputers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Get a NoteBook for Id",
            description = "Get a NoteBook for Id to request param",
            tags = { "Get NoteBook for Id", "get" })
    @GetMapping(value = "/NoteBooks/{id}")
    public ResponseEntity<NoteBook> read(@PathVariable(name = "id") long id) {
        final Optional<NoteBook> noteBook = noteBookService.read(id);

        return noteBook.map(book ->
                new ResponseEntity<>(book, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Update a NoteBook for Id",
            description = "Update a NoteBook object by adding its fields to the body request," +
                    " the JSON format according to the model and Id to request param",
            tags = { "Update NoteBook for Id", "put" })
    @PutMapping(value = "/NoteBooks/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody NoteBook noteBook) {
        final boolean updated = noteBookService.update(noteBook, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(
            summary = "Delete a NoteBook for Id",
            description = "Update a NoteBook for Id to request param",
            tags = {"Delete NoteBook for Id", "put"})
    @DeleteMapping(value = "/NoteBooks/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = noteBookService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
