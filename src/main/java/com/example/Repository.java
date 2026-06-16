package com.example;

import java.util.HashMap;
import java.util.Map;

public class Repository {

    private final Map<Long,Person> map;
    private Long id;

    public Repository(Map<Long,Person> map) {
        this.map = new HashMap<>(map);
        this.id = getCurrentId();
    }

    public Map<Long,Person> getMap() {
        return new HashMap<>(map);
    }

    public void create(Command command) {
        map.put(id, command.getValue());
        System.out.println("String saved with id = {" + id + "}");
        id++;
    }

    public void updateToId(Command command) {
        map.put(command.getId(), command.getValue());
        System.out.println("String  with id = {" + command.getId() + "} updated");
    }

    public void getAll() {
        for (Person person : map.values()) {
            System.out.println(person);
        }
    }

    public void getToId(Command command) {
        System.out.println(map.get(command.getId()));
    }

    public void deleteToId(Command command) {
        map.remove(command.getId());
        System.out.println("String with id = " + command.getId() + " deleted");
    }

    // Получить текущий счетчик
    private long getCurrentId() {
        return  map.keySet()
                .stream().max(Long::compareTo)
                .orElse(1L);
    }
}
