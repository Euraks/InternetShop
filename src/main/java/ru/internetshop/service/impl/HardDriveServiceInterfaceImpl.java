package ru.internetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.internetshop.model.HardDrive;
import ru.internetshop.model.PersonalComputer;
import ru.internetshop.repository.RepositoryByTypeProduct;
import ru.internetshop.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

public class HardDriveServiceInterfaceImpl implements ServiceInterface<HardDrive> {

    @Autowired
    RepositoryByTypeProduct<HardDrive> repository;

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
