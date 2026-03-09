package com.challenge.api_libros.controller;

import com.challenge.api_libros.dto.BookFeatureDTO;
import com.challenge.api_libros.service.BookService;
import com.challenge.api_libros.service.api.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/importar")
public class ImportController {
    @Autowired
    private GutendexService gutendexService;
    @Autowired
    private BookService bookService;

    // Buscar libros en Gutendex (GET /importar/buscar?query=...)
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarLibrosPorNombre(@RequestParam String query) {
        if (query == null || query.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "El parámetro 'query' no puede estar vacío."));
        }

        var resultados = gutendexService.buscarLibrosPorNombre(query);
        return ResponseEntity.ok(resultados);
    }

    // Importar libro por ID de Gutendex (POST /importar/{id})
    @PostMapping("/{gutendexId}")
    public ResponseEntity<?> importarLibro(@PathVariable Long gutendexId) {
        try {
            bookService.guardarLibroDesdeGutendex(gutendexId);
            return ResponseEntity.ok(Map.of("mensaje", "Libro importado con éxito"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/por-titulo")
    public ResponseEntity<?> importarPorTitulo(@RequestParam String titulo) {
        var resultado = gutendexService.buscarLibrosPorNombre(titulo);

        if (resultado.results().isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("error", "No se encontró ningún libro con ese título"));
        }
        var primerLibro = resultado.results().get(0);
        try {
            bookService.guardarLibroDesdeGutendex(primerLibro.id().longValue());
            return ResponseEntity.ok(Map.of("mensaje", "Libro importado con éxito"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/{gutendexId}/feature")
    public ResponseEntity<BookFeatureDTO> obtenerFeature(@PathVariable Long gutendexId) {
        BookFeatureDTO feature = gutendexService.obtenerFeatureDeLibro(gutendexId);
        return ResponseEntity.ok(feature);
    }

}
