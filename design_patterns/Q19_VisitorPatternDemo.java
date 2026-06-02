public class Q19_VisitorPatternDemo {
    public static void main(String[] args) {
        Element item = new Book();
        item.accept(new DisplayVisitor()); // Visited Book
    }
}

interface Element {
    void accept(Visitor v);
}

class Book implements Element {
    public void accept(Visitor v) { v.visit(this); }
}

interface Visitor {
    void visit(Book b);
}

class DisplayVisitor implements Visitor {
    public void visit(Book b) { System.out.println("Visited Book"); }
}
