package com.example;

import java.util.HashMap;
import java.util.Map;

public class Repository {

    private static final Long COUNTER_KEY = -1L;
    private final Map<Long, String> map;

    public Repository(Map<Long, String> map) {
        this.map = new HashMap<>(map);
    }

    public Map<Long, String> getMap() {
        return new HashMap<>(map);
    }

    public void create(Command command) {
        Long id = generateId();
        map.put(id, command.getValue());
        System.out.println("String saved with id = {" + id + "}");
    }

    public void updateToId(Command command) {
        map.put(command.getId(), command.getValue());
        System.out.println("String  with id = {" + command.getId() + "} updated");
    }

    public void getAll() {
        for (String s : map.values()) {
            System.out.println(s);
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
        String counterValue = map.get(COUNTER_KEY);
        if (counterValue == null) {
            return 1;  // если счетчика нет, начинаем с 1
        }
        return Long.parseLong(counterValue);
    }

    private void setCurrentId(long id) {
        map.put(COUNTER_KEY, String.valueOf(id));
    }

    // Генерация нового ID
    private Long generateId() {
        long nextId = getCurrentId();
        setCurrentId(nextId + 1);
        return nextId;
    }

}
