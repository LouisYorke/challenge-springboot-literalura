import type { Book } from "../types";
import { useNavigate } from "react-router-dom";

interface Props {
  book: Book;
}

export default function BookCard({ book }: Props) {
  const navigate = useNavigate();
  return (
    <div 
    onClick={() => navigate(`/libro/${book.id}`)}
    className="bg-white rounded-lg shadow hover:shadow-lg p-4 w-full transition transform hover:scale-105 max-w-xs relative cursor-pointer">
      {/* Favorito */}
      {book.favorito && (
        <span className="absolute top-2 right-2 text-yellow-500 text-xl">★</span>
      )}

      <h2 className="text-lg font-bold">{book.titulo}</h2>

      <p className="text-sm text-gray-600 mb-1">
        {book.autores.map((a) => a.nombre).join(", ") || "Autor desconocido"}
      </p>

      <div className="text-xs text-gray-500 space-y-1">
        <p>Idioma: {book.idioma}</p>
        <p>Descargas: {book.descargas}</p>
        <p>
          Estado:{" "}
          <span
            className={`inline-block px-2 py-0.5 rounded text-white text-xs ${
              book.estadoLectura === "LEYENDO"
                ? "bg-blue-500"
                : book.estadoLectura === "LEIDO"
                ? "bg-green-500"
                : "bg-gray-400"
            }`}
          >
            {book.estadoLectura.toLowerCase()}
          </span>
        </p>
        {book.calificacion !== null && (
          <p>Calificación: {"⭐".repeat(book.calificacion || 0)}</p>
        )}
      </div>
    </div>
  );
}
