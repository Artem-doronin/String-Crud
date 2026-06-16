//
//import com.example.ExampleValidator;
//import com.example.Validator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ExampleValidatorTest {
//
//    private Validator validator;
//
//    @BeforeEach
//    void setUp() {
//        validator = new ExampleValidator();
//    }
//
//    // ============ Тесты isEmpty ============
//
//    @Test
//    void isEmpty_shouldThrowExceptionWhenNull() {
//        assertThrows(NullPointerException.class, () -> validator.isEmpty(null));
//    }
//
//    @Test
//    void isEmpty_shouldThrowExceptionWhenEmpty() {
//        NullPointerException exception = assertThrows(
//                NullPointerException.class,
//                () -> validator.isEmpty("")
//        );
//        assertEquals("Строка не может быть пустой", exception.getMessage());
//    }
//
//    @Test
//    void isEmpty_shouldThrowExceptionWhenOnlySpaces() {
//        assertThrows(NullPointerException.class, () -> validator.isEmpty("   "));
//    }
//
//    @Test
//    void isEmpty_shouldNotThrowExceptionWhenValid() {
//        assertDoesNotThrow(() -> validator.isEmpty("Hello"));
//    }
//
//    // ============ Тесты isValidCommand ============
//
//    @Test
//    void isValidCommand_shouldNotThrowExceptionWhenValidCommand() {
//        assertDoesNotThrow(() -> validator.isValidCommand("CREATE"));
//        assertDoesNotThrow(() -> validator.isValidCommand("GET"));
//        assertDoesNotThrow(() -> validator.isValidCommand("UPDATE"));
//        assertDoesNotThrow(() -> validator.isValidCommand("DELETE"));
//    }
//
//    @Test
//    void isValidCommand_shouldNotThrowExceptionWhenLowerCase() {
//        assertDoesNotThrow(() -> validator.isValidCommand("create"));
//        assertDoesNotThrow(() -> validator.isValidCommand("get"));
//    }
//
//    @Test
//    void isValidCommand_shouldThrowExceptionWhenNull() {
//        assertThrows(IllegalArgumentException.class, () -> validator.isValidCommand(null));
//    }
//
//    @Test
//    void isValidCommand_shouldThrowExceptionWhenEmpty() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.isValidCommand("")
//        );
//        assertEquals("Команда не может быть пустой", exception.getMessage());
//    }
//
//    @Test
//    void isValidCommand_shouldThrowExceptionWhenUnknownCommand() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.isValidCommand("UNKNOWN")
//        );
//        assertEquals("Неизвестная команда: UNKNOWN", exception.getMessage());
//    }
//
//    // ============ Тесты isValidId ============
//
//    @Test
//    void isValidId_shouldNotThrowExceptionWhenValidId() {
//        assertDoesNotThrow(() -> validator.isValidId("1"));
//        assertDoesNotThrow(() -> validator.isValidId("100"));
//        assertDoesNotThrow(() -> validator.isValidId("999999"));
//    }
//
//    @Test
//    void isValidId_shouldThrowExceptionWhenNull() {
//        assertThrows(NullPointerException.class, () -> validator.isValidId(null));
//    }
//
//    @Test
//    void isValidId_shouldThrowExceptionWhenEmpty() {
//        NullPointerException exception = assertThrows(
//                NullPointerException.class,
//                () -> validator.isValidId("")
//        );
//        assertEquals("ID не может быть пустым", exception.getMessage());
//    }
//
//    @Test
//    void isValidId_shouldThrowExceptionWhenNotNumber() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.isValidId("abc")
//        );
//        assertEquals("ID должен быть числом: abc", exception.getMessage());
//    }
//
//    @Test
//    void isValidId_shouldThrowExceptionWhenZero() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.isValidId("0")
//        );
//        assertEquals("ID должен быть положительным числом", exception.getMessage());
//    }
//
//    @Test
//    void isValidId_shouldThrowExceptionWhenNegative() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.isValidId("-5")
//        );
//        assertEquals("ID должен быть положительным числом", exception.getMessage());
//    }
//
//    // ============ Тесты validateCreateCommand ============
//
//    @Test
//    void validateCreateCommand_shouldReturnTextWhenValid() {
//        String[] tokens = {"CREATE", "Hello", "World"};
//        String result = validator.validateCreateCommand(tokens);
//        assertEquals("Hello World", result);
//    }
//
//    @Test
//    void validateCreateCommand_shouldThrowExceptionWhenNoText() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.validateCreateCommand(new String[]{"CREATE"})
//        );
//        assertEquals("CREATE требует текст", exception.getMessage());
//    }
//
//    @Test
//    void validateCreateCommand_shouldThrowExceptionWhenEmptyText() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.validateCreateCommand(new String[]{"CREATE", ""})
//        );
//        assertEquals("CREATE требует непустое значение", exception.getMessage());
//    }
//
//    @Test
//    void validateCreateCommand_shouldThrowExceptionWhenTooLong() {
//        String longText = "a".repeat(1001);
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.validateCreateCommand(new String[]{"CREATE", longText})
//        );
//        assertEquals("Текст слишком длинный (максимум 1000 символов)", exception.getMessage());
//    }
//
//    @Test
//    void validateCreateCommand_shouldAcceptWhenExactly1000() {
//        String longText = "a".repeat(1000);
//        String result = validator.validateCreateCommand(new String[]{"CREATE", longText});
//        assertEquals(longText, result);
//    }
//
//    // ============ Тесты validateUpdateCommand ============
//
//    @Test
//    void validateUpdateCommand_shouldNotThrowExceptionWhenValid() {
//        assertDoesNotThrow(() -> validator.validateUpdateCommand(
//                new String[]{"UPDATE", "1", "New", "Value"}
//        ));
//    }
//
//    @Test
//    void validateUpdateCommand_shouldThrowExceptionWhenNoId() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.validateUpdateCommand(new String[]{"UPDATE"})
//        );
//        assertEquals("UPDATE требует ID", exception.getMessage());
//    }
//
//    @Test
//    void validateUpdateCommand_shouldThrowExceptionWhenInvalidId() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.validateUpdateCommand(new String[]{"UPDATE", "abc", "text"})
//        );
//        assertEquals("ID должен быть числом: abc", exception.getMessage());
//    }
//
//    @Test
//    void validateUpdateCommand_shouldThrowExceptionWhenNoText() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.validateUpdateCommand(new String[]{"UPDATE", "1"})
//        );
//        assertEquals("UPDATE требует текст", exception.getMessage());
//    }
//
//    @Test
//    void validateUpdateCommand_shouldThrowExceptionWhenEmptyText() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.validateUpdateCommand(new String[]{"UPDATE", "1", ""})
//        );
//        assertEquals("UPDATE требует непустое значение", exception.getMessage());
//    }
//
//    // ============ Тесты validateDeleteCommand ============
//
//    @Test
//    void validateDeleteCommand_shouldNotThrowExceptionWhenValid() {
//        assertDoesNotThrow(() -> validator.validateDeleteCommand(
//                new String[]{"DELETE", "5"}
//        ));
//    }
//
//    @Test
//    void validateDeleteCommand_shouldThrowExceptionWhenNoId() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.validateDeleteCommand(new String[]{"DELETE"})
//        );
//        assertEquals("DELETE требует ID", exception.getMessage());
//    }
//
//    @Test
//    void validateDeleteCommand_shouldThrowExceptionWhenInvalidId() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.validateDeleteCommand(new String[]{"DELETE", "abc"})
//        );
//        assertEquals("ID должен быть числом: abc", exception.getMessage());
//    }
//}