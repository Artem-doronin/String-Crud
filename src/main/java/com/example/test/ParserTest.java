package com.example.test;

import com.example.Command;
import com.example.ExampleValidator;
import com.example.Parser;
import com.example.Validator;

public class ParserTest {

    public static void main(String[] args) {
        ParserTest test = new ParserTest();

        test.testCreateCommand();
        test.testGetCommand();
        test.testUpdateCommand();
        test.testDeleteCommand();
        System.out.println("\n✅ Все тесты завершены успешно!");
    }

    private void testCreateCommand() {
        System.out.println("\n=== Тест CREATE команды ===");

        Validator validator = new ExampleValidator();
        Parser parser = new Parser(validator);

        // Тест 1: CREATE с простым текстом
        Command cmd1 = parser.parse("CREATE Hello");
        assertNotNull(cmd1, "CREATE Hello - команда не должна быть null");
        assertCommandEquals("CREATE", cmd1.getCommand(), "Команда должна быть CREATE");
        assertValueEquals("Hello", cmd1.getValue(), "Значение должно быть Hello");
        assertNull(cmd1.getId(), "ID должен быть null");
        System.out.println("  ✓ CREATE Hello прошёл");

        // Тест 2: CREATE с текстом из нескольких слов
        Command cmd2 = parser.parse("CREATE Hello world Java");
        assertNotNull(cmd2, "CREATE Hello world Java - команда не должна быть null");
        assertValueEquals("Hello world Java", cmd2.getValue(), "Значение должно быть 'Hello world Java'");
        System.out.println("  ✓ CREATE Hello world Java прошёл");

        // Тест 3: CREATE с цифрами
        Command cmd3 = parser.parse("CREATE 123 456");
        assertNotNull(cmd3, "CREATE 123 456 - команда не должна быть null");
        assertValueEquals("123 456", cmd3.getValue(), "Значение должно быть '123 456'");
        System.out.println("  ✓ CREATE с цифрами прошёл");

        System.out.println("  ✅ Все CREATE тесты пройдены");
    }

    private void testGetCommand() {
        System.out.println("\n=== Тест GET команды ===");

        Validator validator = new ExampleValidator();
        Parser parser = new Parser(validator);

        // Тест 1: GET без ID
        Command cmd1 = parser.parse("GET");
        assertNotNull(cmd1, "GET - команда не должна быть null");
        assertCommandEquals("GET", cmd1.getCommand(), "Команда должна быть GET");
        assertFalse(cmd1.getAvailabilityOfIdInRequest(), "availabilityOfIdInRequest должен быть false");
        assertNull(cmd1.getId(), "ID должен быть null");
        System.out.println("  ✓ GET (без ID) прошёл");

        // Тест 2: GET с ID
        Command cmd2 = parser.parse("GET 123");
        assertNotNull(cmd2, "GET 123 - команда не должна быть null");
        assertCommandEquals("GET", cmd2.getCommand(), "Команда должна быть GET");
        assertTrue(cmd2.getAvailabilityOfIdInRequest(), "availabilityOfIdInRequest должен быть true");
        assertIdEquals(123L, cmd2.getId(), "ID должен быть 123");
        System.out.println("  ✓ GET 123 прошёл");

        // Тест 3: GET с большим ID
        Command cmd3 = parser.parse("GET 999999");
        assertNotNull(cmd3, "GET 999999 - команда не должна быть null");
        assertIdEquals(999999L, cmd3.getId(), "ID должен быть 999999");
        System.out.println("  ✓ GET 999999 прошёл");

        System.out.println("  ✅ Все GET тесты пройдены");
    }

    private void testUpdateCommand() {
        System.out.println("\n=== Тест UPDATE команды ===");

        Validator validator = new ExampleValidator();
        Parser parser = new Parser(validator);

        // Тест 1: UPDATE с ID и текстом
        Command cmd1 = parser.parse("UPDATE 1 New value");
        assertNotNull(cmd1, "UPDATE 1 New value - команда не должна быть null");
        assertCommandEquals("UPDATE", cmd1.getCommand(), "Команда должна быть UPDATE");
        assertIdEquals(1L, cmd1.getId(), "ID должен быть 1");
        assertValueEquals("New value", cmd1.getValue(), "Значение должно быть 'New value'");
        System.out.println("  ✓ UPDATE 1 New value прошёл");

        // Тест 2: UPDATE с длинным текстом
        Command cmd2 = parser.parse("UPDATE 42 Long text with many words");
        assertNotNull(cmd2, "UPDATE 42 - команда не должна быть null");
        assertIdEquals(42L, cmd2.getId(), "ID должен быть 42");
        assertValueEquals("Long text with many words", cmd2.getValue(), "Значение должно быть корректным");
        System.out.println("  ✓ UPDATE с длинным текстом прошёл");

        System.out.println("  ✅ Все UPDATE тесты пройдены");
    }

    private void testDeleteCommand() {
        System.out.println("\n=== Тест DELETE команды ===");

        Validator validator = new ExampleValidator();
        Parser parser = new Parser(validator);

        // Тест 1: DELETE с ID
        Command cmd1 = parser.parse("DELETE 5");
        assertNotNull(cmd1, "DELETE 5 - команда не должна быть null");
        assertCommandEquals("DELETE", cmd1.getCommand(), "Команда должна быть DELETE");
        assertIdEquals(5L, cmd1.getId(), "ID должен быть 5");
        System.out.println("  ✓ DELETE 5 прошёл");

        // Тест 2: DELETE с большим ID
        Command cmd2 = parser.parse("DELETE 100500");
        assertNotNull(cmd2, "DELETE 100500 - команда не должна быть null");
        assertIdEquals(100500L, cmd2.getId(), "ID должен быть 100500");
        System.out.println("  ✓ DELETE 100500 прошёл");

        System.out.println("  ✅ Все DELETE тесты пройдены");
    }




    // Специализированные методы assert (без перегрузок, уникальные имена)
    private void assertNotNull(Object obj, String message) {
        if (obj == null) {
            throw new AssertionError("❌ " + message);
        }
    }

    private void assertNull(Object obj, String message) {
        if (obj != null) {
            throw new AssertionError("❌ " + message + " (получено: " + obj + ")");
        }
    }

    private void assertCommandEquals(String expected, String actual, String message) {
        if (expected == null && actual == null) return;
        if (expected == null || !expected.equals(actual)) {
            throw new AssertionError("❌ " + message + "\n   Ожидалось: " + expected + "\n   Получено: " + actual);
        }
    }

    private void assertValueEquals(String expected, String actual, String message) {
        if (expected == null && actual == null) return;
        if (expected == null || !expected.equals(actual)) {
            throw new AssertionError("❌ " + message + "\n   Ожидалось: " + expected + "\n   Получено: " + actual);
        }
    }

    private void assertIdEquals(Long expected, Long actual, String message) {
        if (expected == null && actual == null) return;
        if (expected == null || !expected.equals(actual)) {
            throw new AssertionError("❌ " + message + "\n   Ожидалось: " + expected + "\n   Получено: " + actual);
        }
    }

    private void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("❌ " + message);
        }
    }

    private void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError("❌ " + message);
        }
    }
}