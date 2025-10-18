package calculator;

import java.util.List;

public class CalculatorController {
    private final ConsoleView view;
    private final InputParser parser;
    private final CalculatorModel model;

    public CalculatorController(
            ConsoleView view,
            InputParser parser,
            CalculatorModel model) {

        this.view = view;
        this.parser = parser;
        this.model = model;
    }

    public void run() {
        String inputString = view.getInput("덧셈할 문자열을 입력해 주세요.");

        List<Float> numbers = parser.parseString(inputString);

        Float result = model.addNumbers(numbers);

        view.printText("결과 : " + result);

    }
}
