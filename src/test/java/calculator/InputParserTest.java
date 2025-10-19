package calculator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputParserTest {
    private final InputParser inputParser = new InputParser();

    @ParameterizedTest
    @ValueSource(strings = {
            "1,a,2", "a1", "1,-2", "0,1", "//ab\n1a2"
    })
    void test_ParseString_Error_Invalid_Format(String invalidFormat) {

        assertThrows(IllegalArgumentException.class, () -> {
            inputParser.parseString(invalidFormat);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", " ", "\t", "1", "1,2", "//a\n1a2", "//3\n132", "//3\n132,4:5"
    })
    void test_ParseString_Pass(String validFormat) {

        assertDoesNotThrow(() -> {
            inputParser.parseString(validFormat);
        });
    }

    @Test
    void test_ParseString_Value() {
        String validInput = "//3\n132,,4:5";
        List<Float> expectedOutput = Arrays.asList(
                1.0f,
                2.0f,
                0.0f,
                4.0f,
                5.0f
        );

        List<Float> result = inputParser.parseString(validInput);

        assertEquals(expectedOutput, result);
    }

    @Test
    void test_ParseCustomSeparator_Pass() {
        String validInput = "//a\n1,2";

        String extractedDelimiter = inputParser.parseCustomSeparator(validInput);

        assertEquals("a", extractedDelimiter);
    }

    @Test
    void test_ParseCustomSeparator_Return_Null() {
        String normalInput = "1,2,3";

        String result = inputParser.parseCustomSeparator(normalInput);

        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "a\n1,2", "//a1,2", "a//b\n1,2", "////a\n1,2", "//a\n\n1,2"
    })
    void test_ParseCustomSeparator_Error_InvalidFormat(String invalidFormat) {

        assertThrows(IllegalArgumentException.class, () -> {
            inputParser.parseCustomSeparator(invalidFormat);
        });
    }

    @Test
    void test_ParseCustomSeparator_Error_EmptySeparator() {
        String emptyDelimiter = "//\n1,2";

        assertThrows(IllegalArgumentException.class, () -> {
            inputParser.parseCustomSeparator(emptyDelimiter);
        });
    }

    @Test
    void test_ParseCustomSeparator_Error_TooManySeparator() {
        String twoCharDelimiter = "//ab\n1,2";

        assertThrows(IllegalArgumentException.class, () -> {
            inputParser.parseCustomSeparator(twoCharDelimiter);
        });
    }
}