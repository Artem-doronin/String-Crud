package com.example;


import java.util.Map;

public class Main {

    public static void main(String[] args) {
        LoudStorage loudStorage = new LoudStorage();
        Map<Long, Person> longPersonMap = loudStorage.loadMapBySerialization();
        Repository repository = new Repository(longPersonMap);
        Service service = new Service(new Parser(new ExampleValidator()), repository, loudStorage);
        service.start();
    }
}

