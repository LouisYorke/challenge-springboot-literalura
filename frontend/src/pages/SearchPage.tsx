import MainLayout from "../layouts/MainLayout";
import { useGutendexSearch } from "../hooks/useGuntendexSearch";
import GutendexBookCard from "../features/gutendex/components/GutendexBookCard";
export default function SearchPage() {
    const { query, setQuery, buscar, results, loading, error, importar } = useGutendexSearch();
    return (
      <MainLayout>
          <h1 className="text-2xl font-semibold mb-4">Buscar libros en Gutendex</h1>

          <div className="flex gap-2 mb-6">
            <input
              type="text"
              placeholder="Ej: Sherlock Holmes"
              value={query}
              onChange={(e) => setQuery(e.target.value)}
              className="border border-gray-300 px-4 py-2 rounded w-full"
            />
            <button
              onClick={buscar}
              className="bg-yellow-600 text-white px-4 py-2 rounded hover:bg-yellow-700"
            >
              Buscar
            </button>
          </div>

          {loading && <p>Buscando libros...</p>}
          {error && <p className="text-red-600">{error}</p>}

          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
            {results.map((book) => (
              <GutendexBookCard key={book.id} book={book} onImport={importar} />
            ))}
          </div>
        </MainLayout>
      );
}