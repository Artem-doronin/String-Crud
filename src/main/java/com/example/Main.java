package com.example;

public class Main {


    public static void main(String[] args) {
        LoudStorage loudStorage = new LoudStorage();
        RepositoryDb repository = new RepositoryDb(loudStorage);
        Service service = new Service(repository, loudStorage);
        Controller controller = new Controller(service, new Parser(new ExampleValidator()));
        controller.start();
    }
}

