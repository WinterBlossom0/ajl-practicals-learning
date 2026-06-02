import java.security.SecureRandom;
import java.util.Base64;

/**
 * Q26: Generate Secure Random Numbers using SecureRandom Class
 * 
 * Explanation:
 * Java's `java.security.SecureRandom` class provides a cryptographically strong 
 * random number generator (RNG). Unlike the standard `java.util.Random`, which 
 * is pseudorandom and predictable, SecureRandom produces non-deterministic, 
 * secure random numbers suitable for generating keys, session IDs, and salts.
 */
public class Q26_SecureRandomDemo {
    public static void main(String[] args) {
        System.out.println("=== Q26: SecureRandom Demo ===");

        // Step 1: Instantiate SecureRandom
        SecureRandom secureRandom = new SecureRandom();

        // Step 2: Generate random integer values
        int randomInt = secureRandom.nextInt();
        System.out.println("Secure Random Integer: " + randomInt);

        // Generate bounded random integer (e.g., between 0 and 99)
        int boundedInt = secureRandom.nextInt(100);
        System.out.println("Secure Random Integer (0 to 99): " + boundedInt);

        // Step 3: Generate secure random bytes (e.g., for salt)
        byte[] randomBytes = new byte[16]; // 16 bytes = 128 bits
        secureRandom.nextBytes(randomBytes);
        System.out.println("Secure Random Bytes (Base64 encoded): " + 
                           Base64.getEncoder().encodeToString(randomBytes));

        // Step 4: Display the RNG algorithm provider used under the hood
        System.out.println("RNG Algorithm: " + secureRandom.getAlgorithm());
        System.out.println("RNG Provider: " + secureRandom.getProvider().getName());
    }
}
