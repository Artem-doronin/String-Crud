package com.example;

import java.util.Scanner;

public class Controller {
    private final Service service;
    private final Scanner scanner;
    private final Parser parser;


    public Controller(Service service, Parser parser) {
        this.service = service;
        this.parser = parser;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.print("\n> ");
            String str = scanner.nextLine();

            if (str.equalsIgnoreCase("EXIT")) {
                break;
            }
            Command command = parser.parse(str);
            if (command == null) {
                System.out.println("Ошибка: не удалось распарсить команду");
                continue;
            }
            String cmdType = command.getCommand().toUpperCase();
            switch (cmdType) {
                case "CREATE":
                    service.create(command);
                    break;

                case "UPDATE":
                    service.updateToId(command);
                    break;

                case "DELETE":
                    service.deleteToId(command);
                    break;

                case "GET":
                    if (command.getAvailabilityOfIdInRequest()) {
                        service.getToId(command);
                    } else {
                        service.getAll();
                    }
                    break;

                default:
                    System.out.println("Неизвестная команда: " + cmdType);
            }
        }
        scanner.close();
        service.saveMapBySerialization();
        System.out.println("Программа завершена. Данные сохранены.");
    }
}
