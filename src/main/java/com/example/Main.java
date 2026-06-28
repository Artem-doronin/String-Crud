package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {

        // 1. Загружаем конфигурацию (уже загружена в статическом блоке)
        AppConfig.printConfig();

        // 2. Запускаем в зависимости от режима
        if (AppConfig.isDbMode()) {
            System.out.println("=== 🗄️ Запуск с PostgreSQL ===");
            startWithDb();
        } else {
            System.out.println("=== 💾 Запуск с InMemory ===");
            startWithInMemory();
        }
    }

    private static void startWithDb() {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Repository repository = new RepositoryDb(dbConnection);
        Validator validator = new ExampleValidator();
        ObjectMapper objectMapper = new ObjectMapper();
        PersonMapper personMapper = new PersonMapper(objectMapper);
        Parser parser = new Parser(validator, personMapper);
        DataLoader loader = new DataLoader(personMapper);
        Scanner scanner = new Scanner(System.in);
        Service service = new Service(repository, loader);
        Controller controller = new Controller(service, parser, scanner);
        controller.start();
    }

    private static void startWithInMemory() {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonMapper personMapper = new PersonMapper(objectMapper);
        DataLoader loader = new DataLoader(personMapper);
        Repository repository = new InMemoryRepository(loadData(loader));
        Validator validator = new ExampleValidator();
        Parser parser = new Parser(validator, personMapper);
        Scanner scanner = new Scanner(System.in);
        Service service = new Service(repository,loader);
        Controller controller = new Controller(service, parser,scanner);
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

