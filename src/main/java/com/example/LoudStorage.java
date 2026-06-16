package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LoudStorage {
    private static final String STORAGE_FILE = "commands.dat";
    private static final Long COUNTER_KEY = -1L;
    private Map<Long, String> map;


    public LoudStorage() {
        this.map = new HashMap<>();
        loadMapBySerialization();
    }

    public Map<Long, String> getMap(){
        return map;
    }

    public void setMap(Map<Long, String> map) {
        this.map = map;
    }

    public void saveMapBySerialization(Map<Long, String> map) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORAGE_FILE))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Long,String > loadMapBySerialization() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STORAGE_FILE))) {
           return (Map<Long, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }return Collections.emptyMap();
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
    public Long generateId() {
        long nextId = getCurrentId();
        setCurrentId(nextId + 1);
        return nextId;
    }

}
