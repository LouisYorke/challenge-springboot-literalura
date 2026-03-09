package com.challenge.api_libros.dto;

import com.challenge.api_libros.model.StateLecture;

import java.time.LocalDate;
import java.util.List;

public record BookDTO(
        Long id,
        Long gutendexId,
        String titulo,
        List<PersonDTO> autores,
        List<PersonDTO> traductores,
        String idioma,
        List<String> subjects,
        List<String> bookshelves,
        Integer descargas,
        StateLecture estadoLectura,
        Boolean favorito,
        LocalDate fechaAgregado,
        Integer calificacion,
        String resumen,
        String urlEpub,
        String urlHtml,
        String urlTxt
) {
}
