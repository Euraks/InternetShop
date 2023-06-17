package ru.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restfulapi.model.Monitor;

public interface MonitorRepository extends JpaRepository<Monitor,Long> {
}
