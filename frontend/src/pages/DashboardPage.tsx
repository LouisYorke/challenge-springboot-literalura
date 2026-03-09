import MainLayout from "../layouts/MainLayout";
import { useBooks } from "../hooks/useBook";
import BookCard from "../features/books/components/BookCard";

export default function DashboardPage() {
  const { books, loading } = useBooks();

  return (
    <MainLayout>
      <h1 className="text-2xl font-semibold mb-4">📚 Mi Biblioteca</h1>

      {loading ? (
        <p>Cargando libros...</p>
      ) : books.length === 0 ? (
        <p>No hay libros guardados. ¡Buscá en Gutendex para comenzar!</p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {books.map((book) => (
            <BookCard key={book.id} book={book} />
          ))}
        </div>
      )}
    </MainLayout>
  );
}