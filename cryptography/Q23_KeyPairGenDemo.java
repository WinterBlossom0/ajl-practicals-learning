import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * Q23: Generate Public and Private Key Pair using RSA Algorithm
 * 
 * Explanation:
 * RSA (Rivest-Shamir-Adleman) is an asymmetric cryptographic algorithm. 
 * It uses two mathematically linked keys: a Public Key (used to encrypt or verify signature) 
 * and a Private Key (used to decrypt or sign).
 */
public class Q23_KeyPairGenDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Q23: RSA Key Pair Generation Demo ===");

        // Step 1: Initialize KeyPairGenerator with RSA algorithm
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // Standard key size of 2048 bits

        // Step 2: Generate the KeyPair
        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Step 3: Print Key Details
        System.out.println("Public Key Format : " + publicKey.getFormat());  // Usually X.509
        System.out.println("Private Key Format: " + privateKey.getFormat()); // Usually PKCS#8

        // Convert keys to Base64 String format for display
        String pubKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        System.out.println("\n--- Public Key (Base64) ---");
        System.out.println(pubKeyBase64);

        System.out.println("\n--- Private Key (Base64) ---");
        System.out.println(privKeyBase64);
    }
}
