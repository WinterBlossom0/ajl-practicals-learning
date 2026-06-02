public class Q1_FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        
        // Request objects from the factory without exposing creation logic
        factory.getShape("CIRCLE").draw();
        factory.getShape("SQUARE").draw();
    }
}

// 1. Common Product Interface
interface Shape {
    void draw();
}

// 2. Concrete Product implementations
class Circle implements Shape {
    public void draw() { System.out.println("Circle drawn."); }
}

class Square implements Shape {
    public void draw() { System.out.println("Square drawn."); }
}

// 3. The Factory class containing the creation logic
class ShapeFactory {
    // Factory method: decides which subclass to instantiate based on input
    public Shape getShape(String type) {
        if (type.equalsIgnoreCase("CIRCLE")) return new Circle();
        if (type.equalsIgnoreCase("SQUARE")) return new Square();
        return null;
    }
}
