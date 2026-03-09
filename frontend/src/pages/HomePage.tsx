import MainLayout from "../layouts/MainLayout";

export default function HomePage() {
    return (
    <MainLayout>
      <h1 className="text-3xl font-bold mb-4">Bienvenido a LiterAlura</h1>
      <p className="text-gray-700">
        Esta es tu biblioteca personal de libros. Explorá, buscá y gestioná tu colección.
      </p>
    </MainLayout>
  );
}