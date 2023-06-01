package ru.internetshop.service;

import ru.internetshop.model.PersonalComputer;

import java.util.List;

public interface PersonalComputerService {
    /**
     * Create New PersonalComputer
     *
     * @param personalComputer - PersonalComputer to create
     */
    void create(PersonalComputer personalComputer);

    /**
     * Return all PersonalComputers
     *
     * @return list PersonalComputer
     */
    List<PersonalComputer> readAll();

    /**
     * Return PersonalComputer for ID
     *
     * @param id - ID PersonalComputer
     * @return - PersonalComputer for ID
     */
    PersonalComputer read(int id);

    /**
     * Update PersonalComputer for ID
     *
     * @param personalComputer - PersonalComputer where to update the data
     * @param id     - id PersonalComputer to update
     * @return - true if data have been updated, else false
     */
    boolean update(PersonalComputer personalComputer, int id);

}
