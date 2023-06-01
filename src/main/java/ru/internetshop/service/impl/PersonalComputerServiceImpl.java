package ru.internetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.internetshop.model.PersonalComputer;
import ru.internetshop.repository.PersonalComputerRepository;
import ru.internetshop.service.PersonalComputerService;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalComputerServiceImpl implements PersonalComputerService {

    @Autowired
    PersonalComputerRepository repository;

    @Override
    public void  create(PersonalComputer personalComputer) {
        repository.save(personalComputer);
    }

    @Override
    public List<PersonalComputer> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<PersonalComputer> read(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean update(PersonalComputer personalComputer, int id) {
        return false;
    }
}
