package ru.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.internetshop.model.HardDrive;
import ru.internetshop.model.Monitor;
import ru.internetshop.model.NoteBook;
import ru.internetshop.model.PersonalComputer;
import ru.internetshop.repository.HardDriveRepository;
import ru.internetshop.repository.MonitorRepository;
import ru.internetshop.repository.NoteBookRepository;
import ru.internetshop.repository.PersonalComputerRepository;

@RestController
@RequestMapping("/v01")
public class ShopController {

    @Autowired
    HardDriveRepository hardDriveRepository;

    @PostMapping(value = "/HardDrives")
    public ResponseEntity<?> create(@RequestBody HardDrive hardDrive) {
        hardDriveRepository.save(hardDrive);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
