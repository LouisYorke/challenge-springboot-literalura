package com.challenge.api_libros.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.Setter;
import java.util.List;
//Representa una categoría temática del libro. Ejemplos: "Philosophy", "Science Fiction".
@Entity(name = "Subject") @Table(name = "subject")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    private List<Book> libros;

    public Subject(String nombre) {
        this.nombre = nombre;
    }
}
