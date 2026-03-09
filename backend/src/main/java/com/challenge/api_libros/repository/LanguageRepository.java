package com.challenge.api_libros.repository;

import com.challenge.api_libros.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository  extends JpaRepository<Language, Long> {
    Optional<Language> findByCodigo(String codigo);
}
