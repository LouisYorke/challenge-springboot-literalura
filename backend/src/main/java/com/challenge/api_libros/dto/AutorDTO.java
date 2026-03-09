package com.challenge.api_libros.dto;

import java.util.List;

public record AutorDTO(
        Long id,
        String nombre,
        Integer nacimiento,
        Integer fallecimiento,
        List<String> libros
) {
}
