import java.io.IOException;

/**
 * Q31: Advanced Exception Handling in Java
 * 
 * Explanation:
 * Advanced Exception Handling in Java involves:
 * 1. Custom Exceptions - Creating application-specific exceptions by extending Exception.
 * 2. Try-with-Resources - Automatic closing of resources implementing AutoCloseable.
 * 3. Multi-Catch Block - Handling multiple unrelated exceptions in a single catch block.
 * 4. Exception Chaining - Throwing a high-level exception while linking the low-level cause.
 */

// Main class demonstrating advanced exception handling (must be first for direct Java runner)
public class Q31_AdvancedExceptionDemo {
    public static void main(String[] args) {
        System.out.println("=== Q31: Advanced Exception Handling Demo ===");

        try (PaymentTerminal terminal = new PaymentTerminal()) {
            
            System.out.println("--- Scenario 1: Normal payment ---");
            terminal.swipeCard(150.00);

            System.out.println("\n--- Scenario 2: Payment triggering exception ---");
            terminal.swipeCard(1500.00);

        } catch (IOException e) {
            PaymentException chainedException = new PaymentException("Payment failed through terminal.", e);
            System.out.println("Low-level exception wrapped into PaymentException.");
            handlePaymentFailure(chainedException);
        }
    }

    private static void handlePaymentFailure(PaymentException e) {
        System.out.println("\nCaught High-level Exception: " + e.getMessage());
        System.out.println("Root Cause (Chained Exception): " + e.getCause().getMessage());
        
        try {
            double value = Double.parseDouble("InvalidAmount");
        } catch (NumberFormatException | NullPointerException ex) {
            System.out.println("\nHandled via Multi-Catch block: Invalid amount parse error caught.");
        }
    }
}

// Custom Exception
class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }
    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Resource implementing AutoCloseable
class PaymentTerminal implements AutoCloseable {
    public void swipeCard(double amount) throws IOException {
        System.out.println("PaymentTerminal: Processing card payment of $" + amount);
        if (amount > 1000) {
            throw new IOException("Terminal hardware connectivity lost.");
        }
    }

    @Override
    public void close() {
        System.out.println("PaymentTerminal: Connection closed automatically.");
    }
}
