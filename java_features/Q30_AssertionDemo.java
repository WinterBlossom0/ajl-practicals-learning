/**
 * Q30: Assertions in Java
 * 
 * Explanation:
 * Assertions are statement tests that a developer inserts to declare that an 
 * assumption *must* be true at that point in the code.
 * Syntax: `assert expression;` or `assert expression : errorMessage;`
 * 
 * IMPORTANT: By default, assertions are DISABLED at runtime.
 * To enable assertions, run the java program with the `-ea` (or `-enableassertions`) JVM flag:
 * command: `java -ea Q30_AssertionDemo`
 */
public class Q30_AssertionDemo {
    public static void main(String[] args) {
        System.out.println("=== Q30: Assertions Demo ===");

        // Step 1: Detect if assertions are enabled
        boolean assertionsEnabled = false;
        try {
            assert false; // This will trigger AssertionError if assertions are enabled
        } catch (AssertionError e) {
            assertionsEnabled = true;
        }

        System.out.println("Are JVM assertions enabled (-ea flag)? " + assertionsEnabled);

        int examScore = 95;
        int negativeScore = -5;

        // Step 2: Demonstrating assertion validation
        System.out.println("\nValidating positive exam score: " + examScore);
        validateScore(examScore);

        System.out.println("\nValidating invalid exam score: " + negativeScore);
        try {
            validateScore(negativeScore);
        } catch (AssertionError e) {
            System.out.println("Assertion Caught: " + e.getMessage());
        }

        if (!assertionsEnabled) {
            System.out.println("\n[Note] Assertions were NOT checked because JVM was run without the -ea flag.");
            System.out.println("To see assertions fail, run: java -ea Q30_AssertionDemo");
        }
    }

    private static void validateScore(int score) {
        // Assert that score is within a valid range (0 to 100)
        assert (score >= 0 && score <= 100) : "Score must be between 0 and 100. Received: " + score;
        
        System.out.println("Score validation passed.");
    }
}
