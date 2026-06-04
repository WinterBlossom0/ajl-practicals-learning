import java.security.*;

public class Q21_HashingDemo {
    public static void main(String[] args) throws Exception {

        // Input text
        String text = "hello";

        // Create SHA-256 hasher
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Generate hash
        byte[] hash = md.digest(text.getBytes());

        // Print hash object
        System.out.println(hash);
    }
}
