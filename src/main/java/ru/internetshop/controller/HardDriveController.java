package ru.internetshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.internetshop.model.HardDrive;
import ru.internetshop.service.impl.HardDriveServiceInterfaceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v01")
@Tag(name = "HardDrive", description = "Controller HardDrive APIs")
public class HardDriveController {

    @Autowired
    HardDriveServiceInterfaceImpl hardDriveService;

    @Operation(
            summary = "Add a HardDrive",
            description = "Add a HardDrive object by adding its fields to the body request," +
                    " the JSON format according to the model",
            tags = {"HardDrive", "post"})
    @PostMapping(value = "/HardDrives")
    public ResponseEntity<?> create(@RequestBody HardDrive hardDrive) {
        hardDriveService.create(hardDrive);
        return new ResponseEntity<>(hardDrive,HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All a HardDrive",
            description = "Get All HardDrive",
            tags = {"Get All HardDrive", "get"})
    @GetMapping(value = "/HardDrives")
    public ResponseEntity<List<HardDrive>> read() {
        final List<HardDrive> hardDrive = hardDriveService.readAll();

        return !hardDrive.isEmpty()
                ? new ResponseEntity<>(hardDrive, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Get a HardDrive for Id",
            description = "Get a HardDrive for Id to request param",
            tags = {"Get HardDrive for Id", "get"})
    @GetMapping(value = "/HardDrives/{id}")
    public ResponseEntity<HardDrive> read(@PathVariable(name = "id") long id) {
        final Optional<HardDrive> hardDrive = hardDriveService.read(id);

        return hardDrive.map(hard ->
                new ResponseEntity<>(hard, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Update a HardDrive for Id",
            description = "Update a HardDrive object by adding its fields to the body request," +
                    " the JSON format according to the model and Id to request param",
            tags = {"Update HardDrive for Id", "put"})
    @PutMapping(value = "/HardDrives/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody HardDrive hardDrive) {
        final boolean updated = hardDriveService.update(hardDrive, id);

        return updated
                ? new ResponseEntity<>(hardDrive,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(
            summary = "Delete a HardDrive for Id",
            description = "Update a HardDrive for Id to request param",
            tags = {"Delete HardDrive for Id", "put"})
    @DeleteMapping(value = "/HardDrives/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = hardDriveService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
