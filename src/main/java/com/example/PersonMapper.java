package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PersonMapper {
    private final ObjectMapper objectMapper ;

    public PersonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Person jsonToPerson(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Person.class);
    }

    public String personToJson(Person person) throws JsonProcessingException {
        Objects.requireNonNull(person, "Person не может быть null");
        return objectMapper.writeValueAsString(person);
    }

    public String listToJson(List<Person> persons) throws JsonProcessingException {
        Objects.requireNonNull(persons, "Список Persons не может быть null");
        return objectMapper.writeValueAsString(persons);
    }

    public List<Person> jsonToList(String json) throws JsonProcessingException {
        if (json == null || json.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return objectMapper.readValue(json,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class));

    }
}
