package ru.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.internetshop.model.NoteBook;
import ru.internetshop.model.PersonalComputer;
import ru.internetshop.repository.NoteBookRepository;
import ru.internetshop.repository.PersonalComputerRepository;

@RestController
@RequestMapping("/v01")
public class ShopController {

    @Autowired
    PersonalComputerRepository personalComputerRepository;

    @Autowired
    NoteBookRepository noteBookRepository;

    @PostMapping(value = "/PersonalComputers")
    public ResponseEntity<?> create(@RequestBody PersonalComputer personalComputer) {
        personalComputerRepository.save(personalComputer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/NoteBooks")
    public ResponseEntity<?> create(@RequestBody NoteBook noteBook) {
        noteBookRepository.save(noteBook);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
