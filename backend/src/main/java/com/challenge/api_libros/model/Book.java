package com.challenge.api_libros.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
//Entidad que representa un libro importado desde Gutendex y almacenado en la base de datos local.
//Incluye metadatos del libro, relaciones con autores, idioma, categorías, estanterías, etc.
@Entity(name= "Book") @Table(name = "book")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long gutendexId;
    private String titulo;
    private Integer descargas;
    // Campo largo (texto extenso) para guardar resúmenes o descripciones del libro
    @Column(length = 10000)
    private String resumen;
    // La relación es muchos a muchos: un autor puede tener varios libros, y un libro puede tener varios autores.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Person> autores;
    // La relación es muchos a muchos: un traductor puede tener varios libros, y un libro puede tener varios autores.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_translator",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "translator_id")
    )
    private List<Person> traductores;
    //Relación muchos a uno: varios libros pueden compartir el mismo idioma.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idioma_codigo")
    private Language idioma;

    //Lista de temas asociados al libro
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_subject",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;
    ///Lista de estanterías virtuales (bookshelves) asociadas al libro
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_bookshelf",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "bookshelf_id")
    )
    private List<Bookshelf> bookshelves;

    // Campos personales
    private Boolean favorito;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_lectura")
    private StateLecture estadoLectura;

    private Integer calificacion;
    private String comentario;
    private LocalDate fechaAgregado;

    private String urlEpub;
    private String urlHtml;
    private String urlTxt;
    @Column(name = "portada_png")
    private byte[] portada;

}
