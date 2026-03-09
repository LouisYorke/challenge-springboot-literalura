package com.challenge.api_libros.service;

import com.challenge.api_libros.model.Bookshelf;
import com.challenge.api_libros.repository.BookshelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookshelfService {
    @Autowired
    private BookshelfRepository bookshelfRepository;

    public Bookshelf buscarOCrear(String nombre) {
        return bookshelfRepository.findByNombre(nombre)
                .orElseGet(() -> bookshelfRepository.save(new Bookshelf(nombre)));
    }
}
