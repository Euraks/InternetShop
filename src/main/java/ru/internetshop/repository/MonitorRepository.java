package ru.internetshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.internetshop.model.Monitor;

public interface MonitorRepository extends JpaRepository<Monitor,Long> {
}
