package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CalculatorModelTest {
    private final CalculatorModel calculatorModel = new CalculatorModel();

    @Test
    void test_AddNumbers_Value() {
        List<Float> testInput = List.of(
                0.0f
        );
        Float expectedOutput = 0.0f;

        Float result = calculatorModel.addNumbers(testInput);

        assertEquals(expectedOutput, result);
    }

    @Test
    void test_AddNumbers_Value2() {
        List<Float> testInput = List.of(
                0.0f,
                1.0f,
                2.0f
        );
        Float expectedOutput = 3.0f;

        Float result = calculatorModel.addNumbers(testInput);

        assertEquals(expectedOutput, result);
    }
}
