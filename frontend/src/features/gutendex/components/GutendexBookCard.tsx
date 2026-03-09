import type { GutendexResult } from "../types";

interface Props {
  book: GutendexResult;
  onImport: (id: number) => void;
}

export default function GutendexBookCard({ book, onImport }: Props) {
  return (
    <div className="bg-white shadow-md rounded-lg p-4">
      <h2 className="text-lg font-semibold">{book.title}</h2>
      <p className="text-sm text-gray-600">
        {book.authors.map((a) => a.name).join(", ") || "Autor desconocido"}
      </p>
      <p className="text-xs text-gray-500">Idioma: {book.languages.join(", ")}</p>
      <p className="text-xs text-gray-500 mb-2">Descargas: {book.download_count}</p>
      <button
        className="bg-blue-600 text-white px-4 py-1 rounded hover:bg-blue-700 text-sm"
        onClick={() => onImport(book.id)}
      >
        Importar
      </button>
    </div>
  );
}
