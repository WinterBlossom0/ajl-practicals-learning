public class Q01_FactoryPatternDemo {
    public static void main(String[] args) {

        // Choose factory
        ShapeFactory factory = new CircleFactory();

        // Factory creates object
        Shape shape = factory.createShape();

        // Use object
        shape.draw();
    }
}

// Product interface
interface Shape {
    void draw();
}

// Concrete product 1
class Circle implements Shape {
    public void draw() {
        System.out.println("Circle is drawn");
    }
}

// Concrete product 2
class Square implements Shape {
    public void draw() {
        System.out.println("Square is drawn");
    }
}

// Creator class with factory method
abstract class ShapeFactory {
    // Factory method
    abstract Shape createShape();
}

// Factory for Circle
class CircleFactory extends ShapeFactory {
    Shape createShape() {
        return new Circle();
    }
}

// Factory for Square
class SquareFactory extends ShapeFactory {
    Shape createShape() {
        return new Square();
    }
}
