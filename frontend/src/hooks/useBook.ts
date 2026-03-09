import { useEffect, useState } from "react";
import { getAllBooks } from "../features/books/api";
import type { Book } from "../features/books/types";

export function useBooks() {
  const [books, setBooks] = useState<Book[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function loadBooks() {
      try {
        const data = await getAllBooks();
        setBooks(data);
        console.log("📚 Libros recibidos:", data);
      } catch (error) {
        console.error("Error al cargar los libros:", error);
      } finally {
        setLoading(false);
      }
    }
    loadBooks();
  }, []);

  return { books, loading };
}
