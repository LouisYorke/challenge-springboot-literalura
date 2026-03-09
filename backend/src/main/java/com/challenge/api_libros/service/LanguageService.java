package com.challenge.api_libros.service;

import com.challenge.api_libros.model.Language;
import com.challenge.api_libros.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    public Language buscarOCrear(String codigo) {
        return languageRepository.findByCodigo(codigo)
                .orElseGet(() -> languageRepository.save(new Language(null, codigo)));
    }
}
