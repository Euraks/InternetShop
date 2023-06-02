package ru.internetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.internetshop.model.HardDrive;
import ru.internetshop.repository.HardDriveRepository;
import ru.internetshop.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class HardDriveServiceInterfaceImpl implements ServiceInterface<HardDrive> {

    @Autowired
    HardDriveRepository repository;

    @Override
    public void  create(HardDrive type) {
        repository.save(type);
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
    public boolean update(HardDrive personalComputer, long id) {
        return false;
    }
}
