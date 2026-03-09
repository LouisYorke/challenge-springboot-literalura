package com.challenge.api_libros.controller;

import com.challenge.api_libros.dto.BookDTO;
import com.challenge.api_libros.dto.BookResponseDTO;
import com.challenge.api_libros.dto.BookUpdateDTO;
import com.challenge.api_libros.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/libros")
public class BookController {
    @Autowired
    private BookService bookService;

    //Listar todos libros
    @GetMapping
    public ResponseEntity<List<BookDTO>> listarLibrosCompletos() {
        return ResponseEntity.ok(bookService.listarTodosDTO());
    }
    @GetMapping("/resumen")
    public ResponseEntity<List<BookResponseDTO>> listarLibrosResumen() {
        return ResponseEntity.ok(bookService.listarTodosResumen());
    }

    // Obtener un libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> obtenerLibro(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.mapearADTO(id));
    }
    @GetMapping("/{id}/resumen")
    public ResponseEntity<BookResponseDTO> obtenerLibroResumen(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.buscarPorIdResumen(id));
    }

    //Obtener por idioma
    @GetMapping("/idioma/{codigo}")
    public ResponseEntity<?> librosPorIdioma(@PathVariable String codigo) {
        return ResponseEntity.ok(bookService.listarDTOsPorIdioma(codigo));
    }
    //Cuenta los idiomas
    @GetMapping("/idioma/{codigo}/conteo")
    public ResponseEntity<Map<String, Object>> contarLibrosPorIdioma(@PathVariable String codigo) {
        int cantidad = bookService.contarPorIdioma(codigo);
        return ResponseEntity.ok(Map.of("idioma", codigo,"cantidad", cantidad));
    }

    // Actualizar datos personales como comentario, rating, estado, etc.
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarLibro(@PathVariable Long id, @RequestBody BookUpdateDTO update) {
        bookService.actualizar(id, update);
        return ResponseEntity.ok("Libro actualizado con exito");
    }

    // Eliminar un libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        bookService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
