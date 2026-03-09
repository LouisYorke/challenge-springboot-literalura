export interface GutendexResult {
  id: number;
  title: string;
  authors: { name: string }[];
  languages: string[];
  download_count: number;
}

export interface GutendexResponse {
  count: number;
  results: GutendexResult[];
}
