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
        while (true) {
            try {
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
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                System.err.println("Ошибка десериализации : " + e.getMessage());
            } catch (NullPointerException | IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

        try {
            scanner.close();
            service.exitToSave();
        } catch (JsonProcessingException e) {
            System.err.println("Ошибка сериализации : " + e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Не удалось сохранить данные:  " + e.getMessage());
        }
        System.out.println("Программа завершена. Данные сохранены.");
    }

    private void processCommand(Command command) {
        String cmdType = command.getCommand().toUpperCase();
        switch (cmdType) {
            case "CREATE":
                service.create(command.getValue());
                break;
            case "UPDATE":
                service.updateById(command.getValue());
                break;
            case "DELETE":
                service.deleteById(command.getId());
                break;
            case "GET":
                if (command.getId()!=null) {
                    System.out.println(command.getId() + "Id command");
                    service.getById(command.getId());
                } else {
                    service.getAll();
                }
                break;
            default:
                System.out.println("Неизвестная команда: " + cmdType);
        }
    }
}




