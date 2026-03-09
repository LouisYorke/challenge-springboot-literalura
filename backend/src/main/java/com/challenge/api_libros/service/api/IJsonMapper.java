package com.challenge.api_libros.service.api;

public interface IJsonMapper {
    <T> T mapJson(String json, Class<T> clase);
}
