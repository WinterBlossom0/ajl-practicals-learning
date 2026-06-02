import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

/**
 * Q25: Create and Verify a Digital Signature using RSA and SHA algorithms
 * 
 * Explanation:
 * A Digital Signature provides authentication, non-repudiation, and integrity.
 * 1. The sender hashes the message (SHA-256) and encrypts the hash with their Private Key 
 *    using RSA. This encrypted hash is the signature.
 * 2. The recipient decrypts the signature with the sender's Public Key to get the hash, 
 *    re-computes the message hash, and compares both. If they match, the signature is valid.
 */
public class Q25_DigitalSignatureDemo {
    public static void main(String[] args) {
        try {
            System.out.println("=== Q25: Digital Signature Demo (SHA256withRSA) ===");

            String data = "Official Documents and Agreements.";
            System.out.println("Data to sign: " + data);

            // Step 1: Generate RSA Key Pair
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Step 2: Sign the data using Private Key
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(data.getBytes());
            byte[] digitalSignature = signature.sign();

            String signatureStr = Base64.getEncoder().encodeToString(digitalSignature);
            System.out.println("Digital Signature (Base64): " + signatureStr);

            // Step 3: Verify the signature using Public Key
            signature.initVerify(publicKey);
            signature.update(data.getBytes());
            boolean isVerified = signature.verify(digitalSignature);
            System.out.println("Signature Verification: " + isVerified);

            // Step 4: Verify with modified data (Should fail)
            signature.initVerify(publicKey);
            signature.update("Official Documents and Agreements!".getBytes()); // Altered character
            boolean isAlteredVerified = signature.verify(digitalSignature);
            System.out.println("Altered Data Verification: " + isAlteredVerified);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
