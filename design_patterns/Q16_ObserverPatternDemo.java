import java.util.ArrayList;
import java.util.List;

/**
 * Q16: Observer Design Pattern (Behavioral Pattern)
 * 
 * Explanation:
 * The Observer Pattern defines a one-to-many dependency between objects.
 * When the state of one object (Subject) changes, all its dependents (Observers) 
 * are notified and updated automatically.
 * 
 * Real-world analogy: Subscribing to a newsletter. When a new edition is published 
 * (state changes), all subscribers (observers) receive it.
 */

// Main class to demonstrate Observer Pattern (must be first for direct Java runner)
public class Q16_ObserverPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q16: Observer Design Pattern Demo ===");

        Subject subject = new Subject();

        new BinaryObserver(subject);
        new OctalObserver(subject);
        new HexaObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        
        System.out.println("\nSecond state change: 10");
        subject.setState(10);
    }
}

// Subject class that observers watch
class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// Abstract Observer class
abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

// Concrete Observer classes
class BinaryObserver extends Observer {
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
    }
}

class OctalObserver extends Observer {
    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}

class HexaObserver extends Observer {
    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase());
    }
}
