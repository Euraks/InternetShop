package ru.internetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.internetshop.model.NoteBook;
import ru.internetshop.model.PersonalComputer;
import ru.internetshop.repository.NoteBookRepository;
import ru.internetshop.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class NoteBookServiceInterfaceImpl implements ServiceInterface<NoteBook> {

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
    public boolean update(NoteBook noteBook, long id) {
        if (repository.findById(id).isPresent()){
            noteBook.setId(id);
            repository.save(noteBook);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
