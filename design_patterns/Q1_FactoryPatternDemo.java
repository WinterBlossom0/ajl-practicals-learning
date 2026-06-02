/**
 * Q1: Factory Design Pattern (Creational Pattern)
 * 
 * Explanation:
 * The Factory Pattern defines an interface or abstract class for creating an object,
 * but lets subclasses decide which class to instantiate. It delegates object creation
 * to a specialized Factory class, hiding the creation logic from the client.
 * 
 * Real-world analogy: A toy factory that manufactures cars, dolls, or blocks 
 * based on the order type, without the customer knowing how the toys are assembled.
 */

// Main class to demonstrate the Factory Pattern (must be first for direct Java runner)
public class Q1_FactoryPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q1: Factory Design Pattern Demo ===");
        
        ShapeFactory shapeFactory = new ShapeFactory();

        // Get an object of Circle and call its draw method
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        // Get an object of Rectangle and call its draw method
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();

        // Get an object of Square and call its draw method
        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();
    }
}

// Common interface
interface Shape {
    void draw();
}

// Concrete classes implementing the interface
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle: Drawing a Circle.");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle: Drawing a Rectangle.");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square: Drawing a Square.");
    }
}

// Factory class to generate objects of concrete class based on given information
class ShapeFactory {
    // Factory method to get object of type Shape
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
