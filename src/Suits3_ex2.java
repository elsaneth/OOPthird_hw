import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Suits3_ex2 {
    /**
     * Main method to demonstrate file manipulation operations.
     * Reads data from the input file, processes it,
     * and performs line removal based on specific criteria.
     *
     * @param args Command-line arguments (not used in this implementation).
     * @return void
     */
    public static void main(String[] args) {
        String sisendfail = "src/inimesed.txt";
        File file = new File(sisendfail);
        if (file.exists()) {
            convert(sisendfail);
            removeLine(sisendfail, "00000000001|Meri|Piret|errr   //vale");
        } else {
            System.out.println("File does not exist: " + sisendfail);
        }
    }

    /**
     * Reads data from a given input file, processes each line,
     * and categorizes them into valid and non-valid lines based on a specific structure.
     * Valid lines contain four elements separated by '|' with the following format:
     *   - 11 digits (numeric)
     *   - A word (alphabetic characters)
     *   - Another word (alphabetic characters)
     *   - 4 digits (numeric)
     * Non-valid lines are those that do not adhere to the specified structure.
     *
     * @param sisendfail The path to the input file to be processed.
     * @return void
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static void convert(String sisendfail) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader rd = new BufferedReader(new FileReader(sisendfail));
            String ln = rd.readLine();
            while (ln != null) {
                lines.add(ln);
                ln = rd.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(lines);

        List<String> validLines = new ArrayList<>();
        List<String> nonValidLines = new ArrayList<>();
        for (String element : lines) {
            String[] parts = element.split("\\|");

            if (parts.length == 4) {
                if (parts[0].matches("\\d{11}") &&
                        parts[1].matches("\\w+") &&
                        parts[2].matches("\\w+") &&
                        parts[3].matches("\\d{4}")) {
                    validLines.add(element);
                } else {
                    nonValidLines.add(element);
                }
            }
        }
        System.out.println("Valid: " + validLines);
        System.out.println("Non valid: " + nonValidLines);

        writeModifiedStructure(validLines, "src/valund1.txt");
        writeSameStructure(nonValidLines, "src/valjund2.txt");
    }

    /**
     * Writes the valid lines to a file with a modified structure, separating each element into labeled fields.
     * Each valid line is formatted and written to the file with the following structure:
     *   - Perekonnanimi: [last name]
     *   - Eesnimi: [first name]
     *   - Isikukood: [identification number]
     *   - Palk: [salary]
     * Each field is separated by a newline character ('\n').
     *
     * @param validLines A list containing valid lines to be written to the file with modified structure.
     * @return void
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void writeModifiedStructure(List<String> validLines, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : validLines) {
                String[] parts = line.split("\\|");

                writer.write("Perekonnanimi: " + parts[1] + "\n");
                writer.write("Eesnimi: " + parts[2] + "\n");
                writer.write("Isikukood: " + parts[0] + "\n");
                writer.write("Palk: " + parts[3] + "\n");
                writer.write("\n");
            }
            System.out.println("Valid written in 'src/valund1.txt'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the lines to a specified file while maintaining their original structure. Used for non-valid numbers and
     * @method removeLine
     *
     * @param lines A list containing lines to be written to the file.
     * @param filename The name of the file where the lines will be written.
     * @return void
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void writeSameStructure(List<String> lines, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
            System.out.println("Non valid written in '" + filename + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads lines from a given input file, removes the line that matches the specified string, 
     * and writes the remaining lines back to the same file.
     *
     * @param inputFile The path to the input file from which lines will be removed.
     * @param target The string representing the line to be removed.
     * @return void
     * @throws IOException If an I/O error occurs while reading from or writing to the file.
     */
    public static void removeLine(String inputFile, String target) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader rd = new BufferedReader(new FileReader(inputFile));
            String ln = rd.readLine();
            System.out.println("Removing line: " + target);
            while (ln != null) {
                if (!ln.equals(target)) {
                    lines.add(ln);
                }
                ln = rd.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeSameStructure(lines, inputFile);
        System.out.println("Complete finding removable and removed if there was such line!");
    }
}
