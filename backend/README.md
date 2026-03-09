# LiterAlura - Catálogo Bibliográfico Dinámico

**LiterAlura** es una aplicación de gestión bibliográfica desarrollada en **Java** con el framework **Spring Boot**. El sistema permite la interacción con la API externa de [Gutendex](https://gutendex.com/) para la búsqueda, filtrado y persistencia de libros en una base de datos local.

Este proyecto fue desarrollado como parte del desafío técnico del programa **Oracle Next Education (ONE) - Alura LATAM**, enfocándose en la integración de servicios externos, persistencia relacional y procesamiento de datos JSON.

---

## Funcionalidades Principales

El sistema opera actualmente mediante una interfaz de consola interactiva que permite:

* **Búsqueda Inteligente:** Localización de libros por título consumiendo el catálogo de Gutendex.
* **Persistencia Automatizada:** Importación y registro de libros y autores seleccionados en base de datos PostgreSQL.
* **Gestión de Autores:** Consultas especializadas para listar autores registrados y filtrado histórico (autores activos en un año específico).
* **Filtros por Idioma:** Clasificación de la biblioteca local según el idioma de origen.
* **Análisis de Datos:** Manejo de estadísticas y normalización de la información obtenida vía JSON.

---

## Stack Tecnológico

* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3.x
* **Acceso a Datos:** Spring Data JPA / Hibernate
* **Base de Datos:** PostgreSQL
* **Procesamiento de Datos:** Jackson (Manejo de JSON)
* **Gestión de Dependencias:** Maven
* **Contenedores:** Docker (Opcional para servicios de BD)
* **Utilidades:** Lombok

---

## Arquitectura y Diseño

El proyecto sigue una arquitectura limpia orientada a la separación de responsabilidades:
* **Entidades:** Modelado relacional para libros y autores con integridad referencial.
* **Servicios:** Lógica de consumo de API mediante `RestTemplate` / `HttpClient` y procesamiento de reglas de negocio.
* **DTOs:** Objetos de transferencia de datos para desacoplar la respuesta de la API del modelo interno.



---

## Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone [https://github.com/tu-usuario/api-literalura.git](https://github.com/tu-usuario/api-literalura.git)
cd api-literalura

```

### 2. Configurar Propiedades de Entorno
Crea o edita el archivo src/main/resources/application.properties para configurar la conexión a tu base de datos:

Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/api_book
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
3. Infraestructura de Base de Datos (Docker)
Si prefieres utilizar Docker para la base de datos, ejecuta el siguiente comando:

```
Bash
docker run --name pg-literalura \
  -e POSTGRES_DB=api_book \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=tu_contraseña \
  -p 5432:5432 -d postgres
```

## Ejecución del Proyecto
Puedes iniciar la aplicación directamente desde tu IDE ejecutando la clase ApiLibrosApplication.java, o utilizar la terminal con el comando de Maven:

Bash
./mvnw spring-boot:run