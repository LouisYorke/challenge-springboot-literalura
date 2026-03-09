export interface Person {
    id: number;
    nombre: string;
    nacimiento?: number;
    fallecimiento?: number;
}

export type StateLecture = "NO_LEIDO" | "LEYENDO" | "LEIDO";

export interface Book {
    id: number;
    gutendexId?: number;
    titulo: string;
    autores: Person[];
    traductores: Person[];
    idioma: string;
    subjects: string[];
    bookshelves: string[];
    descargas: number;
    estadoLectura: StateLecture;
    favorito: boolean;
    fechaAgregado: string;
    calificacion?: number;
    resumen?: string;
    urlEpub?: string;
    urlHtml?: string;
    urlTxt?: string;
}

export interface BookUpdateDTO {
  comentario?: string;
  calificacion?: number;
  estadoLectura?: "NO_LEIDO" | "LEYENDO" | "LEIDO";
  favorito?: boolean;
}

export interface BookFeatureResponse {
  mediaType: string;
  formats: Record<string, string>;
  copyright: boolean;
}
