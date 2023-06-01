package ru.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.internetshop.model.Monitor;
import ru.internetshop.model.NoteBook;
import ru.internetshop.repository.MonitorRepository;

import java.util.List;

@RestController
@RequestMapping("/v01")
public class MonitorController {

    @Autowired
    MonitorRepository monitorRepository;

    @PostMapping(value = "/Monitors")
    public ResponseEntity<?> create(@RequestBody Monitor monitor) {
        monitorRepository.save(monitor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/Monitors")
    public ResponseEntity<List<Monitor>> read() {
        final List<Monitor> personalComputers = monitorRepository.findAll();

        return !personalComputers.isEmpty()
                ? new ResponseEntity<>(personalComputers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
