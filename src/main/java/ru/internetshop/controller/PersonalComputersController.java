package ru.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.internetshop.model.PersonalComputer;
import ru.internetshop.repository.PersonalComputerRepository;

import java.util.List;

@RestController
@RequestMapping("/v01")
public class PersonalComputersController {
    @Autowired
    PersonalComputerRepository personalComputerRepository;

    @PostMapping(value = "/PersonalComputers")
    public ResponseEntity<?> create(@RequestBody PersonalComputer personalComputer) {
        personalComputerRepository.save(personalComputer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/PersonalComputers")
    public ResponseEntity<List<PersonalComputer>> read() {
        final List<PersonalComputer> personalComputers = personalComputerRepository.findAll();

        return !personalComputers.isEmpty()
                ? new ResponseEntity<>(personalComputers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
