package ru.internetshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.internetshop.model.HardDrive;

public interface HardDriveRepository extends JpaRepository<HardDrive,Long> {
}
