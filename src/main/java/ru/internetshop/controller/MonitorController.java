package ru.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.internetshop.model.Monitor;
import ru.internetshop.repository.MonitorRepository;

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
}
