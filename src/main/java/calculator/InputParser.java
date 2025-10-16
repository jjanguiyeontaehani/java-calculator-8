package calculator;

public class InputParser {
    public void validateInput(String inputString) {
        if (inputString == null || inputString.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력은 비어있을 수 없습니다.");
        }
    }
}
