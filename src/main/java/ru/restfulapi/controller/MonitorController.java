package ru.restfulapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.restfulapi.model.Monitor;
import ru.restfulapi.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v01")
@Tag(name = "Monitor", description = "Controller Monitor APIs")
public class MonitorController {

    @Autowired
    ServiceInterface<Monitor> monitorService;

    @Operation(
            summary = "Add a Monitor",
            description = "Add a Monitor object by adding its fields to the body request," +
                    " the JSON format according to the model",
            tags = { "Monitor", "post" })
    @PostMapping(value = "/Monitors")
    public ResponseEntity<?> create(@RequestBody Monitor monitor) {
        monitorService.create(monitor);
        return new ResponseEntity<>(monitor,HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All a Monitor",
            description = "Get All Monitor",
            tags = { "Get All Monitor", "get" })
    @GetMapping(value = "/Monitors")
    public ResponseEntity<List<Monitor>> read() {
        final List<Monitor> monitor = monitorService.readAll();

        return !monitor.isEmpty()
                ? new ResponseEntity<>(monitor, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Get a Monitor for Id",
            description = "Get a Monitor for Id to request param",
            tags = { "Get Monitor for Id", "get" })
    @GetMapping(value = "/Monitors/{id}")
    public ResponseEntity<Monitor> read(@PathVariable(name = "id") long id) {
        final Optional<Monitor> monitor = monitorService.read(id);

        return monitor.map(mon ->
                new ResponseEntity<>(mon, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Update a Monitor for Id",
            description = "Update a Monitor object by adding its fields to the body request," +
                    " the JSON format according to the model and Id to request param",
            tags = { "Update Monitor for Id", "put" })
    @PutMapping(value = "/Monitors/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody Monitor monitor) {
        final boolean updated = monitorService.update(monitor, id);

        return updated
                ? new ResponseEntity<>(monitor,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(
            summary = "Delete a Monitor for Id",
            description = "Update a Monitor for Id to request param",
            tags = {"Delete Monitor for Id", "put"})
    @DeleteMapping(value = "/Monitors/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = monitorService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
