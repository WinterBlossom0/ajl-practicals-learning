import java.util.HashMap;
import java.util.Map;

public class Q10_FlyweightPatternDemo {
    public static void main(String[] args) {
        // Request identical objects from the factory
        Pen redPen1 = PenFactory.getPen("RED");
        Pen redPen2 = PenFactory.getPen("RED");
        
        // Output true because the exact same object is reused
        System.out.println(redPen1 == redPen2); 
    }
}

// Flyweight Interface
interface Pen {
    void draw();
}

// Concrete Flyweight (Contains shared state)
class ThickPen implements Pen {
    private final String color; // Intrinsic (shared) state
    public ThickPen(String color) { this.color = color; }
    public void draw() { System.out.println("Thick pen drawn in " + color); }
}

// Flyweight Factory (manages caching and reuse of objects)
class PenFactory {
    private static final Map<String, Pen> map = new HashMap<>();
    
    public static Pen getPen(String color) {
        // Returns the cached object if present, else creates and registers it
        return map.computeIfAbsent(color, ThickPen::new);
    }
}
