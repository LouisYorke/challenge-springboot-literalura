import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getBookById, getBookFeatures } from "../features/books/api";
import type { Book,BookFeatureResponse } from "../features/books/types";
import MainLayout from "../layouts/MainLayout";

const BookDetailPage = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [book, setBook] = useState<Book | null>(null);
  const [features, setFeatures] = useState<BookFeatureResponse | null>(null);
  
  useEffect(() => {
    if (!id) return;

    getBookById(Number(id)).then((bookData) => {
      setBook(bookData);
        if (bookData.gutendexId !== undefined) {
            return getBookFeatures(bookData.gutendexId);
        }
    }).then((featureData) => {
      if (featureData) setFeatures(featureData);
    }).catch((err) => console.error("Error:", err));
  }, [id]);

  if (!book) return <div>Cargando libro...</div>;

  return (
    <MainLayout>
    <div className="p-6">
      <button
        onClick={() => navigate(-1)} // -1 te lleva a la página anterior
        className="mb-4 px-4 py-2 bg-gray-200 hover:bg-gray-300 text-sm rounded transition"
      >
        ← Volver
      </button>
      <h1 className="text-2xl font-bold">{book.titulo}</h1>
      <p>Autor/es: {book.autores.map((a) => a.nombre).join(", ")}</p>
      <p>Idioma: {book.idioma}</p>
      <p>Descargas: {book.descargas}</p>
      <p>Estado: {book.estadoLectura}</p>

      {features?.formats && Object.keys(features.formats).length > 0 &&  (
        <div className="mt-4">
          <h2 className="text-lg font-semibold">Formatos disponibles</h2>
          <ul className="list-disc ml-5">
            {Object.entries(features.formats).map(([format, url]) => (
              <li key={format} className="mb-2">
                <a href={url} className="text-blue-600 underline" target="_blank" rel="noreferrer">
                  {format}
                </a>
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
    </MainLayout>
   
  );
};

export default BookDetailPage;
