package com.challenge.api_libros.service.api;

import com.challenge.api_libros.dto.BookFeatureDTO;
import com.challenge.api_libros.dto.GutendexDTO;
import com.challenge.api_libros.dto.GutendexResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class GutendexService {
    private final GutendexApiClient apiClient;
    private final JsonMapper jsonMapper;
    private final String URL_BASE="https://gutendex.com/books";

    @Autowired
    public GutendexService(GutendexApiClient apiClient, JsonMapper jsonMapper) {
        this.apiClient = apiClient;
        this.jsonMapper = jsonMapper;
    }
    //Busqueda general
    public GutendexResponseDTO buscarLibrosPorNombre(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        //Obtengo el JSON de la API Gutendex
        String json = apiClient.obtenerJson(URL_BASE +"/?search="+encodedQuery);
        //Luego me los convierte de JSON a case java
        return jsonMapper.mapJson(json, GutendexResponseDTO.class);
    }

    public Optional<GutendexDTO> buscarLibroPorId(Long gutendexId) {
        String json = apiClient.obtenerJson(URL_BASE + "/" + gutendexId);
        if (json == null || json.isBlank()) {
            return Optional.empty();
        }
        try {
            GutendexDTO dto = jsonMapper.mapJson(json, GutendexDTO.class);
            return Optional.ofNullable(dto);
        } catch (RuntimeException e){
            // Si hay un error al mapear, por ejemplo, si vino HTML o un JSON inesperado
            System.err.println("Error al mapear el libro con ID " + gutendexId + ": " + e.getMessage());
            return Optional.empty();
        }
    }
    //Obtener los features
    public BookFeatureDTO obtenerFeatureDeLibro(Long gutendexId) {
        return buscarLibroPorId(gutendexId)
                .map(dto -> new BookFeatureDTO(
                        dto.media_type(),
                        dto.formats(),
                        dto.copyright()
                ))
                .orElseThrow(() -> new RuntimeException("Libro con ID " + gutendexId + " no encontrado en Gutendex."));
    }



}
