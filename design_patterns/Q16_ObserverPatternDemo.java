import java.util.ArrayList;
import java.util.List;

public class Q16_ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new HexObserver(subject); // Register observer
        
        // Changing state triggers automatic updates to all observers
        subject.setState(15); // Outputs: Hex: F
    }
}

// 1. Subject (The observed object maintaining list of observers)
class Subject {
    private final List<Observer> observers = new ArrayList<>();
    private int state;
    
    public int getState() { return state; }
    public void setState(int val) { state = val; notifyAllObservers(); }
    
    public void attach(Observer o) { observers.add(o); }
    public void notifyAllObservers() { for (Observer o : observers) o.update(); }
}

// 2. Observer Interface
abstract class Observer {
    protected Subject subject;
    abstract void update();
}

// 3. Concrete Observer
class HexObserver extends Observer {
    public HexObserver(Subject s) { subject = s; subject.attach(this); }
    void update() { System.out.println("Hex: " + Integer.toHexString(subject.getState()).toUpperCase()); }
}
