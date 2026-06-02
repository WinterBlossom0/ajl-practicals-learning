import java.util.ArrayList;
import java.util.List;

public class Q7_CompositePatternDemo {
    public static void main(String[] args) {
        Directory root = new Directory();
        root.add(new File("notes.txt"));
        root.show();
    }
}

interface Node {
    void show();
}

class File implements Node {
    private final String name;
    public File(String name) { this.name = name; }
    public void show() { System.out.println("File: " + name); }
}

class Directory implements Node {
    private final List<Node> children = new ArrayList<>();
    public void add(Node node) { children.add(node); }
    public void show() {
        System.out.println("Directory contains:");
        for (Node child : children) child.show();
    }
}
