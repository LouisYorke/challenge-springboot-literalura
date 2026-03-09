package com.challenge.api_libros.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

//Representa a una persona asociada a un libro, ya sea como autor o traductor.
//Puede tener múltiples libros asociados en cada rol.
@Entity(name = "Person")
@Table(name = "person")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private Integer nacimiento;

    private Integer fallecimiento;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "autores")
    private List<Book> librosComoAutor;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "traductores")
    private List<Book> librosComoTraducidos;
}
