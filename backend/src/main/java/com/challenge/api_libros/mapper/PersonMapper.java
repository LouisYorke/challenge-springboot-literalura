package com.challenge.api_libros.mapper;

import com.challenge.api_libros.dto.AutorDTO;
import com.challenge.api_libros.model.Book;
import com.challenge.api_libros.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public AutorDTO mapToAutorDTO(Person person) {
        return new AutorDTO(
                person.getId(),
                person.getNombre(),
                person.getNacimiento(),
                person.getFallecimiento(),
                person.getLibrosComoAutor().stream()
                        .map(Book::getTitulo)
                        .toList()
        );
    }
}
