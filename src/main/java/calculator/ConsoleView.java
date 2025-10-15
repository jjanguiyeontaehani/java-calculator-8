package calculator;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleView {

    public String getInput(String inputPrintText) {
        printText(inputPrintText);

        return Console.readLine();
    }

    public void printText(String PrintText) {
        System.out.println(PrintText);
    }
}