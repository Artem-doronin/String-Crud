package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;

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
        try {
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
               processCommand(command);

            }
            scanner.close();
            service.saveMapBySerialization();
            System.out.println("Программа завершена. Данные сохранены.");
        } catch (JsonProcessingException e) {
            System.err.println("Ошибка сериализации : " + e.getMessage());
        }catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void processCommand(Command command) {
        String cmdType = command.getCommand().toUpperCase();

        switch (cmdType) {
            case "CREATE":
                service.create(command);
                break;
            case "UPDATE":
                service.updateById(command);
                break;
            case "DELETE":
                service.deleteById(command);
                break;
            case "GET":
                if (command.getAvailabilityOfIdInRequest()) {
                    service.getById(command);
                } else {
                    service.getAll();
                }
                break;
            default:
                System.out.println("Неизвестная команда: " + cmdType);
        }
    }
}




