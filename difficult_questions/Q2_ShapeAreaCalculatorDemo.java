package difficult_questions;

import java.util.List;

interface Shape {
    double area();
}

class Circle implements Shape {
    private double r;
    public Circle(double r) { this.r = r; }
    public double area() { return Math.PI * r * r; }
}

class Rectangle implements Shape {
    private double w, h;
    public Rectangle(double w, double h) { this.w = w; this.h = h; }
    public double area() { return w * h; }
}

class Triangle implements Shape {
    private double b, h;
    public Triangle(double b, double h) { this.b = b; this.h = h; }
    public double area() { return 0.5 * b * h; }
}

class AreaCalculator {
    public double calculateTotalArea(List<Shape> shapes) {
        double total = 0;
        for (Shape s : shapes) total += s.area(); // Runtime Polymorphism
        return total;
    }
}

// Pentagon added later (OCP - AreaCalculator remains unmodified)
class Pentagon implements Shape {
    private double s;
    public Pentagon(double s) { this.s = s; }
    public double area() { return 1.72 * s * s; } // Simplified constant area formula
}

public class Q2_ShapeAreaCalculatorDemo {
    public static void main(String[] args) {
        List<Shape> shapes = List.of(
            new Circle(5), 
            new Rectangle(4, 5), 
            new Triangle(3, 4), 
            new Pentagon(4)
        );
        System.out.printf("Total Area of shapes: %.2f\n", new AreaCalculator().calculateTotalArea(shapes));
    }
}
