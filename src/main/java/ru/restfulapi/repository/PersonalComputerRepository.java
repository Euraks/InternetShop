package ru.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restfulapi.model.PersonalComputer;

public interface PersonalComputerRepository extends JpaRepository<PersonalComputer,Long> {
}
