package ru.restfulapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restfulapi.model.PersonalComputer;
import ru.restfulapi.repository.PersonalComputerRepository;
import ru.restfulapi.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalComputerServiceImpl implements ServiceInterface<PersonalComputer> {

    @Autowired
    PersonalComputerRepository repository;

    @Override
    public PersonalComputer create(PersonalComputer personalComputer) {
        repository.save(personalComputer);
        return personalComputer;
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
        if (repository.findById(id).isPresent()) {
            personalComputer.setId(id);
            repository.save(personalComputer);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
