package com.challenge.api_libros.service.api;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Component
public class GutendexApiClient {

    private final HttpClient client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS) // 👈 Esto es obligatorio
            .build();

    public String obtenerJson(String url){

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Respuesta inesperada de Gutendex: HTTP " + response.statusCode());
            }

            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al consumir la API de Gutendex",e);
        }
    }
}
