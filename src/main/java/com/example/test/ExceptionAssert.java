package com.example.test;

public class ExceptionAssert {

        // Проверяет, что блок кода выбрасывает исключение
        public static void assertThrows(Class<? extends Exception> expectedException, Runnable code) {
            try {
                code.run();
                throw new AssertionError("❌ Ожидалось исключение: " + expectedException.getSimpleName() + ", но ничего не выброшено");
            } catch (Exception e) {
                if (!expectedException.isInstance(e)) {
                    throw new AssertionError("❌ Ожидалось: " + expectedException.getSimpleName() +
                            ", но получено: " + e.getClass().getSimpleName());
                }
            }
        }

        // Проверяет, что блок кода выбрасывает исключение с определенным сообщением
        public static void assertThrowsWithMessage(Class<? extends Exception> expectedException,
                                                   String expectedMessage,
                                                   Runnable code) {
            try {
                code.run();
                throw new AssertionError("❌ Ожидалось исключение: " + expectedException.getSimpleName());
            } catch (Exception e) {
                if (!expectedException.isInstance(e)) {
                    throw new AssertionError("❌ Ожидалось: " + expectedException.getSimpleName() +
                            ", но получено: " + e.getClass().getSimpleName());
                }
                if (!expectedMessage.equals(e.getMessage())) {
                    throw new AssertionError("❌ Ожидалось сообщение: '" + expectedMessage +
                            "', но получено: '" + e.getMessage() + "'");
                }
            }
        }

        // Проверяет, что блок кода НЕ выбрасывает исключение
        public static void assertDoesNotThrow(Runnable code) {
            try {
                code.run();
            } catch (Exception e) {
                throw new AssertionError("❌ Не ожидалось исключение, но получено: " + e.getClass().getSimpleName());
            }
        }
    }

