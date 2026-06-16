//import com.example.Command;
//import com.example.ExampleValidator;
//import com.example.Parser;
//import com.example.Validator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ParserTest {
//
//    private Parser parser;
//    private Validator validator;
//
//    @BeforeEach
//    void setUp() {
//        validator = new ExampleValidator();
//        parser = new Parser(validator);
//    }
//
//    // ============ Тесты CREATE ============
//
//    @Test
//    void parse_shouldParseCreateCommand() {
//        Command command = parser.parse("CREATE Hello World");
//
//        assertNotNull(command);
//        assertEquals("CREATE", command.getCommand());
//        assertEquals("Hello World", command.getValue());
//        assertNull(command.getId());
//        assertFalse(command.getAvailabilityOfIdInRequest());
//    }
//
//    @Test
//    void parse_shouldParseCreateCommandWithSpecialChars() {
//        Command command = parser.parse("CREATE !@#$%^&*()");
//
//        assertNotNull(command);
//        assertEquals("!@#$%^&*()", command.getValue());
//    }
//
//    @Test
//    void parse_shouldParseCreateCommandWithNumbers() {
//        Command command = parser.parse("CREATE 123 456");
//
//        assertNotNull(command);
//        assertEquals("123 456", command.getValue());
//    }
//
//    @Test
//    void parse_shouldReturnNullForCreateWithoutText() {
//        Command command = parser.parse("CREATE");
//        assertNull(command);
//    }
//
//    // ============ Тесты GET ============
//
//    @Test
//    void parse_shouldParseGetWithoutId() {
//        Command command = parser.parse("GET");
//
//        assertNotNull(command);
//        assertEquals("GET", command.getCommand());
//        assertNull(command.getId());
//        assertFalse(command.getAvailabilityOfIdInRequest());
//    }
//
//    @Test
//    void parse_shouldParseGetWithId() {
//        Command command = parser.parse("GET 123");
//
//        assertNotNull(command);
//        assertEquals("GET", command.getCommand());
//        assertEquals(123L, command.getId());
//        assertTrue(command.getAvailabilityOfIdInRequest());
//    }
//
//    @Test
//    void parse_shouldParseGetWithLargeId() {
//        Command command = parser.parse("GET 999999");
//
//        assertNotNull(command);
//        assertEquals(999999L, command.getId());
//    }
//
//    @Test
//    void parse_shouldReturnNullForGetWithInvalidId() {
//        Command command = parser.parse("GET abc");
//        assertNull(command);
//    }
//
//    @Test
//    void parse_shouldReturnNullForGetWithZeroId() {
//        Command command = parser.parse("GET 0");
//        assertNull(command);
//    }
//
//    // ============ Тесты UPDATE ============
//
//    @Test
//    void parse_shouldParseUpdateCommand() {
//        Command command = parser.parse("UPDATE 1 New Value");
//
//        assertNotNull(command);
//        assertEquals("UPDATE", command.getCommand());
//        assertEquals(1L, command.getId());
//        assertEquals("New Value", command.getValue());
//        assertTrue(command.getAvailabilityOfIdInRequest());
//    }
//
//    @Test
//    void parse_shouldReturnNullForUpdateWithoutId() {
//        Command command = parser.parse("UPDATE");
//        assertNull(command);
//    }
//
//    @Test
//    void parse_shouldReturnNullForUpdateWithInvalidId() {
//        Command command = parser.parse("UPDATE abc text");
//        assertNull(command);
//    }
//
//    @Test
//    void parse_shouldReturnNullForUpdateWithoutText() {
//        Command command = parser.parse("UPDATE 1");
//        assertNull(command);
//    }
//
//    // ============ Тесты DELETE ============
//
//    @Test
//    void parse_shouldParseDeleteCommand() {
//        Command command = parser.parse("DELETE 5");
//
//        assertNotNull(command);
//        assertEquals("DELETE", command.getCommand());
//        assertEquals(5L, command.getId());
//        assertTrue(command.getAvailabilityOfIdInRequest());
//    }
//
//    @Test
//    void parse_shouldReturnNullForDeleteWithoutId() {
//        Command command = parser.parse("DELETE");
//        assertNull(command);
//    }
//
//    @Test
//    void parse_shouldReturnNullForDeleteWithInvalidId() {
//        Command command = parser.parse("DELETE abc");
//        assertNull(command);
//    }
//
//    // ============ Тесты граничных случаев ============
//
//
//    @Test
//    void parse_shouldIgnoreCase() {
//        Command command = parser.parse("create Hello");
//
//        assertNotNull(command);
//        assertEquals("CREATE", command.getCommand());
//        assertEquals("Hello", command.getValue());
//    }
//
//    @Test
//    void parse_shouldParseCommandWithExtraSpaces() {
//        Command command = parser.parse("   CREATE   Hello   ");
//
//        assertNotNull(command);
//        assertEquals("Hello", command.getValue().trim());
//    }
//
//    @Test
//    void parse_shouldAcceptExactly1000Characters() {
//        String text = "a".repeat(1000);
//        Command command = parser.parse("CREATE " + text);
//
//        assertNotNull(command);
//        assertEquals(text, command.getValue());
//    }
//
//    @Test
//    void parse_shouldRejectMoreThan1000Characters() {
//        String text = "a".repeat(1001);
//        Command command = parser.parse("CREATE " + text);
//
//        assertNull(command);
//    }
//}