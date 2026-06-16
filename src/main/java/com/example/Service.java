package com.example;

import java.util.Scanner;

public class Service {
    private final Parser parser;
    private final Scanner scanner;
    private final Repository repo;
    private final LoudStorage loudStorage;

    public Service(Parser parser, Repository repo,LoudStorage loudStorage) {
        this.parser = parser;
        this.scanner = new Scanner(System.in);
        this.repo = repo;
        this.loudStorage = loudStorage;
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
                repo.create(command);
                break;

            case "UPDATE":
                repo.updateToId(command);
                break;

            case "DELETE":
                repo.deleteToId(command);
                break;

            case "GET":
                if (command.getAvailabilityOfIdInRequest()) {
                    repo.getToId(command);
                } else {
                    repo.getAll();
                }
                break;

            default:
                System.out.println("Неизвестная команда: " + cmdType);
        }
    }

        scanner.close();
        loudStorage.saveMapBySerialization(repo.getMap());
        System.out.println("Программа завершена. Данные сохранены.");
}
}
