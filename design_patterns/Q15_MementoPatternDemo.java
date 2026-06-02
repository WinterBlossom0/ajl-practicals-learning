import java.util.ArrayList;
import java.util.List;

/**
 * Q15: Memento Design Pattern (Behavioral Pattern)
 * 
 * Explanation:
 * The Memento Pattern is used to capture and restore an object's internal state 
 * without violating encapsulation. It enables undo/redo capabilities.
 * It uses three actors:
 * 1. Originator - The object whose state is being saved/restored.
 * 2. Memento - A container object holding a snapshot of the Originator's state.
 * 3. CareTaker - Keeps track of saved states (list of Mementos).
 * 
 * Real-world analogy: Game checkpoints where you can save progress 
 * and restore it when you lose.
 */

// Main class to demonstrate Memento Pattern (must be first for direct Java runner)
public class Q15_MementoPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q15: Memento Design Pattern Demo ===");

        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        // Alter states and save checkpoints
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());

        // Restore to checkpoints
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());

        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }
}

// Memento class that holds the saved state
class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

// Originator class that creates and restores from Memento
class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}

// CareTaker class that manages saved Mementos
class CareTaker {
    private List<Memento> mementoList = new ArrayList<>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}
