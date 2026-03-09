import { useState } from "react";
import { buscarEnGutendex, importarLibroPorId } from "../features/gutendex/api";
import type { GutendexResult } from "../features/gutendex/types";
import { AxiosError } from "axios";

export function useGutendexSearch() {
  const [query, setQuery] = useState("");
  const [results, setResults] = useState<GutendexResult[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const buscar = async () => {
    setLoading(true);
    setError("");
    try {
      const data = await buscarEnGutendex(query);
      setResults(data.results);
    } catch {
      setError("Error al buscar en Gutendex");
    } finally {
      setLoading(false);
    }
  };

  const importar = async (id: number) => {
    try {
      await importarLibroPorId(id);
      alert("📚 Libro importado con éxito");
    } catch (err) {
      const error = err as AxiosError<{ error: string }>;
      const mensaje = error.response?.data?.error || "Error al importar libro";
      alert(mensaje);
    }
  };

  return { query, setQuery, buscar, results, loading, error, importar };
}

