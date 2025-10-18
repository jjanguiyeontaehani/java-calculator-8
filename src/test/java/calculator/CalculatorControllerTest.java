package calculator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorControllerTest {
    private InputStream originalSystemIn;
    private PrintStream originalSystemOut;
    private ByteArrayOutputStream actualOutputStream;

    private CalculatorController controller;

    @BeforeEach
    void setUp() {
        originalSystemIn = System.in;
        originalSystemOut = System.out;

        actualOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutputStream));

        ConsoleView view = new ConsoleView();
        InputParser parser = new InputParser();
        CalculatorModel model = new CalculatorModel();
        controller = new CalculatorController(view, parser, model);
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    void run_ShouldProcessValidInputAndPrintResult() {
        String expectedInputPrintText = "덧셈할 문자열을 입력해 주세요." + System.lineSeparator() +
                "결과 : " + 6;
        String testUserInputText = "1,2:3";

        System.setIn(new ByteArrayInputStream(testUserInputText.getBytes()));

        assertDoesNotThrow(controller::run);

        System.setOut(System.out);
        Assertions.assertEquals(expectedInputPrintText, actualOutputStream.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "a\n1,2", "//a1,2", "a//b\n1,2", "////a\n1,2", "//a\n\n1,2",
            "//\n1,2", "//ab\n1,2"
    })
    void run_ShouldCatchParsingErrorAndPrintError(String testUserInputText) {
        System.setIn(new ByteArrayInputStream(testUserInputText.getBytes()));

        assertThrows(IllegalArgumentException.class, controller::run);
    }
}