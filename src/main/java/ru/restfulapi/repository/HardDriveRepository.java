package ru.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restfulapi.model.HardDrive;

public interface HardDriveRepository extends JpaRepository<HardDrive,Long> {
}
