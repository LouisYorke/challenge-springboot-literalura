package com.challenge.api_libros.service;

import com.challenge.api_libros.dto.*;
import com.challenge.api_libros.mapper.BookMapper;
import com.challenge.api_libros.model.*;
import com.challenge.api_libros.repository.*;
import com.challenge.api_libros.service.api.GutendexService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GutendexService gutendexService;
    @Autowired
    private BookMapper bookMapper;

    //Guarda un nuevo libro a partir de un ID de Gutendex, si no fue registrado previamente.
    public BookDTO guardarLibroDesdeGutendex(Long gutendexId) {
        // Verificar si ya existe por gutendexId
        if (bookRepository.existsByGutendexId(gutendexId)) {
            throw new RuntimeException("El libro con Gutendex ID " + gutendexId + " ya existe.");
        }

        GutendexDTO dto = gutendexService.buscarLibroPorId(gutendexId)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado en Gutendex"));

        Book book = bookMapper.fromGutendexDTO(dto);
        try {
            Book guardado=bookRepository.save(book);
            return bookMapper.mapToBookDTO(guardado);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el libro: " + e.getMessage(), e);
        }
    }
    //Obtener del Mapeo
    public BookDTO mapearADTO(Long id) {
        return bookMapper.mapToBookDTO(buscarPorId(id));
    }
    public BookResponseDTO mapearADTOResumen(Book book) {
        return bookMapper.mapToDTO(book);
    }

    public Book buscarPorId(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Libro no encontrado"));
    }

    //Listar
    public List<BookDTO> listarTodosDTO() {
        return bookRepository.findAll().stream()
                .map(bookMapper::mapToBookDTO)
                .toList();
    }
    public List<BookResponseDTO> listarTodosResumen() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::mapToDTO)
                .toList();
    }
    public List<BookDTO> listarDTOsPorIdioma(String codigo) {
        return bookRepository.findByIdioma_Codigo(codigo).stream()
                .map(bookMapper::mapToBookDTO)
                .toList();
    }

    public BookResponseDTO buscarPorIdResumen(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        return bookMapper.mapToDTO(book);
    }

    //Contar
    public int contarPorIdioma(String codigoIdioma) {
        return bookRepository.countByIdiomaCodigo(codigoIdioma);
    }
    //Actualizar Libro

    public Book actualizar(Long id, BookUpdateDTO update) {
        Book libro = buscarPorId(id);

        libro.setFavorito(update.favorito());
        libro.setComentario(update.comentario());
        libro.setCalificacion(update.calificacion());
        libro.setEstadoLectura(update.estado());
        return bookRepository.save(libro);
    }

    //Eliminar libro
    public void eliminar(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Libro no encontrado");
        }
        bookRepository.deleteById(id);
    }

    public GutendexResponseDTO buscarLibrosPorNombre(String query) {
        return gutendexService.buscarLibrosPorNombre(query);
    }


}
