package com.challenge.api_libros.principal;

import com.challenge.api_libros.dto.AutorDTO;
import com.challenge.api_libros.dto.BookDTO;
import com.challenge.api_libros.dto.PersonDTO;

public class ImpresorConsola {
    public static void imprimirLibro(BookDTO libro) {
        System.out.println("---------------");
        System.out.println("📖 Título: " + libro.titulo());
        System.out.println("👤 Autor(es): " + libro.autores().stream()
                .map(PersonDTO::nombre)
                .reduce((a, b) -> a + ", " + b).orElse("Desconocido"));
        System.out.println("🌐 Idioma: " + libro.idioma());
        System.out.println("⬇️ Descargas: " + libro.descargas());
        if (libro.fechaAgregado() != null) System.out.println("🗓️ Fecha agregado: " + libro.fechaAgregado());;
        if (libro.subjects() != null && !libro.subjects().isEmpty())
            System.out.println("📚 Subjects: " + String.join(", ", libro.subjects()));
        if (libro.bookshelves() != null && !libro.bookshelves().isEmpty())
            System.out.println("📦 Estanterías: " + String.join(", ", libro.bookshelves()));
    }

    public static void imprimirAutor(AutorDTO autor) {
        System.out.println("---------------");
        System.out.println("🧑 Nombre: " + autor.nombre());
        System.out.println("📅 Nacimiento: " + (autor.nacimiento() != null ? autor.nacimiento() : "Desconocido"));
        System.out.println("⚰️ Fallecimiento: " + (autor.fallecimiento() != null ? autor.fallecimiento() : "Aún con vida"));
    }
}
