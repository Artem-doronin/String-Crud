package com.example;


import java.util.Map;

public class Main {

    public static void main(String[] args) {
        LoudStorage loudStorage = new LoudStorage();
        Map<Long, Person> longStringMap = loudStorage.loadMapBySerialization();
        Repository repository = new Repository(longStringMap);
        Service service = new Service(new Parser(new ExampleValidator()), repository, loudStorage);
        service.start();
    }
}

