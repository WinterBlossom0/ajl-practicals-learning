public class Q15_MementoPatternDemo {
    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.setState("State1");
        
        // Save state to Memento container
        Memento memento = originator.saveState();
        
        originator.setState("State2");
        
        // Restore state from Memento
        originator.restoreState(memento);
        System.out.println("Restored state: " + originator.getState()); // State1
    }
}

// 1. Memento: Container object that stores a snapshot of the state
class Memento {
    private final String state;
    public Memento(String s) { state = s; }
    public String getState() { return state; }
}

// 2. Originator: The object whose state needs to be saved and restored
class Originator {
    private String state;
    public void setState(String s) { state = s; }
    public String getState() { return state; }
    
    // Save current state into a new Memento
    public Memento saveState() { return new Memento(state); }
    
    // Restore state from a Memento
    public void restoreState(Memento m) { state = m.getState(); }
}
