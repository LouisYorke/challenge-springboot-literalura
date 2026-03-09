# Challenge Alura LiterAlura

**BookAPI** es una API REST robusta desarrollada con **Spring Boot**, **JPA** y **PostgreSQL**. El sistema permite la integración con la [API pública de Gutendex](https://gutendex.com/books/) para la búsqueda, importación y administración local de un catálogo bibliográfico normalizado.

---

## Objetivo del Proyecto

El sistema actúa como un puente entre fuentes de datos externas y una persistencia local optimizada, permitiendo:
* **Integración:** Consumo dinámico de datos desde Gutendex.
* **Persistencia:** Almacenamiento local mediante un proceso de importación por ID (`gutendexId`).
* **Normalización:** Estructuración de datos complejos (autores, categorías, estanterías e idiomas).
* **Administración:** Gestión completa del catálogo mediante una interfaz RESTful.

---

## Stack Tecnológico

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3.x (Spring Data JPA)
* **Base de Datos:** PostgreSQL 15+
* **Serialización:** Jackson (Manejo de JSON)
* **Gestión de Dependencias:** Maven
* **Contenedores:** Docker (Opcional para base de datos)

---

## Arquitectura de Datos (Entidades)

La base de datos está diseñada bajo un modelo relacional que evita la redundancia:

* **Book:** Entidad principal. Gestiona relaciones con idiomas, autores y categorías.
* **Person:** Modela autores y colaboradores (traductores), gestionando fechas de nacimiento y fallecimiento.
* **Language:** Catálogo de idiomas normalizado por códigos internacionales (ISO).
* **Subject / Bookshelf:** Clasificaciones temáticas y colecciones virtuales asociadas a los títulos.

---

## Data Transfer Objects (DTOs)

Se implementaron DTOs para desacoplar el modelo interno de la capa de presentación, optimizando el rendimiento de las consultas:

* `BookResponseDTO`: Vista simplificada para listados rápidos.
* `BookDTO`: Objeto detallado con toda la metadata del libro.
* `AutorDTO`: Información del autor vinculada a su bibliografía registrada.

---

## Lógica de Mapeo y Transformación

El componente `BookMapper` gestiona la conversión entre entidades y DTOs, aplicando reglas de negocio específicas:
* **Normalización de Relaciones:** En casos de multiplicidad de autores o idiomas, el sistema selecciona el registro principal para mantener la consistencia en vistas simplificadas.
* **Prevención de Ciclos:** Mapeo plano para evitar referencias circulares en las respuestas JSON.

---

## Documentación de Endpoints

### Catálogo Local

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| **GET** | `/libros` | Listar todos los libros registrados. |
| **GET** | `/libros/{id}` | Obtener detalle de un libro específico. |
| **PUT** | `/libros/{id}` | Actualizar información del registro. |
| **DELETE** | `/libros/{id}` | Eliminar un libro del catálogo local. |
| **GET** | `/libros/idioma/{codigo}` | Filtrar títulos por idioma. |

### Integración Gutendex

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| **GET** | `/importar/buscar?query=...` | Buscar títulos en la API externa. |
| **POST** | `/importar/{gutendexId}` | Importar y persistir un libro por su ID. |

### Gestión de Autores

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| **GET** | `/autores` | Listar autores registrados. |
| **GET** | `/autores/vivos/{anio}` | Consultar autores activos en un año específico. |

---

## Configuración y Despliegue Local

### 1. Clonar el repositorio
```bash
git clone [https://github.com/tuusuario/api_libros.git](https://github.com/tuusuario/api_libros.git)
cd api_libros
2. Infraestructura (Docker)
Puedes iniciar la base de datos rápidamente utilizando el archivo docker-compose.yml incluido:

Bash
docker-compose up -d
3. Configuración de Entorno
Asegúrate de configurar las credenciales en src/main/resources/application.properties:

Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/api_libros
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
4. Ejecución
Bash
./mvnw spring-boot:run