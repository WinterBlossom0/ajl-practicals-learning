/**
 * Q27: Default Methods in Java Interfaces
 * 
 * Explanation:
 * Prior to Java 8, interfaces could only contain abstract methods (no implementation).
 * Java 8 introduced "default methods" using the `default` keyword. This allows 
 * interfaces to have method implementations without breaking existing implementing classes.
 * Classes implementing the interface automatically inherit the default method, but 
 * can choose to override it if needed.
 * 
 * Real-life analogy: A smartphone operating system update. All existing phone models 
 * get a new default feature (like dark mode) without requiring manufacturers to build 
 * their own versions from scratch, though they can customize it if they want.
 */

// Main class to demonstrate Default Methods (must be first for direct Java runner)
public class Q27_DefaultMethodDemo {
    public static void main(String[] args) {
        System.out.println("=== Q27: Default Interface Methods Demo ===");

        System.out.println("\n--- Testing Car (Standard implementation) ---");
        Vehicle myCar = new Car();
        myCar.clean();
        myCar.startEngine(); // Inherited default method
        myCar.honk();        // Inherited default method

        System.out.println("\n--- Testing SportsCar (Overridden implementation) ---");
        Vehicle mySportsCar = new SportsCar();
        mySportsCar.clean();
        mySportsCar.startEngine(); // Overridden default method
        mySportsCar.honk();        // Inherited default method
    }
}

// Interface with abstract and default methods
interface Vehicle {
    void clean();

    default void startEngine() {
        System.out.println("Vehicle: Engine started. Vroom!");
    }

    default void honk() {
        System.out.println("Vehicle: Beep beep!");
    }
}

// Implement class that uses default methods as-is
class Car implements Vehicle {
    @Override
    public void clean() {
        System.out.println("Car: Washing the car body and vacuuming the seats.");
    }
}

// Implement class that overrides a default method to customize behavior
class SportsCar implements Vehicle {
    @Override
    public void clean() {
        System.out.println("SportsCar: Detailing the luxury exterior.");
    }

    @Override
    public void startEngine() {
        System.out.println("SportsCar: ROARING engine started! VROOOOOM!");
    }
}
