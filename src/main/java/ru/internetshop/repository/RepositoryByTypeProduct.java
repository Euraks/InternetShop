package ru.internetshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryByTypeProduct<T> extends JpaRepository<T,Long> {
}
