import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

/**
 * Q20: Encryption and Decryption using Java Cryptography Architecture (JCA)
 * 
 * Explanation:
 * JCA provides a framework for access and implementation of cryptographic services.
 * Here we use AES (Advanced Encryption Standard), a symmetric key algorithm, 
 * to encrypt plain text into cipher text, and decrypt cipher text back to plain text.
 */
public class Q20_EncryptionDecryptionDemo {
    public static void main(String[] args) {
        try {
            System.out.println("=== Q20: Encryption and Decryption Demo (AES) ===");

            String originalText = "Hello! This is a secret message for the exam.";
            System.out.println("Original Text : " + originalText);

            // Step 1: Generate a secret key for AES
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // Specifying key size (128 bits)
            SecretKey secretKey = keyGen.generateKey();

            // Step 2: Initialize Cipher for ENCRYPTION
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(originalText.getBytes());

            // Convert encrypted bytes to Base64 String for printable format
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("Encrypted Text (Base64) : " + encryptedText);

            // Step 3: Initialize Cipher for DECRYPTION
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            
            String decryptedText = new String(decryptedBytes);
            System.out.println("Decrypted Text : " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
