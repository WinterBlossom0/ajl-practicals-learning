public class Q02_AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        // Retrieve concrete factory for rounded shapes
        AbstractFactory factory = FactoryProducer.getFactory(true);
        factory.getShape().draw(); // Outputs: Rounded Shape
    }
}

// Product interface
interface Shape {
    void draw();
}

// Concrete products
class NormalShape implements Shape {
    public void draw() { System.out.println("Normal Shape"); }
}

class RoundedShape implements Shape {
    public void draw() { System.out.println("Rounded Shape"); }
}

// Abstract Factory: Defines a method template to get shapes
abstract class AbstractFactory {
    abstract Shape getShape();
}

// Concrete Factory 1: Creates normal shapes
class NormalFactory extends AbstractFactory {
    Shape getShape() { return new NormalShape(); }
}

// Concrete Factory 2: Creates rounded shapes
class RoundedFactory extends AbstractFactory {
    Shape getShape() { return new RoundedShape(); }
}

// Factory Producer: Creates the concrete factory based on parameter (Factory of Factories)
class FactoryProducer {
    public static AbstractFactory getFactory(boolean rounded) {
        return rounded ? new RoundedFactory() : new NormalFactory();
    }
}
