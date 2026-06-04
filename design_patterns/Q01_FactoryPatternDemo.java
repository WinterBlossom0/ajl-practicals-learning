public class Q01_FactoryPatternDemo {
    public static void main(String[] args) {

        // Create factory object
        ShapeFactory factory = new ShapeFactory();

        // Ask factory to create a Circle
        Shape shape = factory.createShape("circle");

        // Call method of created object
        shape.draw();
    }
}

// Common interface for all shapes
interface Shape {
    void draw();
}

// Circle class implements Shape
class Circle implements Shape {
    public void draw() {
        System.out.println("Circle is drawn");
    }
}

// Square class implements Shape
class Square implements Shape {
    public void draw() {
        System.out.println("Square is drawn");
    }
}

// Factory class creates Shape objects
class ShapeFactory {

    // This method decides which object to create
    Shape createShape(String type) {

        if (type.equals("circle")) {
            return new Circle();   // create Circle object
        } else {
            return new Square();   // create Square object
        }
    }
}
