package com.example;


import com.example.test.ParserTest;
import com.example.test.ValidatorTest;

public class Main {


    public static void main(String[] args) {

        System.out.println("\n========================================");
        System.out.println("ЗАПУСК ТЕСТОВ ПАРСЕРА");
        System.out.println("========================================");

        ParserTest.main(args);
        ValidatorTest.main(args);
    }

}

