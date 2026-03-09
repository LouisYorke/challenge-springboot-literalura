import api from "../../services/api";
import type { Book } from "./types";
import type { BookUpdateDTO, BookFeatureResponse } from "../books/types"; 

// Traer todos los libros en detalle (BookDTO)
export async function getAllBooks(): Promise<Book[]> {
  const response = await api.get("/libros");
  return response.data;
}

// Traer un libro por ID
export async function getBookById(id: number): Promise<Book> {
  const response = await api.get(`/libros/${id}`);
  return response.data;
}

// Eliminar un libro
export async function deleteBook(id: number): Promise<void> {
  await api.delete(`/libros/${id}`);
}

// Actualizar comentario, rating, estado, etc.
export async function updateBook(id: number, data: BookUpdateDTO): Promise<void> {
  await api.put(`/libros/${id}`, data);
}

// Filtrar libros por idioma
export async function getBooksByLanguage(code: string): Promise<Book[]> {
  const response = await api.get(`/libros/idioma/${code}`);
  return response.data;
}

// Obtener formatos del libro desde Gutendex por gutendexId
export async function getBookFeatures(gutendexId: number): Promise<BookFeatureResponse> {
  const response = await api.get(`/importar/${gutendexId}/feature`);
  return response.data;
}
