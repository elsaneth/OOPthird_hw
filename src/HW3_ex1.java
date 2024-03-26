import java.util.*;

public class HW3_ex1 {
    /**
     * Main method to demonstrate string manipulation operations.
     * Calls methods to find the most frequent character in a string and modify the string accordingly.
     * If the result for the method findMostFrequentNr is null, then other methods are not executed.
     *
     * @param args Command-line arguments (not used in this implementation).
     * @return void
     */
    public static void main(String[] args) {
        String ln = "trrkrt kpsstVVrqqKKt !!!!!!!!!!!!";
        System.out.println("Intial String: " + ln);
        char mostFrequentNr = findMostFrequentNr(ln);
        if (mostFrequentNr != '\0') {
            System.out.println("Most frequent nr: " + mostFrequentNr);
            String modified = removeMostFrequentNumber(ln, mostFrequentNr);
            System.out.println("Final String: " + modified);
        }
    }

    /**
     * Finds the most frequent digit character in the given string and returns it.
     * If the string contains no digit characters, it returns the null character '\0'.
     *
     * @param line The input string to search for the most frequent digit character.
     * @return The most frequent digit character in the string, or '\0' if no digits are found.
     */
    public static char findMostFrequentNr(String line) {
        /*
        Creates a new list to store digit characters. Iterates through the string, and if a character
        in the string is a digit, adds it to the list.
         */
        List<Character> numbers = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit((line.charAt(i)))) {
                numbers.add(line.charAt(i));
            }
        }
        if (numbers.isEmpty()) {
            System.out.println("No numbers in this string!");
            return '\0';
        }

        /*
        Counts occurrences of each digit character and stores them as key-value pairs in a HashMap.
        If a character already exists as a key, its corresponding value is incremented by 1. Otherwise,
        a new key-value pair is added with the character and a count of 1.
         */
        Map<Character, Integer> numberCounts = new HashMap<>();
        for (char n : numbers) {
            if (numberCounts.containsKey(n)) {
                numberCounts.put(n, numberCounts.get(n) +1);
            } else {
                numberCounts.put(n, 1);
            }
        }

        /*
        Iterates through the entries in the HashMap to find the digit character with the highest count.
        Initializes maxCount as 0 and iterates through the HashMap. If the count of the current digit
        character is greater than maxCount, or if it is equal to maxCount but the character is greater
        than the current result, updates the result to the current character.
         */
        int maxCount = 0;
        int result = 0;
        for (Map.Entry<Character, Integer> entry : numberCounts.entrySet()) {
            int currentCount = entry.getValue();
            if (currentCount > maxCount || (currentCount == maxCount && entry.getKey() > result)) {
                maxCount = currentCount;
                result = entry.getKey();
            }
        }
        return (char) result;
    }

    /**
     * Modifies the input string by removing the most frequent digit character and replacing all non-alphanumeric
     * characters with '.'.
     *
     * @param line The input string to be modified.
     * @param number The most frequent digit character to be removed from the string.
     * @return The modified string with the most frequent digit character removed and symbols replaced with '.'.
     */
    public static String removeMostFrequentNumber(String line, char number) {
        // Replaces all non-alphanumeric characters with '.'
        line = line.replaceAll("[^a-zA-Z0-9\\s]", ".");
        // Removes occurrences of the most frequent digit character from the string
        return line.replace(String.valueOf(number), "");
    }
}