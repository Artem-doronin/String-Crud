package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository implements Repository {

    private final Map<Long,Person> map;
    private Long id;
    private LoudStorage storage;

    public InMemoryRepository(LoudStorage loudStorage) {
        this.storage = loudStorage;
        this.map = storage.loadMapFromFile();
        this.id = getMAXCurrentId();
    }

    public Map<Long,Person> getMap() {
        return new HashMap<>(map);
    }

    @Override
    public void create(Person person) {
        id++;
        map.put(id, person);
        System.out.println("Person saved with id = "+id);
    }

    @Override
    public void updateById(Person person) {
        map.put(person.getId(), person);
        System.out.println("Person with id = "+person.getId()+"updated");
    }

    @Override
    public List<Person> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Person getById(Long id) {
        return map.get(id);
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
        System.out.println("Person with id = " + id + " deleted");
    }

    private long getMAXCurrentId() {
        return  map.keySet()
                .stream().max(Long::compareTo)
                .orElse(1L);
    }

    public void saveToStorage() throws JsonProcessingException {
        storage.saveMapToFile(map);
    }
}
