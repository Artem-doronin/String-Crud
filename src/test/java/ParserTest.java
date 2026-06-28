import com.example.Command;
import com.example.ExampleValidator;
import com.example.Parser;
import com.example.Person;
import com.example.PersonMapper;
import com.example.Validator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private Parser parser;
    private Validator validator;
    private PersonMapper mapper;

    @BeforeEach
    void setUp() {
        validator = new ExampleValidator();
        mapper = new PersonMapper(new ObjectMapper());
        parser = new Parser(validator,mapper);
    }


    @Test
    void parse_shouldParseCreateCommand() throws JsonProcessingException {
        Person person = new Person("Ваня", 22);
        Command command = parser.parse("CREATE {\"name\":\"Ваня\",\"age\":22}");

        assertNotNull(command);
        assertEquals("CREATE", command.getCommand());
        assertEquals(person, command.getValue());
        assertNull(command.getId());
    }

    @Test
    void parse_shouldParseCreateCommand1() throws JsonProcessingException {
        Person expected = new Person("Ваня", 22);
        String json = mapper.personToJson(expected);

        Command command = parser.parse("CREATE " + json);

        assertNotNull(command);
        assertEquals("CREATE", command.getCommand());
        assertEquals(expected, command.getValue());
        assertNull(command.getId());
    }

    @Test
    void parse_shouldParseCreateCommandIsValidValue() {
        assertThrows(JsonProcessingException.class, () -> parser.parse("CREATE 123435"));
    }

    @Test
    void parse_shouldParseCreateCommandThrowExceptionWhenNull()  {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    void parse_shouldParseCreateCommandThrowExceptionWhenEmpty()  {
        assertThrows(NullPointerException.class, () -> parser.parse(""));
    }

    @Test
    void parse_shouldParseCreateCommandThrowExceptionWhenInvalidCommand()  {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("INVALID"));
    }


    @Test
    void parse_shouldParseGetWithoutId() throws JsonProcessingException {
        Command command = parser.parse("GET");

        assertNotNull(command);
        assertEquals("GET", command.getCommand());
        assertNull(command.getId());
        assertNull(command.getValue());
    }


    @Test
    void parse_shouldParseGetWithId() throws JsonProcessingException {
        Command command = parser.parse("GET 123");

        assertNotNull(command);
        assertEquals("GET", command.getCommand());
        assertEquals(123L, command.getId());
    }

    @Test
    void parse_shouldParseGetWithLargeId() throws JsonProcessingException {
        Command command = parser.parse("GET 999999");

        assertNotNull(command);
        assertEquals(999999L, command.getId());
    }

    @Test
    void parse_shouldReturnNullForGetWithInvalidId() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> parser.parse("GET abc"));
        assertEquals(illegalArgumentException.getMessage(),"ID должен быть числом: abc");
    }

    @Test
    void parse_shouldReturnNegativeId() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> parser.parse("GET -123"));
        assertEquals(illegalArgumentException.getMessage(),"ID должен быть положительным числом");
    }

    @Test
    void parse_shouldIgnoreCase() throws JsonProcessingException {
        Command command = parser.parse("get");
        assertNotNull(command);
        assertEquals("GET", command.getCommand());

    }


    @Test
    void parse_shouldParseUpdateCommand() throws JsonProcessingException {
        Person expected = new Person("Ваня", 22);
        String json = mapper.personToJson(expected);
        Command command = parser.parse("UPDATE 1 " + json);

        assertNotNull(command);
        assertEquals("UPDATE", command.getCommand());
        assertEquals(1L, command.getId());
        assertEquals(expected, command.getValue());
    }


    @Test
    void parse_shouldReturnNullForUpdateWithInvalidId() throws JsonProcessingException {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> parser.parse("UPDATE abc"));
        assertEquals(illegalArgumentException.getMessage(),"ID должен быть числом: abc");
    }


    @Test
    void parse_shouldReturnNegativeIdForUpdate() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> parser.parse("UPDATE -1"));
        assertEquals(illegalArgumentException.getMessage(),"ID должен быть положительным числом");
    }


    @Test
    void parse_shouldParseDeleteCommand() throws JsonProcessingException {
        Command command = parser.parse("DELETE 5");

        assertNotNull(command);
        assertEquals("DELETE", command.getCommand());
        assertEquals(5L, command.getId());
    }

    @Test
    void parse_shouldReturnNullForDeleteWithoutId()  {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> parser.parse("DELETE"));
        assertEquals(exception.getMessage(),"DELETE требует ID");
    }

    @Test
    void parse_shouldReturnNullForDeleteWithInvalidId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> parser.parse("DELETE abc"));
        assertEquals(exception.getMessage(),"ID должен быть числом: abc");
    }
    @Test
    void parse_shouldParseDeleteWithIdNegative()  {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> parser.parse("DELETE -2"));
        assertEquals(exception.getMessage(),"ID должен быть положительным числом");
    }
}