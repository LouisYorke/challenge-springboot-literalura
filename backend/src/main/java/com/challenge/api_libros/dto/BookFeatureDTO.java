package com.challenge.api_libros.dto;

import java.util.Map;

public record BookFeatureDTO(
        String mediaType,
        Map<String, String> formats,
        Boolean copyright
) {
}
