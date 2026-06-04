public class Q10_FlyweightPatternDemo {
    public static void main(String[] args) {

        // Both variables get same Red pen object
        Pen p1 = PenFactory.getPen();
        Pen p2 = PenFactory.getPen();

        p1.write();
        p2.write();

        // true means same object is reused
        System.out.println(p1 == p2);
    }
}

// Flyweight class: object to be reused
class Pen {
    String color = "Red";

    void write() {
        System.out.println(color + " pen");
    }
}

// Flyweight Factory: gives same shared object
class PenFactory {
    static Pen redPen = new Pen(); // created only once

    static Pen getPen() {
        return redPen; // same object reused
    }
}
