package calculator;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
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

        Assertions.assertDoesNotThrow(() -> {
            inputParser.validateInput(validInput);
        });
    }
}