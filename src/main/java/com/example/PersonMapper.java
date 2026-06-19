package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class PersonMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Person jsonToPerson(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Person.class);
    }

    public String personToJson(Person person) throws JsonProcessingException {
        if (person == null) {
            return "{}";
        }
            return objectMapper.writeValueAsString(person);
    }

    public String mapToJson(Map<Long, Person> map) throws JsonProcessingException {
        if (map == null) {
            return "{}";
        }
        return objectMapper.writeValueAsString(map);
    }

    public Map<Long, Person> jsonToMap(String json) throws JsonProcessingException {
        if (json == null || json.trim().isEmpty()) {
            return new HashMap<>();
        }
        return objectMapper.readValue(json,
                objectMapper.getTypeFactory().constructMapType(Map.class, Long.class, Person.class));

    }
}
