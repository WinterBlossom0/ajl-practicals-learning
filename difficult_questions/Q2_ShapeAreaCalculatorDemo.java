package difficult_questions;

import java.util.List;

/**
 * Q2: Shape Area Calculator demonstrating Open-Closed Principle (OCP)
 * 
 * - Open for Extension: We can add new shapes (like Pentagon) by implementing the Shape interface.
 * - Closed for Modification: The AreaCalculator class remains completely unchanged.
 */

public class Q2_ShapeAreaCalculatorDemo {
    public static void main(String[] args) {
        System.out.println("=== Q2: Shape Area Calculator (SOLID) ===");

        // List of mixed shapes including Circle, Rectangle, Triangle, and Pentagon
        List<Shape> shapes = List.of(
            new Circle(5),       // Area: ~78.54
            new Rectangle(4, 5), // Area: 20
            new Triangle(3, 4),  // Area: 6
            new Pentagon(4)      // Area: ~27.53 (added later)
        );

        AreaCalculator calculator = new AreaCalculator();
        double totalArea = calculator.calculateTotalArea(shapes);

        System.out.printf("Total area of the mixed shapes list: %.2f\n", totalArea);
    }
}

// Shape Abstraction
interface Shape {
    double area();
}

// Concrete Shapes
class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

class Triangle implements Shape {
    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return 0.5 * base * height;
    }
}

// AreaCalculator is closed for modification
class AreaCalculator {
    public double calculateTotalArea(List<Shape> shapes) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.area(); // Runtime Polymorphism
        }
        return totalArea;
    }
}

// Adding Pentagon after — AreaCalculator MUST NOT CHANGE
class Pentagon implements Shape {
    private final double side;

    public Pentagon(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return 0.25 * Math.sqrt(5 * (5 + 2 * Math.sqrt(5))) * side * side;
    }
}
