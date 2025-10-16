package calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorConfigTest {

    @Test
    void testCalculatorConfig() {
        final String EXPECTED_DEFAULT_SEPERATOR_REGEX = ",|:";
        final String EXPECTED_CUSTOM_SEPERATOR_PREFIX = "//";

        Assertions.assertEquals(
                EXPECTED_DEFAULT_SEPERATOR_REGEX,
                CalculatorConfig.DEFAULT_SEPERATOR_REGEX);
        Assertions.assertEquals(
                EXPECTED_CUSTOM_SEPERATOR_PREFIX,
                CalculatorConfig.CUSTOM_SEPERATOR_PREFIX);
    }
}
