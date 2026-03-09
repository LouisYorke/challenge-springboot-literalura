
import { Routes, Route } from "react-router-dom";
import HomePage from "../src/pages/HomePage";
import DashboardPage from "./pages/DashboardPage";
import SearchPage from "../src/pages/SearchPage";
import BookDetailPage from "./pages/BookDetailPage";
function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage/>} />
      <Route path="/dashboard" element={<DashboardPage />} />
      <Route path="/buscar" element={<SearchPage />} />
      <Route path="/libro/:id" element={<BookDetailPage />} />
    </Routes>
  );
}
export default App
