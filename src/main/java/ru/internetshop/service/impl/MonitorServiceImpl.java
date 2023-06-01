package ru.internetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.internetshop.model.Monitor;
import ru.internetshop.repository.MonitorRepository;
import ru.internetshop.service.MonitorService;

import java.util.List;
import java.util.Optional;

@Service
public class MonitorServiceImpl implements MonitorService {
    @Autowired
    MonitorRepository repository;

    @Override
    public void create(Monitor monitor) {
        repository.save(monitor);
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
    public boolean update(Monitor personalComputer, int id) {
        return false;
    }
}
