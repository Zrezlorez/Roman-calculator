public class Convert {
    private static final int[] intervals = {0, 1, 4, 5, 9, 10, 40, 50, 90, 100};
    private static final String[] romanNumbers = {"0", "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};

    private static int findFloor(final int number, final int firstIndex, final int lastIndex) {
        if(firstIndex == lastIndex)
            return firstIndex;
        if(intervals[firstIndex] == number)
            return firstIndex;
        if(intervals[lastIndex] == number)
            return lastIndex;
        final int median = (lastIndex + firstIndex) / 2;
        if(median == firstIndex)
            return firstIndex;
        if(number == intervals[median])
            return median;
        if(number > intervals[median])
            return findFloor(number, median, lastIndex);
        else
            return findFloor(number, firstIndex, median);

    }

    public static String toRoman(final int number) {
        int floorIndex = findFloor(number, 0, intervals.length-1);
        if (number == intervals[floorIndex])
            return romanNumbers[floorIndex];
        return romanNumbers[floorIndex]+toRoman(number-intervals[floorIndex]);
    }

    public static int toArabic(String roman) {
        int result = 0;
        for (int i = intervals.length-1; i >= 0; i-- ) {
            while (roman.indexOf(romanNumbers[i]) == 0) {
                result += intervals[i];
                roman = roman.substring(romanNumbers[i].length());
            }
        }
        return result;
    }
}