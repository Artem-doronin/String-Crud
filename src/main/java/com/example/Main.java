package com.example;

public class Main {

    public static void main(String[] args) {
        Service service = new Service(new Parser(new ExampleValidator()),new Repository(new LoudStorage()),new LoudStorage());
        service.start();


    }
}