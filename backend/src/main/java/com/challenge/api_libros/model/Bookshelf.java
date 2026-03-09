package com.challenge.api_libros.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
//Representa una estantería virtual o categoría donde el usuario agrupa sus libros.
@Entity(name = "Bookshelf") @Table(name = "bookshelf")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Bookshelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @ManyToMany(mappedBy = "bookshelves",fetch = FetchType.LAZY)
    private List<Book> libros;

    public Bookshelf(String nombre) {
        this.nombre=nombre;
    }
}
