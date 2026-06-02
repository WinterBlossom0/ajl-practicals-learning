/**
 * Q6: Bridge Design Pattern (Structural Pattern)
 * 
 * Explanation:
 * The Bridge Pattern decouples an abstraction from its implementation 
 * so that both can vary independently. It uses composition instead of inheritance
 * to separate abstraction (conceptual part) from implementor (technical implementation part).
 * 
 * Real-world analogy: A switch (abstraction) can operate a fan or a light (implementors).
 * The switch doesn't need to know the internal wiring details of the light or fan.
 */

// Main class to demonstrate Bridge Pattern (must be first for direct Java runner)
public class Q6_BridgePatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q6: Bridge Design Pattern Demo ===");

        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(200, 200, 20, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}

// Implementor Interface
interface DrawAPI {
    void drawCircle(int radius, int x, int y);
}

// Concrete Implementor Classes
class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle [color: red, radius: " + radius + ", x: " + x + ", y: " + y + "]");
    }
}

class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle [color: green, radius: " + radius + ", x: " + x + ", y: " + y + "]");
    }
}

// Abstraction class containing a reference to Implementor
abstract class Shape {
    protected DrawAPI drawAPI; // The Bridge

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}

// Refined Abstraction class extending Abstraction
class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}
