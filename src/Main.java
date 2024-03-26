import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        findMostFrequentNr(" The years were 21155122222111!!!!");
//        try {
//            BufferedReader rd = new BufferedReader(new FileReader("src/inimesed.txt"));
//            String ln = rd.readLine();
//            while (ln != null) {
//                System.out.println(ln);
//                System.out.println("The most frquent nr: ");
//                findMostFrequentNr(ln);
//                ln = rd.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static char findMostFrequentNr(String line) {
        char result = '0';
        System.out.println("Line: " + line);
        line = line.replaceAll("[^a-zA-Z0-9\\s]", ".");
        System.out.println("Line: " + line);
        // make list for numbers in string
        List<Character> numbers = new ArrayList<>();
        // add numbers from string into list
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit((line.charAt(i)))) {
                numbers.add(line.charAt(i));
            }
        }
        System.out.println("Numbers: " + Arrays.toString(numbers.toArray()));

        // count numbers in list, make hashmap for counts?
        Map<Character, Integer> numberCounts = new HashMap<>();
        for (char n : numbers) {
            if (numberCounts.containsKey(n)) {
                numberCounts.put(n, numberCounts.get(n) +1);
            } else {
                numberCounts.put(n, 1);
            }
        }


        return result;
    }
}