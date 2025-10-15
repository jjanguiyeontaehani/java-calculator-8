package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class ConsoleViewTest extends NsTest {
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


    @Test
    void testConsoleView_printText() {
        // todo: failed to use System.setIn, System.setOut should find replacing it
    }

    @Override
    public void runMain() {

    }
}
