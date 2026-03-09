package com.challenge.api_libros.dto;

import com.challenge.api_libros.model.StateLecture;

public record BookUpdateDTO(
        Boolean favorito,
        String comentario,
        Integer calificacion,
        StateLecture estado
) {
}
