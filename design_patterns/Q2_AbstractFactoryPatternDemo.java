/**
 * Q2: Abstract Factory Design Pattern (Creational Pattern)
 * 
 * Explanation:
 * Abstract Factory acts as a \"factory of factories\". It provides an interface for 
 * creating families of related or dependent objects without specifying their concrete classes.
 * 
 * Real-world analogy: A furniture factory that creates themed sets of furniture 
 * (e.g., Modern Chair + Modern Sofa vs. Victorian Chair + Victorian Sofa).
 */

// Main class to demonstrate Abstract Factory Pattern (must be first for direct Java runner)
public class Q2_AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q2: Abstract Factory Design Pattern Demo ===");

        // Get normal shape factory
        AbstractFactory shapeFactory = FactoryProducer.getFactory(false);
        
        // Get normal Rectangle
        Shape shape1 = shapeFactory.getShape("RECTANGLE");
        shape1.draw();
        
        // Get normal Square
        Shape shape2 = shapeFactory.getShape("SQUARE");
        shape2.draw();

        // Get rounded shape factory
        AbstractFactory roundedShapeFactory = FactoryProducer.getFactory(true);
        
        // Get rounded Rectangle
        Shape shape3 = roundedShapeFactory.getShape("RECTANGLE");
        shape3.draw();
        
        // Get rounded Square
        Shape shape4 = roundedShapeFactory.getShape("SQUARE");
        shape4.draw();
    }
}

// Common interface for Shapes
interface Shape {
    void draw();
}

// Concrete classes for Normal Shapes
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

// Concrete classes for Rounded Shapes
class RoundedRectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside RoundedRectangle::draw() method.");
    }
}

class RoundedSquare implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside RoundedSquare::draw() method.");
    }
}

// Abstract Class for Factories
abstract class AbstractFactory {
    abstract Shape getShape(String shapeType);
}

// Factory classes extending AbstractFactory
class ShapeFactory extends AbstractFactory {
    @Override
    Shape getShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}

class RoundedShapeFactory extends AbstractFactory {
    @Override
    Shape getShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new RoundedRectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new RoundedSquare();
        }
        return null;
    }
}

// Factory Generator/Producer class to get factories
class FactoryProducer {
    public static AbstractFactory getFactory(boolean rounded) {
        if (rounded) {
            return new RoundedShapeFactory();
        } else {
            return new ShapeFactory();
        }
    }
}
