import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Q22: Generate and Verify HMAC using Java Cryptography Architecture (JCA)
 * 
 * Explanation:
 * HMAC (Hash-based Message Authentication Code) is a specific type of message 
 * authentication code involving a cryptographic hash function and a secret key.
 * It provides both data integrity and authenticity checks.
 */
public class Q22_HMACDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Q22: HMAC Generation and Verification Demo ===");

        String message = "Confidential Transaction Data";
        System.out.println("Message: " + message);

        // Step 1: Generate a secret key for HMAC-SHA256
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGen.generateKey();

        // Step 2: Generate HMAC
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] hmacBytes = mac.doFinal(message.getBytes());
        String generatedHmac = Base64.getEncoder().encodeToString(hmacBytes);
        System.out.println("Generated HMAC (Base64): " + generatedHmac);

        // Step 3: Verify HMAC
        mac.init(secretKey);
        byte[] computedBytes = mac.doFinal(message.getBytes());
        
        boolean isValid = MessageDigest.isEqual(hmacBytes, computedBytes);
        System.out.println("HMAC Verification Success: " + isValid);

        // Verification fail check (with altered message)
        String alteredMessage = "Confidential Transaction Data.";
        byte[] alteredBytes = mac.doFinal(alteredMessage.getBytes());
        boolean isAlteredValid = MessageDigest.isEqual(hmacBytes, alteredBytes);
        System.out.println("Altered Message Verification Success: " + isAlteredValid);
    }
}
