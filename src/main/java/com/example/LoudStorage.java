package com.example;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class LoudStorage {
    private static final String STORAGE_FILE = "command.dat";

    public void saveMapBySerialization(Map<Long, Person> map) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORAGE_FILE))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Long, Person> loadMapBySerialization() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STORAGE_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<Long, Person> loaded = (Map<Long, Person>) obj;
                return loaded;
            }
            return new HashMap<>();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            return new HashMap<>();
        } catch (EOFException e) {
            System.err.println("Файл пустой");
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }


}
