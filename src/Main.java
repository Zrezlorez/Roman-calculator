import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(calc(scan.nextLine()));
    }

    public static String calc(String input) {
        input = input.replaceAll("\\s+", "");
        String[] signs = {"\\+", "\\-", "\\*", "\\/"};
        for (var operation : signs){
            if (input.contains(operation.substring(1))){
                String[] numbers = input.split(operation);
                if (numbers.length > 2)
                    throw new RuntimeException();
                return checkNumbers(numbers[0], numbers[1], operation.substring(1));
            }
        }
        throw new RuntimeException();
    }

    public static String checkNumbers(String firstNum, String secondNum, String sign) {
        boolean isRoman = firstNum.matches("[IXV]+") && secondNum.matches("[IXV]+");
        int result = calculate(
                isRoman ? Convert.toArabic(firstNum) : tryParseInt(firstNum),
                isRoman ? Convert.toArabic(secondNum) : tryParseInt(secondNum),
                sign);
        if (isRoman && result<1)
            throw new RuntimeException();
        return isRoman ? Convert.toRoman(result) : String.valueOf(result);
    }
    public static int calculate(int x, int y, String operation){
        return switch (operation) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "*" -> x * y;
            case "/" -> x / y;
            default -> 0;
        };
    }
    public static int tryParseInt(String value) {
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