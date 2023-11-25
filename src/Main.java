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
        int result = calculateByOperation(
                isRoman ? Convert.toArabic(firstNum) : tryParseInt(firstNum),
                isRoman ? Convert.toArabic(secondNum) : tryParseInt(secondNum),
                sign);
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
            int result = Integer.parseInt(value);
            if (result > 10)
                throw new RuntimeException();
            return result;
        } catch (NumberFormatException e) {
            throw new RuntimeException();
        }
    }
}