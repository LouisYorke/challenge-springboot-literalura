package com.challenge.api_libros.repository;

import com.challenge.api_libros.model.Bookshelf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {
    Optional<Bookshelf> findByNombre(String nombre);
}

