public class Q3_PrototypePatternDemo {
    public static void main(String[] args) {
        Sheep s1 = new Sheep("Dolly");
        Sheep s2 = (Sheep) s1.clone();
        System.out.println("Sheep 1: " + s1.name);
        System.out.println("Sheep 2: " + s2.name);
    }
}

class Sheep implements Cloneable {
    String name;
    public Sheep(String name) { this.name = name; }
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
