import { Link, useLocation } from "react-router-dom";

export default function Header() {
  const location = useLocation();

  const isActive = (path: string) =>
    location.pathname === path
      ? "text-yellow-600 font-semibold"
      : "text-gray-600";

return (
    <header className="bg-white shadow-md px-6 py-4 flex justify-between items-center">
      <h1 className="text-xl font-bold text-gray-800">📚 LiterAlura</h1>
      <nav className="space-x-4">
        <Link to="/dashboard" className={isActive("/dashboard")}>
          Mi Biblioteca
        </Link>
        <Link to="/buscar" className={isActive("/buscar")}>
          Buscar Libros
        </Link>
      </nav>
    </header>
  );
}
