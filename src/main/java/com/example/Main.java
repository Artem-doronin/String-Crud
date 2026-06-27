package com.example;

public class Main {


    public static void main(String[] args) {
        Repository repository = new RepositoryDb();
        Service service = new Service(repository);
        Controller controller = new Controller(service, new Parser(new ExampleValidator()));
        controller.start();
    }
}

