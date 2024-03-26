import java.util.*;

public class HW3_ex1 {
    public static void main(String[] args) {
        String ln = " The years were 21155122222111!!!!";
        System.out.println("Intial String: " + ln);
        char mostFrequentNr = findMostFrequentNr(ln);
        System.out.println("Most frequent nr: " + mostFrequentNr);
        String modified = removeMostFrequentNumber(ln, mostFrequentNr);
        System.out.println("Final String: " + modified);
//        try {
//            BufferedReader rd = new BufferedReader(new FileReader("src/inimesed.txt"));
//            String ln = rd.readLine();
//            while (ln != null) {
//                System.out.println("Intial String: " + ln);
//                char number = findMostFrequentNr(ln);
//                System.out.println("The most frquent nr: " + number);
//                System.out.println("Final String: " + removeMostFrequentNumber(ln, number));
//                ln = rd.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static char findMostFrequentNr(String line) {
        /*
        Make new list for numbers. Goes through the String and if an element in String is digit,
        then it adds it to the list.
         */
        List<Character> numbers = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit((line.charAt(i)))) {
                numbers.add(line.charAt(i));
            }
        }

        /*
        Counts all the different numbers in lists. And puts them as key, value pairs into the HashMap.
        If there is already key for that element, then it adds to the value +1
        If there is not key for that element, then it puts key and 1 into the HashMap
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
        Goes through the entries in HashMap, and finds key with biggest value. Intially maxCount is 0, and it goes one by one
        through the HashMap. If next value (currentCount) is bigger, then it replaces char result with specific key and
        it's value becomes new maxCount. If maxCount == currentCount --> checks if also the key is bigger than the current
        result.
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

    /*
    Would be better name modifyString, as it removes most frequent number AND replaces symbols with "."
    Using regex to replace symbols
     */
    public static String removeMostFrequentNumber(String line, char number) {
        line = line.replaceAll("[^a-zA-Z0-9\\s]", ".");
        return line.replace(String.valueOf(number), "");
    }
}