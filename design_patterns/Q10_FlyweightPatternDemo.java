import java.util.HashMap;
import java.util.Map;

public class Q10_FlyweightPatternDemo {
    public static void main(String[] args) {
        Pen redPen1 = PenFactory.getPen("RED");
        Pen redPen2 = PenFactory.getPen("RED");
        System.out.println(redPen1 == redPen2); // true (the same instance is reused)
    }
}

interface Pen {
    void draw();
}

class ThickPen implements Pen {
    private final String color;
    public ThickPen(String color) { this.color = color; }
    public void draw() { System.out.println("Thick pen drawn in " + color); }
}

class PenFactory {
    private static final Map<String, Pen> map = new HashMap<>();
    public static Pen getPen(String color) {
        return map.computeIfAbsent(color, ThickPen::new);
    }
}
