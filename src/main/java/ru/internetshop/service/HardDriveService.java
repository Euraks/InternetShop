package ru.internetshop.service;

import ru.internetshop.model.HardDrive;
import ru.internetshop.model.Monitor;

import java.util.List;

public interface HardDriveService {
    /**
     * Create New HardDrive
     *
     * @param hardDrive - HardDrive to create
     */
    void create(HardDrive hardDrive);

    /**
     * Return all HardDrive
     *
     * @return list HardDrive
     */
    List<HardDrive> readAll();

    /**
     * Return HardDrive for ID
     *
     * @param id - ID HardDrive
     * @return - HardDrive for ID
     */
    HardDrive read(int id);

    /**
     * Update HardDrive for ID
     *
     * @param hardDrive - HardDrive where to update the data
     * @param id     - id HardDrive to update
     * @return - true if data have been updated, else false
     */
    boolean update(Monitor hardDrive, int id);

}
