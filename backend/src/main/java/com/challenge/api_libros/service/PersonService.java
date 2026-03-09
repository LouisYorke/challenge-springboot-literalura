package com.challenge.api_libros.service;

import com.challenge.api_libros.dto.AutorDTO;
import com.challenge.api_libros.mapper.PersonMapper;
import com.challenge.api_libros.model.Person;
import com.challenge.api_libros.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;

    public Person buscarOCrear(String nombre, Integer nacimiento, Integer fallecimiento) {
        return personRepository.findByNombreAndNacimientoAndFallecimiento(nombre, nacimiento, fallecimiento)
                .orElseGet(() -> {
                    Person nueva = new Person(null, nombre, nacimiento, fallecimiento, null, null);
                    return personRepository.save(nueva);
                });
    }

    public List<AutorDTO> listarAutoresConLibros() {
        return personRepository.findByLibrosComoAutorIsNotEmpty().stream()
                .map(personMapper::mapToAutorDTO)
                .toList();
    }
    public List<AutorDTO> buscarAutoresVivosEnAnio(Integer anio) {
        return personRepository.buscarAutoresVivosEnAnio(anio).stream()
                .map(personMapper::mapToAutorDTO)
                .toList();
    }

}
