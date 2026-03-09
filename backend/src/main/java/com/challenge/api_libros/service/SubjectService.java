package com.challenge.api_libros.service;

import com.challenge.api_libros.model.Subject;
import com.challenge.api_libros.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject buscarOCrear(String nombre) {
        return subjectRepository.findByNombre(nombre)
                .orElseGet(() -> subjectRepository.save(new Subject(nombre)));
    }
}

