package com.challenge.api_libros.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonMapper implements IJsonMapper {
    //Transformar JSON a objetos Java
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public <T> T mapJson(String json, Class<T> clase) {
        //Verifica que no este vacio
        if (json == null || json.isBlank()) {
            throw new RuntimeException("Respuesta vacía al mapear JSON a clase " + clase.getSimpleName());
        }
        // Nos devuelve un objetcoMapper, va leer ese Json y lo transforma en la clase
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al mapear JSON a clase "+ clase.getSimpleName(), e);
        }
    }
}
