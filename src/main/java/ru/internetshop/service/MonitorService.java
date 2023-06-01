package ru.internetshop.service;

import ru.internetshop.model.Monitor;
import ru.internetshop.model.NoteBook;
import ru.internetshop.model.PersonalComputer;

import java.util.List;

public interface MonitorService {
    /**
     * Create New Monitor
     *
     * @param monitor - NoteBook to create
     */
    void create(Monitor monitor);

    /**
     * Return all Monitor
     *
     * @return list Monitor
     */
    List<Monitor> readAll();

    /**
     * Return Monitor for ID
     *
     * @param id - ID Monitor
     * @return - Monitor for ID
     */
    Monitor read(int id);

    /**
     * Update NoteBook for ID
     *
     * @param monitor - Monitor where to update the data
     * @param id     - id Monitor to update
     * @return - true if data have been updated, else false
     */
    boolean update(Monitor monitor, int id);

}
