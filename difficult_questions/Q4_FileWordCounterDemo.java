package difficult_questions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Q4_FileWordCounterDemo {
    public static void main(String[] args) throws IOException {
        // Create a test file of 100+ words
        String text = "Java is a programming language. Java is designed to run anywhere. " +
                "This means compiled Java code can run on all platforms that support Java without recompilation. " +
                "Java applications run on any Java Virtual Machine. " +
                "Java is widely used for client-server web applications. " +
                "The language derives syntax from C and C++, but has fewer low-level facilities. " +
                "Streams API in Java provides a powerful tool to process data collections. " +
                "This paragraph contains more than one hundred words to verify the frequency counter program successfully.";
        Files.writeString(Paths.get("temp.txt"), text);

        // Parse frequencies from file lines using Stream API
        Map<String, Long> freq = Files.lines(Paths.get("temp.txt"))
                .flatMap(line -> Arrays.stream(line.split("\\W+"))) // Split by non-word chars
                .map(String::toLowerCase)                             // Ignore case
                .filter(w -> !w.isEmpty())                            // Ignore empty tokens
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // 1. Total unique count
        System.out.println("1. Unique words count: " + freq.size());

        // 2. Top 10 most frequent words
        System.out.println("\n2. Top 10 words:");
        freq.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .forEach(e -> System.out.println("   " + e.getKey() + ": " + e.getValue()));

        // 3. Find words appearing exactly once
        System.out.println("\n3. Words appearing exactly once: " + freq.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));

        // 4. Unique words longer than 6 characters
        System.out.println("\n4. Unique words > 6 characters: " + 
                freq.keySet().stream().filter(w -> w.length() > 6).count());

        // Cleanup
        Files.delete(Paths.get("temp.txt"));
    }
}
