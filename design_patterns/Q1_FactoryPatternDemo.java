public class Q1_FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        factory.getShape("CIRCLE").draw();
        factory.getShape("SQUARE").draw();
    }
}

interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() { System.out.println("Circle drawn."); }
}

class Square implements Shape {
    public void draw() { System.out.println("Square drawn."); }
}

class ShapeFactory {
    public Shape getShape(String type) {
        if (type.equalsIgnoreCase("CIRCLE")) return new Circle();
        if (type.equalsIgnoreCase("SQUARE")) return new Square();
        return null;
    }
}
