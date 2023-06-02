package ru.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.internetshop.model.PersonalComputer;
import ru.internetshop.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v01")
public class PersonalComputersController {
    @Autowired
    ServiceInterface<PersonalComputer> personalComputerService;

    @PostMapping(value = "/PersonalComputers")
    public ResponseEntity<?> create(@RequestBody PersonalComputer personalComputer) {
        personalComputerService.create(personalComputer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/PersonalComputers")
    public ResponseEntity<List<PersonalComputer>> read() {
        final List<PersonalComputer> personalComputers = personalComputerService.readAll();

        return !personalComputers.isEmpty()
                ? new ResponseEntity<>(personalComputers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/PersonalComputers/{id}")
    public ResponseEntity<PersonalComputer> read(@PathVariable(name = "id") long id) {
        final Optional<PersonalComputer> personalComputer = personalComputerService.read(id);

        return personalComputer.map(computer ->
                new ResponseEntity<>(computer, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/PersonalComputers/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody PersonalComputer personalComputer) {
        final boolean updated = personalComputerService.update(personalComputer, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/PersonalComputers/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = personalComputerService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
