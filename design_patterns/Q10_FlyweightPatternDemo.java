import java.util.*;

public class Q10_FlyweightPatternDemo {
    public static void main(String[] args) {

        // Both use same red pen object
        Pen p1 = PenFactory.getPen("Red");
        Pen p2 = PenFactory.getPen("Red");

        p1.draw("Hello");
        p2.draw("World");

        // Proves object is reused
        System.out.println(p1 == p2);
    }
}

// Flyweight object
class Pen {
    String color; // shared data

    Pen(String color) {
        this.color = color;
    }

    void draw(String text) {
        System.out.println(color + " pen writes: " + text);
    }
}

// Factory stores and reuses Pen objects
class PenFactory {
    static HashMap<String, Pen> pens = new HashMap<>();

    static Pen getPen(String color) {

        // Create pen only if it does not already exist
        if (!pens.containsKey(color)) {
            pens.put(color, new Pen(color));
        }

        // Return existing pen
        return pens.get(color);
    }
}
