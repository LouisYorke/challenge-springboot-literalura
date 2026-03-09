package com.challenge.api_libros.principal;

import com.challenge.api_libros.dto.AutorDTO;
import com.challenge.api_libros.dto.BookDTO;
import com.challenge.api_libros.service.BookService;
import com.challenge.api_libros.service.PersonService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Scanner;

@Component
public class Consola {
    private Scanner teclado = new Scanner(System.in);
    private final RestTemplate restTemplate = new RestTemplate();

    private final BookService bookService;
    private final PersonService personService;

    public Consola(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            System.out.println("""
                    1- Buscar Libro por título
                    2- Listar Libros registrados
                    3- Listar Autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    0 - Salir
                    """);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1 -> buscarLibroPortitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosPorAnio();
                case 5 -> listarLibrosPorIdioma();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Selección inválida");
            }
        }
    }

    private void buscarLibroPortitulo() {
        System.out.print("Ingrese el título a buscar: ");
        var titulo = teclado.nextLine();

        var resultado = bookService.buscarLibrosPorNombre(titulo);
        if (resultado.results().isEmpty()) {
            System.out.println("No se encontró ningún libro con ese título.");
            return;
        }

        var libro = resultado.results().get(0);
        try {
            BookDTO importado = bookService.guardarLibroDesdeGutendex(libro.id().longValue());
            System.out.println("\n✅ Libro importado con éxito:");
            ImpresorConsola.imprimirLibro(importado);
        } catch (RuntimeException e) {
            System.out.println("Error al importar: " + e.getMessage());
        }
    }

    private void listarLibrosRegistrados() {
        try {
            ResponseEntity<List<BookDTO>> response = restTemplate.exchange(
                    "http://localhost:8080/libros",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
            List<BookDTO> libros = response.getBody();
            if (libros == null || libros.isEmpty()) {
                System.out.println("📭 No hay libros registrados aún.");
                return;
            }
            System.out.println("\n📚 Libros registrados:");
            libros.forEach(ImpresorConsola::imprimirLibro);
        } catch (Exception e) {
            System.out.println("❌ Error al obtener los libros: " + e.getMessage());
        }
    }

    private void listarAutoresRegistrados() {
        try {
            ResponseEntity<List<AutorDTO>> response = restTemplate.exchange(
                    "http://localhost:8080/autores",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
            List<AutorDTO> autores = response.getBody();
            if (autores == null || autores.isEmpty()) {
                System.out.println("📭 No hay autores registrados aún.");
                return;
            }
            System.out.println("\n👤 Autores registrados:");
            autores.forEach(ImpresorConsola::imprimirAutor);
        } catch (Exception e) {
            System.out.println("❌ Error al obtener los autores: " + e.getMessage());
        }
    }

    private void listarAutoresVivosPorAnio() {
        System.out.print("Ingrese un año para consultar autores vivos: ");
        int anio = teclado.nextInt();
        teclado.nextLine();
        try {
            ResponseEntity<List<AutorDTO>> response = restTemplate.exchange(
                    "http://localhost:8080/autores/vivos/" + anio,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
            List<AutorDTO> autores = response.getBody();
            if (autores == null || autores.isEmpty()) {
                System.out.println("📭 No se encontraron autores vivos en el año " + anio + ".");
                return;
            }
            System.out.println("\n👤 Autores vivos en el año " + anio + ":");
            autores.forEach(ImpresorConsola::imprimirAutor);
        } catch (Exception e) {
            System.out.println("❌ Error al obtener autores vivos: " + e.getMessage());
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.print("Ingrese el código del idioma (ej: en, es, fr): ");
        String codigo = teclado.nextLine();
        try {
            ResponseEntity<List<BookDTO>> response = restTemplate.exchange(
                    "http://localhost:8080/libros/idioma/" + codigo,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
            List<BookDTO> libros = response.getBody();
            if (libros == null || libros.isEmpty()) {
                System.out.println("📭 No hay libros registrados en el idioma '" + codigo + "'.");
                return;
            }
            System.out.println("\n📚 Libros en idioma '" + codigo + "':");
            libros.forEach(ImpresorConsola::imprimirLibro);
        } catch (Exception e) {
            System.out.println("❌ Error al consultar libros por idioma: " + e.getMessage());
        }
    }
}