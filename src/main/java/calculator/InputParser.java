package calculator;

import java.util.regex.Pattern;

public class InputParser {
    public void validateInput(String inputString) {
        if (inputString == null || inputString.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력은 비어있을 수 없습니다.");
        }
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
