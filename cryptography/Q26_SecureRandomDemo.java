import java.security.SecureRandom;

public class Q26_SecureRandomDemo {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();

        int number = random.nextInt(100); // 0 to 99

        System.out.println("Random number: " + number);
    }
}
