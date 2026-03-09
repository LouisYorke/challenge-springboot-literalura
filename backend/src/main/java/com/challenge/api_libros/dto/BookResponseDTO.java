package com.challenge.api_libros.dto;

public record BookResponseDTO(
      String titulo,
      String autor,
      String idioma,
      Integer descargas
) {
}
