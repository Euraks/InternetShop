package ru.internetshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "PersonalComputersController", description = "Controller Personal Computer APIs")
public class PersonalComputersController {
    @Autowired
    ServiceInterface<PersonalComputer> personalComputerService;
    @Operation(
            summary = "Add a Personal Computer",
            description = "Add a Personal Computer object by adding its fields to the body request," +
                    " the JSON format according to the model",
            tags = {"Personal Computer", "post"})
    @PostMapping(value = "/PersonalComputers")
    public ResponseEntity<PersonalComputer> create(@RequestBody PersonalComputer personalComputer) {
        personalComputerService.create(personalComputer);
        return new ResponseEntity<>(personalComputer,HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All a Personal Computer",
            description = "Get All Personal Computer",
            tags = {"Get All Personal Computer", "get"})
    @GetMapping(value = "/PersonalComputers")
    public ResponseEntity<List<PersonalComputer>> read() {
        final List<PersonalComputer> personalComputers = personalComputerService.readAll();

        return !personalComputers.isEmpty()
                ? new ResponseEntity<>(personalComputers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Get a Personal Computer for Id",
            description = "Get a Personal Computer for Id to request param",
            tags = {"Get Personal Computer for Id", "get"})
    @GetMapping(value = "/PersonalComputers/{id}")
    public ResponseEntity<PersonalComputer> read(@PathVariable(name = "id") long id) {
        final Optional<PersonalComputer> personalComputer = personalComputerService.read(id);

        return personalComputer.map(computer ->
                new ResponseEntity<>(computer, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Update a Personal Computer for Id",
            description = "Update a Personal Computer object by adding its fields to the body request," +
                    " the JSON format according to the model and Id to request param",
            tags = {"Update Personal Computer for Id", "put"})
    @PutMapping(value = "/PersonalComputers/{id}")
    public ResponseEntity<PersonalComputer> update(@PathVariable(name = "id") long id, @RequestBody PersonalComputer personalComputer) {
        final boolean updated = personalComputerService.update(personalComputer, id);

        return updated
                ? new ResponseEntity<>(personalComputer,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(
            summary = "Delete a Personal Computer for Id",
            description = "Update a Personal Computer for Id to request param",
            tags = {"Delete Personal Computer for Id", "put"})
    @DeleteMapping(value = "/PersonalComputers/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = personalComputerService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
