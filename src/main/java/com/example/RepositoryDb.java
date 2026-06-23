package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoryDb implements Repository {

    private final Map<Long,Person> map;
    private Long id;
    private LoudStorage storage;

    public RepositoryDb(LoudStorage loudStorage) {
        this.storage = loudStorage;
        this.map = storage.loadMapFromFile();
        this.id = getMAXCurrentId();
    }

    public Map<Long,Person> getMap() {
        return new HashMap<>(map);
    }

    @Override
    public void create(Command command) {
        id++;
        map.put(id, command.getValue());
        System.out.println("Person saved with id = "+id);
    }

    @Override
    public void updateById(Command command) {
        map.put(command.getId(), command.getValue());
        System.out.println("Person with id = "+command.getId()+"updated");
    }

    @Override
    public List<Person> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Person getById(Command command) {
        return map.get(command.getId());
    }

    @Override
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
