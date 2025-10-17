package calculator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class InputParserTest {
    private final InputParser inputParser = new InputParser();


    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n"})
    void test_ValidateInput_Error(String input) {
        assertThrows(IllegalArgumentException.class, () -> {
            inputParser.validateInput(input);
        });
    }

    @Test
    void test_ValidateInput_Pass() {
        String validInput = "1,2:3";

        assertDoesNotThrow(() -> {
            inputParser.validateInput(validInput);
        });
    }

    @Test
    void test_ParseCustomSeperator_Pass() {
        String validInput = "//a\n1,2";

        String extractedDelimiter = inputParser.parseCustomSeperator(validInput);

        assertEquals("a", extractedDelimiter);
    }

    @Test
    void test_ParseCustomSeperator_Return_Null() {
        String normalInput = "1,2,3";

        String result = inputParser.parseCustomSeperator(normalInput);

        assertNull(result);
    }

    @Test
    void test_ParseCustomSeperator_Error_InvalidFormat() {
        String invalidFormat = "a\n1,2";

        assertThrows(IllegalArgumentException.class, () -> {
            inputParser.parseCustomSeperator(invalidFormat);
        });
    }

    @Test
    void test_ParseCustomSeperator_Error_InvalidFormat2() {
        String twoCharDelimiter = "a//b\n1,2";

        assertThrows(IllegalArgumentException.class, () -> {
            inputParser.parseCustomSeperator(twoCharDelimiter);
        });
    }

    @Test
    void test_ParseCustomSeperator_Error_EmptySeperator() {
        String emptyDelimiter = "//\n1,2";

        assertThrows(IllegalArgumentException.class, () -> {
            inputParser.parseCustomSeperator(emptyDelimiter);
        });
    }

    @Test
    void test_ParseCustomSeperator_Error_TooManySeperator() {
        String twoCharDelimiter = "//ab\n1,2";

        assertThrows(IllegalArgumentException.class, () -> {
            inputParser.parseCustomSeperator(twoCharDelimiter);
        });
    }
}