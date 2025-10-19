package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InputParser {
    public List<Float> parseString(String inputString) {
        String customSeparator = parseCustomSeparator(inputString);

        return parseNumbersUsingSeparator(customSeparator, inputString);
    }

    private List<Float> parseNumbersUsingSeparator(String customSeparator, String inputString) {
        String numberString = inputString;
        String separatorRegex = CalculatorConfig.DEFAULT_SEPARATOR_REGEX;
        if (customSeparator != null) {
            int suffixIndex = inputString.indexOf(CalculatorConfig.CUSTOM_SEPARATOR_SUFFIX) +
                    CalculatorConfig.CUSTOM_SEPARATOR_SUFFIX.length();

            numberString = inputString.substring(suffixIndex);

            separatorRegex += "|" + Pattern.quote(customSeparator);
        }

        String[] parsedStringArray = numberString.split(separatorRegex, -1);

        return parseNumberList(parsedStringArray);
    }

    private List<Float> parseNumberList(String[] parsedNumberArray) {
        List<Float> numbers = new ArrayList<>();

        for (String numberToken : parsedNumberArray) {
            Float numberValue = parseNumberToken(numberToken.trim());

            numbers.add(numberValue);
        }
        return numbers;
    }

    private Float parseNumberToken(String numberToken) {

        if (numberToken.isEmpty()) {
            return (float) 0;
        }

        float numberValue;
        try {
            numberValue = Float.parseFloat(numberToken);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "[ERROR] 유효하지 않은 숫자 형식이나 구분자 외의 문자가 감지되었습니다.", e);
        }

        if (numberValue <= 0) {
            throw new IllegalArgumentException(
                    "[ERROR] 음수 및 0은 입력할 수 없습니다. 값: " + numberValue);
        }

        return numberValue;
    }

    private boolean isCustomSeparatorAttempted(String inputString) {
        boolean hasSuffix = inputString.contains(CalculatorConfig.CUSTOM_SEPARATOR_SUFFIX);
        boolean hasPrefix = inputString.startsWith(CalculatorConfig.CUSTOM_SEPARATOR_PREFIX);

        return hasPrefix || hasSuffix;
    }

    public String parseCustomSeparator(String inputString) {
        if (isCustomSeparatorAttempted(inputString)) {
            validateSeparatorFormat(inputString);

            int prefixLen = CalculatorConfig.CUSTOM_SEPARATOR_PREFIX.length();
            int suffixIndex = inputString.indexOf(CalculatorConfig.CUSTOM_SEPARATOR_SUFFIX);

            String customSeparator = inputString.substring(prefixLen, suffixIndex);

            validateCustomSeparatorContent(customSeparator);

            return customSeparator;
        }
        return null;
    }

    private void validateSeparatorOccurrence(String inputString) {
        if (inputString.split(Pattern.quote(CalculatorConfig.CUSTOM_SEPARATOR_PREFIX)).length > 2) {
            throw new IllegalArgumentException(
                    "[ERROR] 커스텀 구분자 사용 시 " + CalculatorConfig.CUSTOM_SEPARATOR_PREFIX + " 은 1번만 사용되어야 합니다.");
        }

        if (inputString.split(Pattern.quote(CalculatorConfig.CUSTOM_SEPARATOR_SUFFIX)).length > 2) {
            throw new IllegalArgumentException(
                    "[ERROR] 커스텀 구분자 사용 시 " + CalculatorConfig.CUSTOM_SEPARATOR_SUFFIX + " 은 1번만 사용되어야 합니다.");
        }
    }

    private void validateSeparatorFormat(String inputString) {
        if (!inputString.startsWith(CalculatorConfig.CUSTOM_SEPARATOR_PREFIX)) {
            throw new IllegalArgumentException(
                    "[ERROR] 커스텀 구분자 사용 시 " + CalculatorConfig.CUSTOM_SEPARATOR_PREFIX +
                            "가 문자열 가장 앞에 입력되어야 합니다.");
        }

        if (!inputString.contains(CalculatorConfig.CUSTOM_SEPARATOR_SUFFIX)) {
            throw new IllegalArgumentException(
                    "[ERROR] 커스텀 구분자 사용 시 " + CalculatorConfig.CUSTOM_SEPARATOR_SUFFIX +
                            "가 구분자 뒤에 입력되어야 합니다.");
        }

        validateSeparatorOccurrence(inputString);
    }

    private void validateCustomSeparatorContent(String customSeparator) {
        if (customSeparator.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 커스텀 구분자는 비어있을 수 없습니다.");
        }

        if (customSeparator.length() > 1) {
            throw new IllegalArgumentException("[ERROR] 커스텀 구분자는 1개만 사용 가능합니다.");
        }
    }
}
