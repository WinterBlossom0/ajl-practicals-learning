/**
 * Q8: Decorator Design Pattern (Structural Pattern)
 * 
 * Explanation:
 * The Decorator Pattern dynamically adds new behavior or responsibilities 
 * to an existing object without modifying its structure. It does this by creating 
 * a wrapper class (decorator) that implements the same interface as the wrapped object.
 * 
 * Real-world analogy: Adding toppings to a pizza. The base pizza remains the same, 
 * but toppings decorate it with additional features and pricing.
 */

// Main class to demonstrate Decorator Pattern (must be first for direct Java runner)
public class Q8_DecoratorPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q8: Decorator Design Pattern Demo ===");

        Shape circle = new Circle();
        System.out.println("Normal Circle:");
        circle.draw();

        System.out.println("\nRed Border Circle:");
        Shape redCircle = new RedShapeDecorator(new Circle());
        redCircle.draw();

        System.out.println("\nRed Border Rectangle:");
        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        redRectangle.draw();
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
        System.out.println("Shape: Circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}

// Abstract decorator class implementing Shape
abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape; // Reference to wrapped object

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}

// Concrete decorator class extending abstract decorator
class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();       // Draw original shape
        setRedBorder(decoratedShape); // Apply decoration
    }

    private void setRedBorder(Shape decoratedShape) {
        System.out.println("Border Color: Red");
    }
}
