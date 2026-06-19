package com.example;

public class Main {


    public static void main(String[] args) {
        LoudStorage loudStorage = new LoudStorage();
        Repository repository = new Repository(loudStorage);
        Service service = new Service(repository, loudStorage);
        Controller controller = new Controller(service, new Parser(new ExampleValidator()));
        controller.start();
    }
}

