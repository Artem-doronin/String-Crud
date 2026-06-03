package com.example;

import java.util.Arrays;

public class Parser {
    public static Command parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            System.err.println("Ошибка: пустая команда");
            return null;
        }

        String[] array = input.split(" ");
        String strCommand = array[0].toUpperCase();


        if (array.length < 2 && strCommand.equals("GET")) {

            return new Command(null, false, "GET", "");
        }

        if (array.length < 2 && strCommand.equals("CREATE")) {
            System.err.println("Ошибка: CREATE требует текст");
            return null;
        }

        Long id = null;
        String value = "";
        boolean availabilityOfIdInRequest = false;

        try {
            if (strCommand.equals("CREATE")) {
                availabilityOfIdInRequest = false;
                String[] result = Arrays.copyOfRange(array, 1, array.length);
                value = String.join(" ", result);

                if (value.isEmpty()) {
                    System.err.println("Ошибка: CREATE требует непустое значение");
                    return null;
                }

            } else if (strCommand.equals("GET")) {

                if (array.length == 1) {
                    availabilityOfIdInRequest = false;
                    id = null;
                    value = "";
                } else {
                    String strId = array[1];
                    if (!isLong(strId)) {
                        System.err.println("Ошибка: ID должен быть числом");
                        return null;
                    }
                    id = Long.parseLong(strId);
                    availabilityOfIdInRequest = true;
                    value = "";
                }

            } else if (strCommand.equals("UPDATE") || strCommand.equals("DELETE")) {
                if (array.length < 2) {
                    System.err.println("Ошибка: " + strCommand + " требует ID");
                    return null;
                }

                String strId = array[1];
                if (!isLong(strId)) {
                    System.err.println("Ошибка: ID должен быть числом");
                    return null;
                }

                id = Long.parseLong(strId);
                availabilityOfIdInRequest = true;

                if (array.length > 2) {
                    String[] result = Arrays.copyOfRange(array, 2, array.length);
                    value = String.join(" ", result);
                } else {
                    value = "";
                }

            } else {
                System.err.println("Ошибка: неизвестная команда '" + strCommand + "'");
                return null;
            }

            return new Command(id, availabilityOfIdInRequest, strCommand, value);

        } catch (Exception e) {
            System.err.println("Ошибка парсинга: " + e.getMessage());
            return null;
        }
    }

    private static boolean isLong(String str) {
        if (str == null) {
            return false;
        }
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
