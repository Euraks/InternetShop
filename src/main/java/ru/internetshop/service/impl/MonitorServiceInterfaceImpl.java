package ru.internetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.internetshop.model.Monitor;
import ru.internetshop.repository.MonitorRepository;
import ru.internetshop.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class MonitorServiceInterfaceImpl implements ServiceInterface<Monitor> {

    @Autowired
    MonitorRepository repository;

    @Override
    public Monitor create(Monitor monitor) {
        repository.save(monitor);
        return monitor;
    }

    @Override
    public List<Monitor> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Monitor> read(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean update(Monitor monitor, long id) {
        if (repository.findById(id).isPresent()) {
            monitor.setId(id);
            repository.save(monitor);
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
