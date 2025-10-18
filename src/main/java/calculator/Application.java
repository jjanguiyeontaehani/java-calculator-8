package calculator;

public class Application {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        try {

            CalculatorController controller = new CalculatorController(
                    view,
                    new InputParser(),
                    new CalculatorModel()
            );

            controller.run();
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "예상치 못한 오류가 발생했습니다: " + e.getMessage());
        } finally {
            view.closeConsole();
        }

    }
}
