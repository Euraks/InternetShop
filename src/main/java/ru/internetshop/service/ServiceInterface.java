package ru.internetshop.service;

import ru.internetshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T extends Product> {
    /**
     * Create New <T>
     *
     * @param type - PersonalComputer to create
     */
    T create(T type);

    /**
     * Return all <T>
     *
     * @return list PersonalComputer
     */
    List<T> readAll();

    /**
     * Return <T> for ID
     *
     * @param id - ID <T>
     * @return - <T> for ID
     */
    Optional<T> read(long id);

    /**
     * Update <T> for ID
     *
     * @param type - <T> where to update the data
     * @param id   - id <T> to update
     * @return - true if data have been updated, else false
     */
    boolean update(T type, long id);

    /**
     * Delete <T> с заданным ID
     *
     * @param id - <T> where to delete
     * @return - true if <T> delete, else false
     */
    boolean delete(long id);
}
