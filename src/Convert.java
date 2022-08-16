import java.util.Map;
import java.util.TreeMap;

public class Convert {

    private static final TreeMap<Integer, String> ROMAN = new TreeMap<>() {{
        put(0, "0");
        put(1, "I");
        put(4, "IV");
        put(5, "V");
        put(9, "IX");
        put(10, "X");
        put(40, "XL");
        put(50, "L");
        put(90, "XC");
        put(100, "C");
    }};
    public static String toRoman(final int number) {
        Map.Entry<Integer, String> romanEntry = ROMAN.floorEntry(number);
        if (number == romanEntry.getKey())
            return romanEntry.getValue();
        return romanEntry.getValue()+toRoman(number-romanEntry.getKey());
    }

    public static int toArabic(String roman) {
        int result = 0;
        for (var entry : ROMAN.descendingMap().entrySet()) {
            while(roman.indexOf(entry.getValue()) == 0) {
                result += entry.getKey();
                roman = roman.substring(entry.getValue().length());
            }
        }
        return result;
    }
}