import java.util.ArrayList;
import java.util.List;

public class Q16_ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new HexObserver(subject);
        subject.setState(15); // Outputs: F
    }
}

class Subject {
    private final List<Observer> observers = new ArrayList<>();
    private int state;
    public int getState() { return state; }
    public void setState(int val) { state = val; notifyAllObservers(); }
    public void attach(Observer o) { observers.add(o); }
    public void notifyAllObservers() { for (Observer o : observers) o.update(); }
}

abstract class Observer {
    protected Subject subject;
    abstract void update();
}

class HexObserver extends Observer {
    public HexObserver(Subject s) { subject = s; subject.attach(this); }
    void update() { System.out.println("Hex: " + Integer.toHexString(subject.getState()).toUpperCase()); }
}
