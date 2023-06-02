package ru.internetshop.controller;

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
public class NoteBookController {

    @Autowired
    ServiceInterface<NoteBook> noteBookService;

    @PostMapping(value = "/NoteBooks")
    public ResponseEntity<?> create(@RequestBody NoteBook noteBook) {
        noteBookService.create(noteBook);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/NoteBooks")
    public ResponseEntity<List<NoteBook>> read() {
        final List<NoteBook> personalComputers = noteBookService.readAll();

        return !personalComputers.isEmpty()
                ? new ResponseEntity<>(personalComputers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/NoteBooks/{id}")
    public ResponseEntity<NoteBook> read(@PathVariable(name = "id") long id) {
        final Optional<NoteBook> noteBook = noteBookService.read(id);

        return noteBook.map(book ->
                new ResponseEntity<>(book, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/NoteBooks/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody NoteBook noteBook) {
        final boolean updated = noteBookService.update(noteBook, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/NoteBooks/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = noteBookService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
