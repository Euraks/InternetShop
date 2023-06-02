package ru.internetshop.controller;

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
public class HardDriveController {

    @Autowired
    HardDriveServiceInterfaceImpl hardDriveService;

    @PostMapping(value = "/HardDrives")
    public ResponseEntity<?> create(@RequestBody HardDrive hardDrive) {
        hardDriveService.create(hardDrive);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/HardDrives")
    public ResponseEntity<List<HardDrive>> read() {
        final List<HardDrive> personalComputers = hardDriveService.readAll();

        return !personalComputers.isEmpty()
                ? new ResponseEntity<>(personalComputers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/HardDrives/{id}")
    public ResponseEntity<HardDrive> read(@PathVariable(name = "id") long id) {
        final Optional<HardDrive> hardDrive = hardDriveService.read(id);

        return hardDrive.map(hard ->
                new ResponseEntity<>(hard, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/HardDrives/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody HardDrive hardDrive) {
        final boolean updated = hardDriveService.update(hardDrive, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/HardDrives/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = hardDriveService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
