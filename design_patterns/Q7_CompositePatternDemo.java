import java.util.ArrayList;
import java.util.List;

public class Q7_CompositePatternDemo {
    public static void main(String[] args) {
        Directory root = new Directory();
        root.add(new File("notes.txt"));
        
        // Execute operation uniformly on composite tree structure
        root.show();
    }
}

// 1. Component Interface (Common type for elements and collections)
interface Node {
    void show();
}

// 2. Leaf Node (Individual object containing no children)
class File implements Node {
    private final String name;
    public File(String name) { this.name = name; }
    public void show() { System.out.println("File: " + name); }
}

// 3. Composite Node (Contains child components)
class Directory implements Node {
    private final List<Node> children = new ArrayList<>();
    public void add(Node node) { children.add(node); }
    
    // Delegating operation recursively to all children
    public void show() {
        System.out.println("Directory contains:");
        for (Node child : children) child.show();
    }
}
