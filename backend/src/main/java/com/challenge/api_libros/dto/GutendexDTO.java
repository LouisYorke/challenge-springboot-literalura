package com.challenge.api_libros.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexDTO(
        Integer id,
        String title,
        List<String> subjects,
        List<PersonDTO> authors,
        List<String> summaries,
        List<PersonDTO> translators,
        List<String> bookshelves,
        List<String> languages,
        Boolean copyright,
        String media_type,
        Map<String, String> formats,
        Integer download_count
) {
}
