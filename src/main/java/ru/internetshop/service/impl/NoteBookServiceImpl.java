package ru.internetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.internetshop.model.NoteBook;
import ru.internetshop.repository.NoteBookRepository;
import ru.internetshop.service.NoteBookService;

import java.util.List;
import java.util.Optional;

@Service
public class NoteBookServiceImpl implements NoteBookService {
    @Autowired
    NoteBookRepository repository;

    @Override
    public void create(NoteBook noteBook) {
        repository.save(noteBook);
    }

    @Override
    public List<NoteBook> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<NoteBook> read(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean update(NoteBook personalComputer, long id) {
        return false;
    }
}
