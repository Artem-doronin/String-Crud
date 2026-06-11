package com.example.test;

import com.example.ExampleValidator;
import com.example.Validator;

public class ValidatorTest {
        public static void main(String[] args) {
            Validator validator = new ExampleValidator();

            System.out.println("=== Тест исключений Validator ===\n");

            // Тест 1: isEmpty с null
            ExceptionAssert.assertThrowsWithMessage(
                    NullPointerException.class,
                    "Строка не может быть пустой",
                    () -> validator.isEmpty(null)
            );
            System.out.println("✓ isEmpty(null) -> NullPointerException");

            // Тест 2: isEmpty с пустой строкой
            ExceptionAssert.assertThrowsWithMessage(
                    NullPointerException.class,
                    "Строка не может быть пустой",
                    () -> validator.isEmpty("")
            );
            System.out.println("✓ isEmpty(\"\") -> NullPointerException");

            // Тест 3: isValidCommand с неизвестной командой
            ExceptionAssert.assertThrowsWithMessage(
                    IllegalArgumentException.class,
                    "Неизвестная команда: UNKNOWN",
                    () -> validator.isValidCommand("UNKNOWN")
            );
            System.out.println("✓ isValidCommand(\"UNKNOWN\") -> IllegalArgumentException");

            // Тест 4: isValidId с не числом
            ExceptionAssert.assertThrowsWithMessage(
                    IllegalArgumentException.class,
                    "ID должен быть числом: abc",
                    () -> validator.isValidId("abc")
            );
            System.out.println("✓ isValidId(\"abc\") -> IllegalArgumentException");

            // Тест 5: isValidId с нулем
            ExceptionAssert.assertThrowsWithMessage(
                    IllegalArgumentException.class,
                    "ID должен быть положительным числом",
                    () -> validator.isValidId("0")
            );
            System.out.println("✓ isValidId(\"0\") -> IllegalArgumentException");

            // Тест 6: validateCreateCommand без текста
            ExceptionAssert.assertThrowsWithMessage(
                    IllegalArgumentException.class,
                    "CREATE требует текст",
                    () -> validator.validateCreateCommand(new String[]{"CREATE"})
            );
            System.out.println("✓ validateCreateCommand без текста -> IllegalArgumentException");

            // Тест 7: проверка что НЕТ исключения
            ExceptionAssert.assertDoesNotThrow(() -> {
                validator.isValidCommand("CREATE");
            });
            System.out.println("✓ isValidCommand(\"CREATE\") -> нет исключения");

            System.out.println("\n✅ Все тесты исключений пройдены!");
        }
    }

