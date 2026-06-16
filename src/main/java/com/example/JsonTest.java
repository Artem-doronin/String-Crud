package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Person jsonToPerson(String json) throws JsonProcessingException {
        return objectMapper.readValue(json,Person.class);
    }

    public String personToJson(Person person) throws JsonProcessingException {
        return objectMapper.writeValueAsString(person);
    }
}
