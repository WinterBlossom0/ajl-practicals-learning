public class Q03_PrototypePatternDemo {
    public static void main(String[] args) {
        Sheep s1 = new Sheep("Dolly");
        
        // Clone the sheep object using Prototype pattern
        Sheep s2 = s1.clone();
        
        System.out.println("Sheep 1: " + s1.name);
        System.out.println("Sheep 2: " + s2.name);
    }
}

// Prototype Class
class Sheep {
    String name;
    public Sheep(String name) { this.name = name; }
    
    // Simulates the cloning process
    public Sheep clone() {
        return new Sheep(this.name);
    }
}
