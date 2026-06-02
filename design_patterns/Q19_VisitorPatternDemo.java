/**
 * Q19: Visitor Design Pattern (Behavioral Pattern)
 * 
 * Explanation:
 * The Visitor Pattern separates an algorithm or operation from the object structure 
 * on which it operates. It allows you to add new operations to existing object structures 
 * without modifying their classes. It utilizes "double dispatch" via an accept() method.
 * 
 * Real-world analogy: A building inspector (Visitor) visiting a house. 
 * The house elements (electric wiring, plumbing) accept the inspector to perform checkups.
 */

// Main class to demonstrate Visitor Pattern (must be first for direct Java runner)
public class Q19_VisitorPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q19: Visitor Design Pattern Demo ===");

        ComputerPart computer = new Computer();
        ComputerPartVisitor visitor = new ComputerPartDisplayVisitor();

        computer.accept(visitor);
    }
}

// Element interface representing computer parts
interface ComputerPart {
    void accept(ComputerPartVisitor visitor);
}

// Concrete classes implementing ComputerPart
class Keyboard implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

class Monitor implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

class Mouse implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

class Computer implements ComputerPart {
    private ComputerPart[] parts;

    public Computer() {
        parts = new ComputerPart[] { new Mouse(), new Keyboard(), new Monitor() };
    }

    @Override
    public void accept(ComputerPartVisitor visitor) {
        for (int i = 0; i < parts.length; i++) {
            parts[i].accept(visitor);
        }
        visitor.visit(this);
    }
}

// Visitor interface
interface ComputerPartVisitor {
    void visit(Keyboard keyboard);
    void visit(Monitor monitor);
    void visit(Mouse mouse);
    void visit(Computer computer);
}

// Concrete visitor class
class ComputerPartDisplayVisitor implements ComputerPartVisitor {
    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard.");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor.");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse.");
    }

    @Override
    public void visit(Computer computer) {
        System.out.println("Displaying Computer.");
    }
}
