public class Q03_PrototypePatternDemo {
    public static void main(String[] args) {
        Sheep s1 = new Sheep("Dolly");
        
        // Clone the sheep object rather than creating a new one via 'new' constructor
        Sheep s2 = (Sheep) s1.clone();
        
        System.out.println("Sheep 1: " + s1.name);
        System.out.println("Sheep 2: " + s2.name);
    }
}

// Concrete Prototype implementing Cloneable interface
class Sheep implements Cloneable {
    String name;
    public Sheep(String name) { this.name = name; }
    
    // Override clone method to allow copying instances
    public Object clone() {
        try {
            return super.clone(); // Performs shallow copy
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
