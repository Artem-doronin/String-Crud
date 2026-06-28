package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DataLoader {
    private static final String STORAGE_FILE = "command.dat";
    private final PersonMapper mapper = new PersonMapper();

    public void saveData(List<Person> persons) throws JsonProcessingException {
        Objects.requireNonNull(persons, "List persons is null");

        String json = mapper.listToJson(persons);
        if (json == null || json.equals("{}")) {
            System.err.println("Ошибка: не удалось сериализовать список Person");
            return;
        }

        try (FileWriter writer = new FileWriter(STORAGE_FILE)) {
            writer.write(json);
            System.out.println("✓ Сохранено " + persons.size() + " записей в " + STORAGE_FILE);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения в файл: " + e.getMessage());
        }
    }

    public List<Person> loadData() {
        File file = new File(DataLoader.STORAGE_FILE);

        if (!file.exists()) {
            System.out.println("Файл не найден: " + DataLoader.STORAGE_FILE);
            return Collections.emptyList();
        }

        if (file.length() == 0) {
            System.out.println("Файл пустой: " + DataLoader.STORAGE_FILE);
            return Collections.emptyList();
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            return Collections.emptyList();
        }

        String json = content.toString();
        if (json.trim().isEmpty()) {
            return Collections.emptyList();
        }
        try {
            return mapper.jsonToList(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
