package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Person jsonToPerson(String json) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(json, Person.class);
        } catch (JsonProcessingException e) {
            System.err.println("Ошибка парсинга JSON в Person: " + e.getMessage());
            return null;
        }
    }

    public String personToJson(Person person) {
        if (person == null) {
            return "{}";
        }
        try {
            return objectMapper.writeValueAsString(person);
        } catch (JsonProcessingException e) {
            System.err.println("Ошибка сериализации Person в JSON: " + e.getMessage());
            return "{}";
        }
    }

    public String mapToJson(Map<Long, Person> map) {
        if (map == null) {
            return "{}";
        }
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            System.err.println("Ошибка сериализации Map в JSON: " + e.getMessage());
            return "{}";
        }
    }

    public Map<Long, Person> jsonToMap(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new HashMap<>();
        }
        try {
            return objectMapper.readValue(json,
                    objectMapper.getTypeFactory().constructMapType(Map.class, Long.class, Person.class));
        } catch (JsonProcessingException e) {
            System.err.println("Ошибка парсинга JSON в Map: " + e.getMessage());
            return new HashMap<>();
        }
    }
}
