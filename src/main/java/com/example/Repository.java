package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {

    private final Map<Long,Person> map;
    private Long id;
    private LoudStorage storage;

    public Repository(LoudStorage loudStorage) {
        this.storage = loudStorage;
        try {
            this.map = storage.loadMapFromFile();
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Критическая ошибка при инициализации репозитория из файла", e);
        }
        this.id = getMAXCurrentId();
    }

    public Map<Long,Person> getMap() {
        return new HashMap<>(map);
    }

    public void create(Command command) {
        id++;
        map.put(id, command.getValue());
        System.out.println("Person saved with id = "+id);
    }

    public void updateById(Command command) {
        map.put(command.getId(), command.getValue());
        System.out.println("Person with id = "+command.getId()+"updated");
    }

    public List<Person> getAll() {
        return new ArrayList<>(map.values());
    }

    public Person getById(Command command) {
        return map.get(command.getId());
    }

    public void deleteById(Command command) {
        map.remove(command.getId());
        System.out.println("Person with id = " + command.getId() + " deleted");
    }

    private long getMAXCurrentId() {
        return  map.keySet()
                .stream().max(Long::compareTo)
                .orElse(1L);
    }
}
