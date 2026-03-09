package com.challenge.api_libros.model;

import jakarta.persistence.*;
import lombok.*;
@Entity(name= "Language")
@Table(name = "language")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Código ISO del idioma, por ejemplo "en", "fr", "es
    @Column(unique = true, length = 10)
    private String codigo; // Ej: "en", "es"

    public Language(String codigo) {
        this.codigo = codigo;
    }
}
