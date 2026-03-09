package com.challenge.api_libros.mapper;

import com.challenge.api_libros.dto.*;
import com.challenge.api_libros.model.*;
import com.challenge.api_libros.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class BookMapper {
    @Autowired private PersonService personService;
    @Autowired private LanguageService languageService;
    @Autowired private SubjectService subjectService;
    @Autowired private BookshelfService bookshelfService;

    //Convierte un GutendexDTO externo en una entidad Book normalizada y lista para persistir.
    public Book fromGutendexDTO (GutendexDTO dto) {
        List<Person> autores = dto.authors().stream()
                .map(a -> personService.buscarOCrear(a.nombre(), a.nacimiento(), a.fallecimiento()))
                .toList();
        //Puede haber o no traductores
        List<Person> traductores = dto.translators() != null ? dto.translators().stream()
                .map(t -> personService.buscarOCrear(t.nombre(), t.nacimiento(), t.fallecimiento()))
                .toList() : List.of();
        //
        Language idioma = null;
        if (dto.languages() != null && !dto.languages().isEmpty()) {
            idioma = languageService.buscarOCrear(dto.languages().getFirst());
        }

        List<Subject> subjects = dto.subjects() != null
                ? dto.subjects().stream().map(subjectService::buscarOCrear).toList()
                : List.of();

        List<Bookshelf> bookshelves = dto.bookshelves() != null
                ? dto.bookshelves().stream().map(bookshelfService::buscarOCrear).toList()
                : List.of();

        String resumen = dto.summaries() != null ? String.join("\n", dto.summaries()) : "";

        return Book.builder()
                .gutendexId(dto.id().longValue())
                .titulo(dto.title())
                .descargas(dto.download_count())
                .resumen(resumen)
                .autores(autores)
                .traductores(traductores)
                .idioma(idioma)
                .subjects(subjects)
                .bookshelves(bookshelves)
                .favorito(false)
                .estadoLectura(StateLecture.NO_EMPEZADO)
                .calificacion(null)
                .comentario(null)
                .fechaAgregado(LocalDate.now())
                .urlEpub(null)
                .urlHtml(null)
                .urlTxt(null)
                .portada(null)
                .build();
    }
    //Mapeo de BookResponse A UN DTO
    public BookResponseDTO mapToDTO(Book book) {
        String autor = book.getAutores().isEmpty()
                ? "Desconocido"
                : book.getAutores().getFirst().getNombre();
        String idioma = book.getIdioma() != null
                ? book.getIdioma().getCodigo()
                : "??";

        return new BookResponseDTO(
                book.getTitulo(),
                autor,
                idioma,
                book.getDescargas()
        );
    }

    public BookDTO mapToBookDTO(Book book) {

        List<PersonDTO> autoresDTO = book.getAutores().stream()
                .map(p -> new PersonDTO(p.getNombre(), p.getNacimiento(), p.getFallecimiento()))
                .toList();

        List<PersonDTO> traductoresDTO = book.getTraductores() != null
                ? book.getTraductores().stream()
                .map(t -> new PersonDTO(t.getNombre(), t.getNacimiento(), t.getFallecimiento()))
                .toList()
                : List.of();
        String idiomaCodigo = book.getIdioma() != null
                ? book.getIdioma().getCodigo()
                : "??";
        List<String> subjects = book.getSubjects() != null
                ? book.getSubjects().stream().map(Subject::getNombre).toList()
                : List.of();
        List <String> bookshelves= book.getBookshelves() != null
                ? book.getBookshelves().stream().map(Bookshelf::getNombre).toList()
                : List.of();

        return new BookDTO(
                book.getId(),
                book.getGutendexId(),
                book.getTitulo(),
                autoresDTO,
                traductoresDTO,
                idiomaCodigo,
                subjects,
                bookshelves,
                book.getDescargas(),
                book.getEstadoLectura(),
                book.getFavorito(),
                book.getFechaAgregado(),
                book.getCalificacion(),
                book.getResumen(),
                book.getUrlEpub(),
                book.getUrlHtml(),
                book.getUrlTxt()
        );
    }

}
