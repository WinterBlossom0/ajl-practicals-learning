public class Q30_AssertionDemo {
    public static void main(String[] args) {
        int age = 15;

        assert age >= 18 : "Age must be 18 or above";

        System.out.println("Allowed to vote");
    }
}
