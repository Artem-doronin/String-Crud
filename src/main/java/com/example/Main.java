package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\n> ");
            String str = scanner.nextLine();


            if (str.equalsIgnoreCase("EXIT")) {
                break;
            }

            Command command = Parser.parse(str);

            // Проверка на null после парсинга
            if (command == null) {
                System.out.println("Ошибка: не удалось распарсить команду");
                continue;
            }

            String cmdType = command.getCommand().toUpperCase();

            switch (cmdType) {
                case "CREATE":
                    Repository.create(command);
                    break;

                case "UPDATE":
                    Repository.updateToId(command);
                    break;

                case "DELETE":
                    Repository.deleteToId(command);
                    break;

                case "GET":
                    if (command.getAvailabilityOfIdInGet()) {
                        Repository.getToId(command);
                    } else {
                        Repository.getAll();
                    }
                    break;

                default:
                    System.out.println("Неизвестная команда: " + cmdType);
            }
        }

        scanner.close();
        Repository.saveMapBySerialization();
        System.out.println("Программа завершена. Данные сохранены.");
    }

}