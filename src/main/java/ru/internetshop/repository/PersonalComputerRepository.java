package ru.internetshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.internetshop.model.PersonalComputer;

public interface PersonalComputerRepository extends JpaRepository<PersonalComputer,Long> {
}
