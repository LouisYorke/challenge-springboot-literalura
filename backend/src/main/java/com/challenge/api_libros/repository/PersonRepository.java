package com.challenge.api_libros.repository;

import com.challenge.api_libros.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByLibrosComoAutorIsNotEmpty();

    Optional<Person> findByNombreAndNacimientoAndFallecimiento(String nombre, Integer nacimiento, Integer fallecimiento);

    @Query("SELECT p FROM Person p WHERE p.nacimiento <= :anio AND (p.fallecimiento IS NULL OR p.fallecimiento > :anio)")
    List<Person> buscarAutoresVivosEnAnio(@Param("anio") Integer anio);

}
