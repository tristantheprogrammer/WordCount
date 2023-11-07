import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try {
            createInputFileIfNotExists(inputFile); 
            if (new File(inputFile).length() > 0) {
                Map<String, Integer> wordCount = countWords(inputFile);
                writeWordCountToFile(wordCount, outputFile);
                System.out.println("Word count results have been written to " + outputFile);
            } else {
                System.out.println("Input file is empty. No output file created.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createInputFileIfNotExists(String inputFile) throws IOException {
        File file = new File(inputFile);
        if (!file.exists()) {
            System.out.println("The input file doesn't exist. Creating a new one for editing.");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            }
        }
    }

    private static Map<String, Integer> countWords(String inputFile) throws IOException {
        Map<String, Integer> wordCount = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim().toLowerCase();
                wordCount.put(line, wordCount.getOrDefault(line, 0) + 1);
            }
        }

        return wordCount;
    }

    private static void writeWordCountToFile(Map<String, Integer> wordCount, String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        }
    }
}
