package ru.internetshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.internetshop.model.NoteBook;

public interface NoteBookRepository extends JpaRepository<NoteBook,Long> {
}
