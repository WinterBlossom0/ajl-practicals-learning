import java.security.MessageDigest;

/**
 * Q21: Hashing Value using MD5 and SHA-256 Hashing Algorithms
 * 
 * Explanation:
 * A hashing algorithm is a one-way cryptographic function that maps input data 
 * of any size to a fixed-size hash value. It is deterministic, meaning the same 
 * input always produces the same hash. MD5 produces 128-bit hashes, while 
 * SHA-256 produces 256-bit hashes.
 */
public class Q21_HashingDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Q21: Hashing Demo (MD5 and SHA-256) ===");

        String input = "ExamStudyMaterial2026";
        System.out.println("Input String : " + input);

        // Compute MD5 Hash
        String md5Hash = getHash(input, "MD5");
        System.out.println("MD5 Hash (128-bit)    : " + md5Hash);

        // Compute SHA-256 Hash
        String sha256Hash = getHash(input, "SHA-256");
        System.out.println("SHA-256 Hash (256-bit): " + sha256Hash);
    }

    // Helper method to compute hash using MessageDigest
    private static String getHash(String input, String algorithm) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = digest.digest(input.getBytes());

        // Convert byte array to hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
