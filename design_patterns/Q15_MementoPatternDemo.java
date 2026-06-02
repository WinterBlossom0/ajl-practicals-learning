public class Q15_MementoPatternDemo {
    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.setState("State1");
        Memento memento = originator.saveState();
        originator.setState("State2");
        originator.restoreState(memento);
        System.out.println("Restored state: " + originator.getState()); // State1
    }
}

class Memento {
    private final String state;
    public Memento(String s) { state = s; }
    public String getState() { return state; }
}

class Originator {
    private String state;
    public void setState(String s) { state = s; }
    public String getState() { return state; }
    public Memento saveState() { return new Memento(state); }
    public void restoreState(Memento m) { state = m.getState(); }
}
