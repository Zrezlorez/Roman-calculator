import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(calc(scan.nextLine()));
    }

    public static String calc(String input) {
        String replacedInput = input.replaceAll("\\s+", "");
        String[] signs = {"\\+", "\\-", "\\*", "\\/"};
        for (var operation : signs) {
            if (replacedInput.contains(operation.substring(1))){
                String[] numbers = replacedInput.split(operation);
                if (numbers.length > 2)
                    throw new RuntimeException();
                return checkAndCalcNumbers(numbers[0], numbers[1], operation.substring(1));
            }
        }
        throw new RuntimeException();
    }

    private static String checkAndCalcNumbers(String firstNum, String secondNum, String sign) {
        boolean isRoman = firstNum.matches("[IXV]+") && secondNum.matches("[IXV]+");

        int first = isRoman ? Convert.toArabic(firstNum) : tryParseInt(firstNum);
        int second = isRoman ? Convert.toArabic(secondNum) : tryParseInt(secondNum);

        int result = calculateByOperation(checkLimit(first), checkLimit(second), sign);
        if (isRoman && result < 1)
            throw new RuntimeException();
        return isRoman ? Convert.toRoman(result) : String.valueOf(result);
    }

    private static int calculateByOperation(int x, int y, String operation) {
        return switch (operation) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "*" -> x * y;
            case "/" -> x / y;
            default -> 0;
        };
    }

    private static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException();
        }
    }

    private static int checkLimit(int value) {
        if (value > 10 || value < 1) {
            throw new RuntimeException();
        }
        return value;
    }
}