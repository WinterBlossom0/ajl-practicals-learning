public class Q2_AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        AbstractFactory factory = FactoryProducer.getFactory(true);
        factory.getShape().draw();
    }
}

interface Shape {
    void draw();
}

class NormalShape implements Shape {
    public void draw() { System.out.println("Normal Shape"); }
}

class RoundedShape implements Shape {
    public void draw() { System.out.println("Rounded Shape"); }
}

abstract class AbstractFactory {
    abstract Shape getShape();
}

class NormalFactory extends AbstractFactory {
    Shape getShape() { return new NormalShape(); }
}

class RoundedFactory extends AbstractFactory {
    Shape getShape() { return new RoundedShape(); }
}

class FactoryProducer {
    public static AbstractFactory getFactory(boolean rounded) {
        return rounded ? new RoundedFactory() : new NormalFactory();
    }
}
