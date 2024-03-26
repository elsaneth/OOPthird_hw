import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HW3_ex2 {
    public static void main(String[] args) {
        String sisendfail = "src/inimesed.txt";
        File file = new File(sisendfail);
        if (file.exists()) {
            convert(sisendfail);
        } else {
            System.out.println("File does not exist: " + sisendfail);
        }
    }

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

        writeValidLinesToFile(validLines);
        writeNonValidLinesToFile(nonValidLines);
    }

    public static void writeValidLinesToFile(List<String> validLines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/valund1.txt"))) {
            for (String line : validLines) {
                String[] parts = line.split("\\|");

                writer.write("Perekonnanimi: " + parts[1] + "\n");
                writer.write("Eesnimi: " + parts[2] + "\n");
                writer.write("Isikukood: " + parts[0] + "\n");
                writer.write("Palk: " + parts[3] + "\n");
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeNonValidLinesToFile(List<String> nonValidLines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/valjund2.txt"))) {
            for (String line : nonValidLines) {
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeLine(String sisendfail, String otsitav) {

    }
}
