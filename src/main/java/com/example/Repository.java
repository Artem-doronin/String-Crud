package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    private static final String STORAGE_FILE = "commands.dat";

    private static Map<Long, String> storage = new HashMap<>();

    static {
        loadMapBySerialization();
    }


    public static void saveMapBySerialization() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORAGE_FILE))) {
            oos.writeObject(storage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadMapBySerialization() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STORAGE_FILE))) {
            storage = (Map<Long, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void create(Command command) {
        command.setId((long) (storage.size() + 1));
        storage.put(command.getId(), command.getValue());
        System.out.println("String saved with id = {" + command.getId() + "}");
    }

    public static void updateToId(Command command) {
        storage.put(command.getId(), command.getValue());
        System.out.println("String  with id = {" + command.getId() + "} updated");
    }

    public static void getAll() {
        for (String s : storage.values()) {
            System.out.println(s);
        }
    }

    public static void getToId(Command command) {
        System.out.println(storage.get(command.getId()));
    }

    public static void deleteToId(Command command) {
        System.out.println("String with id = " + command.getId() + " deleted");
    }

}
