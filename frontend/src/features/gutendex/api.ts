import api from "../../services/api";
import type { GutendexResponse } from "../gutendex/types";

export async function buscarEnGutendex(query: string): Promise<GutendexResponse> {
  const response = await api.get("/importar/buscar", { params: { query } });
  return response.data;
}

export async function importarLibroPorId(gutendexId: number) {
  const response = await api.post(`/importar/${gutendexId}`);
  return response.data;
}
