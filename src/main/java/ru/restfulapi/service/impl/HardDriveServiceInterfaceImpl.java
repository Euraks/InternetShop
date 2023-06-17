package ru.restfulapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restfulapi.model.HardDrive;
import ru.restfulapi.repository.HardDriveRepository;
import ru.restfulapi.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class HardDriveServiceInterfaceImpl implements ServiceInterface<HardDrive> {

    @Autowired
    HardDriveRepository repository;

    @Override
    public HardDrive create(HardDrive hardDrive) {
        repository.save(hardDrive);
        return hardDrive;
    }

    @Override
    public List<HardDrive> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<HardDrive> read(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean update(HardDrive hardDrive, long id) {
        if (repository.findById(id).isPresent()) {
            hardDrive.setId(id);
            repository.save(hardDrive);
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
