package ru.internetshop.service;

import ru.internetshop.model.NoteBook;
import ru.internetshop.model.PersonalComputer;

import java.util.List;

public interface NoteBookService {
    /**
     * Create New NoteBook
     *
     * @param noteBook - NoteBook to create
     */
    void create(NoteBook noteBook);

    /**
     * Return all NoteBooks
     *
     * @return list NoteBook
     */
    List<NoteBook> readAll();

    /**
     * Return NoteBook for ID
     *
     * @param id - ID NoteBook
     * @return - NoteBook for ID
     */
    NoteBook read(int id);

    /**
     * Update NoteBook for ID
     *
     * @param noteBook - NoteBook where to update the data
     * @param id     - id NoteBook to update
     * @return - true if data have been updated, else false
     */
    boolean update(NoteBook noteBook, int id);

}
