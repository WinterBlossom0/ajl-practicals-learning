import java.util.*;

public class Q07_CompositePatternDemo {
    public static void main(String[] args) {

        // Create files
        File f1 = new File("a.txt");
        File f2 = new File("b.txt");

        // Create folder
        Folder folder = new Folder("MyFolder");

        // Add files into folder
        folder.add(f1);
        folder.add(f2);

        // Same method works for folder and files
        folder.show();
    }
}

// Common interface for both File and Folder
interface Item {
    void show();
}

// Leaf class: single object
class File implements Item {
    String name;

    File(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("File: " + name);
    }
}

// Composite class: group of objects
class Folder implements Item {
    String name;
    ArrayList<Item> items = new ArrayList<>();

    Folder(String name) {
        this.name = name;
    }

    // Add file or folder inside this folder
    void add(Item item) {
        items.add(item);
    }

    public void show() {
        System.out.println("Folder: " + name);

        // Show all items inside folder
        for (Item item : items) {
            item.show();
        }
    }
}
