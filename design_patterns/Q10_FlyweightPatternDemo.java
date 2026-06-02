import java.util.HashMap;
import java.util.Map;

/**
 * Q10: Flyweight Design Pattern (Structural Pattern)
 * 
 * Explanation:
 * The Flyweight Pattern is used to reduce the number of objects created, 
 * decreasing memory usage and improving performance. It shares identical 
 * parts of objects (intrinsic state) instead of creating duplicate objects.
 * 
 * Real-world analogy: A text editor where characters (A, B, C) are shared objects 
 * rather than creating a unique object for every single letter on the page.
 */

// Main class to demonstrate Flyweight Pattern (must be first for direct Java runner)
public class Q10_FlyweightPatternDemo {
    private static final String colors[] = { "Red", "Green", "Blue", "White", "Black" };

    public static void main(String[] args) {
        System.out.println("=== Q10: Flyweight Design Pattern Demo ===");

        // We will generate 20 circles using only 5 shared circle objects
        for (int i = 0; i < 20; ++i) {
            Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }

    private static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    private static int getRandomX() {
        return (int) (Math.random() * 100);
    }

    private static int getRandomY() {
        return (int) (Math.random() * 100);
    }
}

// Interface
interface Shape {
    void draw();
}

// Concrete class implementing the interface
class Circle implements Shape {
    private String color; // Intrinsic state (shared)
    private int x;        // Extrinsic state (supplied by client)
    private int y;        // Extrinsic state (supplied by client)
    private int radius;   // Extrinsic state (supplied by client)

    public Circle(String color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color + ", x : " + x + ", y :" + y + ", radius :" + radius + "]");
    }
}

// Flyweight Factory to manage and reuse shared objects
class ShapeFactory {
    private static final Map<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}
