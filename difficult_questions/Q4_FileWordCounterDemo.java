package difficult_questions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Q4: File Word Frequency Counter using Streams API
 * 
 * Objectives:
 * 1. Count frequency of each word (case-insensitive, ignore punctuation)
 * 2. Print top 10 most frequent words with their counts
 * 3. Find all words that appear exactly once
 * 4. Count how many unique words are longer than 6 characters
 * 
 * Uses Files.lines() to read the file as a stream.
 */
public class Q4_FileWordCounterDemo {
    private static final String FILE_NAME = "temp_words_viva.txt";

    public static void main(String[] args) {
        System.out.println("=== Q4: File Word Frequency Counter (Streams API) ===");

        // Step 1: Create a test file of 100+ words to ensure it runs self-contained
        createTestFile();

        // Step 2: Use Files.lines() to process the text file
        try (Stream<String> lines = Files.lines(Paths.get(FILE_NAME))) {
            
            // Map to store frequency: case-insensitive, punctuation ignored
            Map<String, Long> wordFrequencies = lines
                .flatMap(line -> Arrays.stream(line.split("\\W+"))) // Split by non-word characters
                .map(String::toLowerCase)                             // Case-insensitive
                .filter(word -> !word.isEmpty())                     // Ignore empty tokens
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            // 1. Total unique words
            System.out.println("Total Unique Words Found: " + wordFrequencies.size());

            // 2. Print the top 10 most frequent words with their counts
            System.out.println("\n--- Top 10 Most Frequent Words ---");
            wordFrequencies.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .forEach(entry -> System.out.println("   " + entry.getKey() + ": " + entry.getValue()));

            // 3. Find all words that appear exactly once
            List<String> wordsAppearingOnce = wordFrequencies.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
            System.out.println("\nWords that appear exactly once (showing first 15 for readability):");
            int limit = Math.min(wordsAppearingOnce.size(), 15);
            System.out.println("   " + wordsAppearingOnce.subList(0, limit) + " ... (Total: " + wordsAppearingOnce.size() + ")");

            // 4. Count how many unique words are longer than 6 characters
            long longWordsCount = wordFrequencies.keySet().stream()
                .filter(word -> word.length() > 6)
                .count();
            System.out.println("\nNumber of unique words longer than 6 characters: " + longWordsCount);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } finally {
            // Clean up the created test file
            try {
                Files.deleteIfExists(Paths.get(FILE_NAME));
            } catch (IOException ignored) {}
        }
    }

    // Helper method to create a dummy paragraph of at least 100 words
    private static void createTestFile() {
        String content = "Java is a popular, class-based, object-oriented programming language. " +
                "Java is designed to have as few implementation dependencies as possible. " +
                "It is a general-purpose programming language intended to let programmers write once, run anywhere. " +
                "This means compiled Java code can run on all platforms that support Java without the need for recompilation. " +
                "Java applications are typically compiled to bytecode that can run on any Java Virtual Machine, regardless of the underlying computer architecture. " +
                "As of 2026, Java is one of the most widely used programming languages in software development, particularly for client-server web applications. " +
                "The language derives much of its syntax from C and C++, but it has fewer low-level facilities. " +
                "Streams API in Java provides a powerful tool to process data collections declaratively. " +
                "This paragraph contains more than one hundred words to verify the frequency counter program successfully.";
        try {
            Files.write(Paths.get(FILE_NAME), content.getBytes());
        } catch (IOException e) {
            System.err.println("Could not create test file: " + e.getMessage());
        }
    }
}
