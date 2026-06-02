package com.example;

import java.util.Arrays;

public class Parser {
    public Command parse(String input) {
        String[] array = input.split(" ");
        String strCommand = input.split(" ")[0];
        String strId = input.split(" ")[1];
        Long id;
        String value;
        boolean availabilityOfIdInRequest;
        if (strCommand.equals("CREATE") || (strCommand.equals("GET") && (!isLong(strId)))) {
            id = null;
            availabilityOfIdInRequest = false;
            String[] result = Arrays.copyOfRange(array, 1, array.length);
            value = String.join(" ", result);
        } else {
            id = Long.parseLong(strId);
            availabilityOfIdInRequest = true;
            String[] result = Arrays.copyOfRange(array, 2, array.length);
            value = String.join(" ", result);
        }

        return new Command(id, availabilityOfIdInRequest, strCommand, value);
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
