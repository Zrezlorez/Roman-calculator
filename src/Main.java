import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        System.out.println(calc(input.replaceAll("\\s+", "")));
    }

    public static String calc(String input) throws Exception {
        String[] signs = {"\\+", "\\-", "\\*", "\\/"};
        for(var operation : signs){
            if(input.contains(operation.substring(1))){
                String[] numbers = input.split(operation);
                if(numbers.length > 2)
                    throw new Exception();
                return checkNumbers(numbers[0], numbers[1], operation.substring(1));
            }
        }
        throw new Exception();
    }

    public static String checkNumbers(String firstNum, String secondNum, String sign) throws Exception {
        boolean isRoman = firstNum.matches("[IXV]+") && secondNum.matches("[IXV]+");
        int result = calculate(
                isRoman ? Convert.toArabic(firstNum) : tryParseInt(firstNum),
                isRoman ? Convert.toArabic(secondNum) : tryParseInt(secondNum),
                sign);

        if(isRoman && result<1)
            throw new Exception();

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
    static int tryParseInt(String value) throws Exception {
        try {
            int result = Integer.parseInt(value);
            if(result > 10)
                throw new Exception();
            return result;
        } catch (Exception e) {
            throw new Exception();
        }
    }
}