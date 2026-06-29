package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        AppConfig.printConfig();
        ObjectMapper objectMapper = new ObjectMapper();
        PersonMapper personMapper = new PersonMapper(objectMapper);
        DataLoader loader = new DataLoader(personMapper);
        Repository repository;
        Scanner scanner = new Scanner(System.in);
        Validator validator = new ExampleValidator();
        Parser parser = new Parser(validator,personMapper);

        if (AppConfig.isDbMode()) {
            System.out.println("=== 🗄️ Запуск с PostgreSQL ===");
            DatabaseConnection dbConnection = new DatabaseConnection();
            repository = new RepositoryDb(dbConnection);
        } else {
            System.out.println("=== 💾 Запуск с InMemory ===");
            repository = new InMemoryRepository(loadData(loader));
        }
        Service service = new Service(repository, loader);
        Controller controller = new Controller(service, parser, scanner);
        controller.start();
    }

    private static Map<Long, Person> loadData(DataLoader dataLoader) {
        List<Person> loaded = dataLoader.loadData();
        if (loaded != null && !loaded.isEmpty()) {
            Map<Long, Person> loadedMap = loaded.stream()
                    .collect(Collectors.toMap(Person::getId, person -> person));
            System.out.println("✓ Загружено " + loaded.size() + " записей");
            return loadedMap;
        } else {
            System.out.println("✓ Новое хранилище (данных нет)");
            return Collections.emptyMap();
        }
    }
}

