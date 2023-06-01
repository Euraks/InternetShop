package ru.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.internetshop.model.HardDrive;
import ru.internetshop.model.Monitor;
import ru.internetshop.repository.HardDriveRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v01")
public class HardDriveController {

    @Autowired
    HardDriveRepository hardDriveRepository;

    @PostMapping(value = "/HardDrives")
    public ResponseEntity<?> create(@RequestBody HardDrive hardDrive) {
        hardDriveRepository.save(hardDrive);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/HardDrives")
    public ResponseEntity<List<HardDrive>> read() {
        final List<HardDrive> personalComputers = hardDriveRepository.findAll();

        return !personalComputers.isEmpty()
                ? new ResponseEntity<>(personalComputers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/HardDrives/{id}")
    public ResponseEntity<HardDrive> read(@PathVariable(name = "id") long id) {
        final Optional<HardDrive> hardDrive = hardDriveRepository.findById(id);

        return hardDrive.map(hard ->
                new ResponseEntity<>(hard, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
