/**
 * Q9: Facade Design Pattern (Structural Pattern)
 * 
 * Explanation:
 * The Facade Pattern provides a unified and simplified interface to a complex system 
 * of classes or subsystem. It defines a higher-level interface that makes the subsystem 
 * easier to use.
 * 
 * Real-world analogy: A customer support representative acting as a facade for a 
 * customer. Instead of the customer contacting the warehouse, shipping, and billing 
 * departments separately, the rep handles everything.
 */

// Main class to demonstrate Facade Pattern (must be first for direct Java runner)
public class Q9_FacadePatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q9: Facade Design Pattern Demo ===");

        // Client interacts only with the Facade class
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
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
        System.out.println("Circle::draw()");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}

// Facade class to simplify calls to subsystem classes
class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}
