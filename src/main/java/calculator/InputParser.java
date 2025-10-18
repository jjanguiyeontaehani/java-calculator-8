package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InputParser {
    public List<Float> parseString(String inputString) {
        String customSeperator = parseCustomSeperator(inputString);

        return parseNumbersUsingSeperator(customSeperator, inputString);
    }

    private List<Float> parseNumbersUsingSeperator(String customSeperator, String inputString) {
        String numberString = inputString;
        String seperatorRegex = CalculatorConfig.DEFAULT_SEPERATOR_REGEX;
        if (customSeperator != null) {
            int suffixIndex = inputString.indexOf(CalculatorConfig.CUSTOM_SEPERATOR_SUFFIX) + 2;

            numberString = inputString.substring(suffixIndex);

            seperatorRegex += "|" + Pattern.quote(customSeperator);
        }

        String[] parsedStringArray = numberString.split(seperatorRegex, -1);

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

    private boolean isCustomSeperatorAttempted(String inputString) {
        boolean hasSuffix = inputString.contains(CalculatorConfig.CUSTOM_SEPERATOR_SUFFIX);
        boolean hasPrefix = inputString.startsWith(CalculatorConfig.CUSTOM_SEPERATOR_PREFIX);

        return hasPrefix || hasSuffix;
    }

    public String parseCustomSeperator(String inputString) {
        if (isCustomSeperatorAttempted(inputString)) {
            validateSeperatorFormat(inputString);

            int prefixLen = CalculatorConfig.CUSTOM_SEPERATOR_PREFIX.length();
            int suffixIndex = inputString.indexOf(CalculatorConfig.CUSTOM_SEPERATOR_SUFFIX);

            String customSeperator = inputString.substring(prefixLen, suffixIndex);

            validateCustomSeperatorContent(customSeperator);

            return customSeperator;
        }
        return null;
    }

    private void validateSeperatorOccurrence(String inputString) {
        if (inputString.split(Pattern.quote(CalculatorConfig.CUSTOM_SEPERATOR_PREFIX)).length > 2) {
            throw new IllegalArgumentException("[ERROR] 커스텀 구분자 사용 시 '//' 은 1번만 사용되어야 합니다.");
        }

        if (inputString.split(Pattern.quote(CalculatorConfig.CUSTOM_SEPERATOR_SUFFIX)).length > 2) {
            throw new IllegalArgumentException("[ERROR] 커스텀 구분자 사용 시 '\\n' 은 1번만 사용되어야 합니다.");
        }
    }

    private void validateSeperatorFormat(String inputString) {
        if (!inputString.startsWith(CalculatorConfig.CUSTOM_SEPERATOR_PREFIX)) {
            throw new IllegalArgumentException(
                    "[ERROR] 커스텀 구분자 사용 시 '//'가 문자열 가장 앞에 입력되어야 합니다.");
        }

        if (!inputString.contains(CalculatorConfig.CUSTOM_SEPERATOR_SUFFIX)) {
            throw new IllegalArgumentException(
                    "[ERROR] 커스텀 구분자 사용 시 '\\n'가 구분자 뒤에 입력되어야 합니다.");
        }

        validateSeperatorOccurrence(inputString);
    }

    private void validateCustomSeperatorContent(String customSeperator) {
        if (customSeperator.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 커스텀 구분자는 비어있을 수 없습니다.");
        }

        if (customSeperator.length() > 1) {
            throw new IllegalArgumentException("[ERROR] 커스텀 구분자는 1개만 사용 가능합니다.");
        }
    }
}
