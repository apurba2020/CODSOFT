import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class WordCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter 'text' to input text manually or 'file' to provide a file: ");
        String inputType = scanner.nextLine().toLowerCase();

        String text = "";
        if (inputType.equals("text")) {
            System.out.print("Enter the text: ");
            text = scanner.nextLine();
        } else if (inputType.equals("file")) {
            System.out.print("Enter the file path: ");
            String filePath = scanner.nextLine();
            text = readFile(filePath);
            if (text == null) {
                System.out.println("File not found or error reading the file.");
                scanner.close();
                return;
            }
        } else {
            System.out.println("Invalid input type.");
            scanner.close();
            return;
        }

        String[] words = text.split("[\\s\\p{Punct}]+");
        int wordCount = words.length;

        System.out.println("Total word count: " + wordCount);
 

        // Count frequency of each word using a HashMap
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        System.out.println("Word Frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }

    private static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                content.append(fileScanner.nextLine()).append("\n");
            }
            fileScanner.close();
            return content.toString();
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
