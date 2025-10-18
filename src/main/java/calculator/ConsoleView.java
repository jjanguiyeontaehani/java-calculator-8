package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.text.DecimalFormat;

public class ConsoleView {

    public String getInput(String inputPrintText) {
        printText(inputPrintText + System.lineSeparator());

        return Console.readLine();
    }

    private void printText(String PrintText) {
        System.out.print(PrintText);
    }

    public void printResult(Float result) {
        String formattedResult = formatResult(result);
        printText("결과 : " + formattedResult);
    }

    public void closeConsole() {
        Console.close();
    }

    private String formatResult(Float result) {
        DecimalFormat df = new DecimalFormat("#.#####");
        return df.format(result);
    }
}