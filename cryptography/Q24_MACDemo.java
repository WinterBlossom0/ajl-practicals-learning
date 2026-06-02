import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Q24: Generate and Verify Message Authentication Code (MAC)
 * 
 * Explanation:
 * A Message Authentication Code (MAC) is used to verify the integrity and authenticity 
 * of a message using a shared secret key. Unlike plain hashing, MAC requires a secret key.
 * This demo shows deriving a key from a pre-shared secret string and generating/verifying 
 * a MAC using HmacSHA512.
 */
public class Q24_MACDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Q24: Message Authentication Code (MAC) Demo ===");

        String message = "Transfer $500 to Alice.";
        String preSharedKeyStr = "SuperSecretSharedPassword123";

        System.out.println("Message: " + message);
        System.out.println("Shared Secret Password: " + preSharedKeyStr);

        // Step 1: Create a SecretKeySpec using the shared password bytes
        SecretKeySpec secretKey = new SecretKeySpec(preSharedKeyStr.getBytes(), "HmacSHA512");

        // Step 2: Initialize Mac instance with HmacSHA512
        Mac mac = Mac.getInstance("HmacSHA512");
        mac.init(secretKey);

        // Step 3: Generate the MAC bytes and encode to Base64
        byte[] macBytes = mac.doFinal(message.getBytes());
        String macResult = Base64.getEncoder().encodeToString(macBytes);
        System.out.println("Generated MAC (HmacSHA512): " + macResult);

        // Step 4: Verification process
        boolean isVerified = verifyMAC(message, macResult, secretKey);
        System.out.println("Verification Result (Valid Message): " + isVerified);

        // Verify with altered message
        boolean isAlteredVerified = verifyMAC("Transfer $5000 to Alice.", macResult, secretKey);
        System.out.println("Verification Result (Altered Message): " + isAlteredVerified);
    }

    // Recipient method to verify MAC
    private static boolean verifyMAC(String message, String receivedMacStr, SecretKeySpec secretKey) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA512");
        mac.init(secretKey);
        
        byte[] computedMacBytes = mac.doFinal(message.getBytes());
        byte[] receivedMacBytes = Base64.getDecoder().decode(receivedMacStr);

        // Safe constant-time byte array comparison
        return MessageDigest.isEqual(computedMacBytes, receivedMacBytes);
    }
}
