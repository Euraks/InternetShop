package ru.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.internetshop.model.Monitor;
import ru.internetshop.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v01")
public class MonitorController {

    @Autowired
    ServiceInterface<Monitor> monitorService;

    @PostMapping(value = "/Monitors")
    public ResponseEntity<?> create(@RequestBody Monitor monitor) {
        monitorService.create(monitor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/Monitors")
    public ResponseEntity<List<Monitor>> read() {
        final List<Monitor> monitor = monitorService.readAll();

        return !monitor.isEmpty()
                ? new ResponseEntity<>(monitor, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/Monitors/{id}")
    public ResponseEntity<Monitor> read(@PathVariable(name = "id") long id) {
        final Optional<Monitor> monitor = monitorService.read(id);

        return monitor.map(mon ->
                new ResponseEntity<>(mon, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/Monitors/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody Monitor monitor) {
        final boolean updated = monitorService.update(monitor, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/Monitors/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = monitorService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
