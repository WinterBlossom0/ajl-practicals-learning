public class Q19_VisitorPatternDemo {
    public static void main(String[] args) {
        Element item = new Book();
        // Pass concrete visitor class to element structure (double dispatch)
        item.accept(new DisplayVisitor()); // Visited Book
    }
}

// 1. Element interface representing structure components
interface Element {
    void accept(Visitor v);
}

// 2. Concrete Element
class Book implements Element {
    public void accept(Visitor v) { v.visit(this); }
}

// 3. Visitor interface defining operations for each element type
interface Visitor {
    void visit(Book b);
}

// 4. Concrete Visitor implementing the operations
class DisplayVisitor implements Visitor {
    public void visit(Book b) { System.out.println("Visited Book"); }
}
