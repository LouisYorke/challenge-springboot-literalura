package com.challenge.api_libros.repository;

import com.challenge.api_libros.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByGutendexId(Long gutendexId);
    int countByIdiomaCodigo(String codigo);
    List<Book> findByIdioma_Codigo(String codigo);

}
