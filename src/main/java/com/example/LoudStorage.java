package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoudStorage {
    private static final String STORAGE_FILE = "command.dat";
    private final PersonMapper mapper = new PersonMapper();

    public void saveMapToFile(Map<Long, Person> map) throws JsonProcessingException {
        if (map == null) {
            System.err.println("Ошибка: map не может быть null");
            return;
        }

        String json = mapper.mapToJson(map);
        if (json == null || json.equals("{}")) {
            System.err.println("Ошибка: не удалось сериализовать map");
            return;
        }

        try (FileWriter writer = new FileWriter(STORAGE_FILE)) {
            writer.write(json);
            System.out.println("✓ Сохранено " + map.size() + " записей в " + STORAGE_FILE);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения в файл: " + e.getMessage());
        }
    }

    public Map<Long, Person> loadMapFromFile() throws JsonProcessingException {
        File file = new File(LoudStorage.STORAGE_FILE);

        if (!file.exists()) {
            System.out.println("Файл не найден: " + LoudStorage.STORAGE_FILE);
            return new HashMap<>();
        }

        if (file.length() == 0) {
            System.out.println("Файл пустой: " + LoudStorage.STORAGE_FILE);
            return new HashMap<>();
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            return new HashMap<>();
        }

        String json = content.toString();
        if (json.trim().isEmpty()) {
            return new HashMap<>();
        }
        return mapper.jsonToMap(json);
    }


}
