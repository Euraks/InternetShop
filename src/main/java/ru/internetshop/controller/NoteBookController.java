package ru.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.internetshop.model.NoteBook;
import ru.internetshop.model.PersonalComputer;
import ru.internetshop.repository.NoteBookRepository;

import java.util.List;

@RestController
@RequestMapping("/v01")
public class NoteBookController {

    @Autowired
    NoteBookRepository noteBookRepository;

    @PostMapping(value = "/NoteBooks")
    public ResponseEntity<?> create(@RequestBody NoteBook noteBook) {
        noteBookRepository.save(noteBook);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/NoteBooks")
    public ResponseEntity<List<NoteBook>> read() {
        final List<NoteBook> personalComputers = noteBookRepository.findAll();

        return !personalComputers.isEmpty()
                ? new ResponseEntity<>(personalComputers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
