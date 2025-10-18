package calculator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class ConsoleViewTest {
    ConsoleView consoleViewInstance = new ConsoleView();

    @Test
    void testConsoleView_getInput() {
        String testInputPrintText = "덧셈할 문자열을 입력해 주세요.";
        String expectedInputPrintText = testInputPrintText + System.lineSeparator();
        String testUserInputText = "1,2:3";

        System.setIn(new ByteArrayInputStream(testUserInputText.getBytes()));
        ByteArrayOutputStream actualOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutputStream));

        String actualReturnText = consoleViewInstance.getInput(testInputPrintText);

        System.setOut(System.out);

        Assertions.assertEquals(expectedInputPrintText, actualOutputStream.toString());
        Assertions.assertEquals(testUserInputText, actualReturnText);
    }

    @ParameterizedTest
    @ValueSource(floats = {
            1.1f,
            2.0f
    })
    void testConsoleView_printResult(Float result) {
        int intValue = result.intValue();
        String stringValue = String.valueOf(result);

        if (result == (float) intValue) {
            stringValue = String.valueOf(intValue);
        }
        String expectedOutString = "결과 : " + stringValue;

        ByteArrayOutputStream actualOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutputStream));

        consoleViewInstance.printResult(result);

        System.setOut(System.out);

        Assertions.assertEquals(expectedOutString, actualOutputStream.toString());
    }
}
