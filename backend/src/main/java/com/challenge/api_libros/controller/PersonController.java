package com.challenge.api_libros.controller;

import com.challenge.api_libros.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping
    public ResponseEntity<?> listarSoloAutores() {
        return ResponseEntity.ok(personService.listarAutoresConLibros());
    }
    @GetMapping("/vivos/{anio}")
    public ResponseEntity<?> listarAutoresVivos(@PathVariable Integer anio) {
        return ResponseEntity.ok(personService.buscarAutoresVivosEnAnio(anio));
    }
}
