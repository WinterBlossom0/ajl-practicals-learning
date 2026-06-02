/**
 * Q11: Proxy Design Pattern (Structural Pattern)
 * 
 * Explanation:
 * The Proxy Pattern provides a placeholder or surrogate object to control 
 * access to the original object. It is often used for lazy loading (virtual proxy) 
 * or access control (protection proxy).
 * 
 * Real-world analogy: A debit/credit card acts as a proxy for physical cash 
 * in a bank account.
 */

// Main class to demonstrate Proxy Pattern (must be first for direct Java runner)
public class Q11_ProxyPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q11: Proxy Design Pattern Demo ===");

        Image image = new ProxyImage("high_resolution_photo.jpg");

        // Image will be loaded from disk and displayed (first time)
        System.out.println("--- First Call to display() ---");
        image.display();
        System.out.println();

        // Image will not be loaded from disk again, just displayed
        System.out.println("--- Second Call to display() ---");
        image.display();
    }
}

// Common interface
interface Image {
    void display();
}

// Concrete class implementing the interface (Real Object)
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading image from disk: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}

// Proxy class implementing the interface (Proxy Object)
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
