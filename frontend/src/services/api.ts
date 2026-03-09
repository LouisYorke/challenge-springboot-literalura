import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080", // Cambiá si tu backend corre en otro puerto
});

export default api;