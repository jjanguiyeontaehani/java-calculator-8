package calculator;

import java.util.List;

public class CalculatorModel {
    public Float addNumbers(List<Float> inputNumbers) {
        Float sum = 0.0f;

        for (Float number : inputNumbers) {
            sum += number;
        }
        
        return sum;
    }
}