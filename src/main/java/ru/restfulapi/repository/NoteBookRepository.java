package ru.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restfulapi.model.NoteBook;

public interface NoteBookRepository extends JpaRepository<NoteBook,Long> {
}
