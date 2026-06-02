import java.util.HashMap;
import java.util.Map;

/**
 * Q3: Prototype Design Pattern (Creational Pattern)
 * 
 * Explanation:
 * Prototype Pattern is used when the creation of an object is costly or complex,
 * and we want to create new objects by cloning an existing "prototype" instance.
 * It uses Java's Cloneable interface to achieve this.
 * 
 * Real-world analogy: Photocopying a document and editing the copy, rather than 
 * writing the document again from scratch.
 */

// Main class to demonstrate the Prototype Pattern (must be first for direct Java runner)
public class Q3_PrototypePatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q3: Prototype Design Pattern Demo ===");

        // Load prototype instances into cache
        ShapeCache.loadCache();

        // Retrieve and clone shape 1 (Circle)
        Shape clonedShape1 = ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape1.getType());
        clonedShape1.draw();

        // Retrieve and clone shape 2 (Square)
        Shape clonedShape2 = ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());
        clonedShape2.draw();

        // Retrieve and clone shape 3 (Rectangle)
        Shape clonedShape3 = ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
        clonedShape3.draw();
    }
}

// Abstract class implementing Cloneable
abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    abstract void draw();

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

// Concrete classes extending Shape
class Rectangle extends Shape {
    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Square extends Shape {
    public Square() {
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

class Circle extends Shape {
    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

// Shape Cache to store and retrieve prototype instances
class ShapeCache {
    private static Map<String, Shape> shapeMap = new HashMap<>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMap.put(rectangle.getId(), rectangle);
    }
}
