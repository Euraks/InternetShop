package ru.internetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.internetshop.model.PersonalComputer;
import ru.internetshop.repository.PersonalComputerRepository;
import ru.internetshop.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalComputerServiceImpl implements ServiceInterface<PersonalComputer> {

    @Autowired
    PersonalComputerRepository repository;

    @Override
    public void create(PersonalComputer personalComputer) {
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
    public boolean update(PersonalComputer personalComputer, long id) {
        return false;
    }
}
