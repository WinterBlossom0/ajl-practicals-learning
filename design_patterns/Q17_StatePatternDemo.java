/**
 * Q17: State Design Pattern (Behavioral Pattern)
 * 
 * Explanation:
 * The State Pattern allows an object to alter its behavior when its internal state changes.
 * The object will appear to change its class. Behavior is delegated to state objects.
 * 
 * Real-world analogy: A traffic light. Its behavior (which light is shining) changes 
 * based on its internal state (Red, Yellow, Green).
 */

// Main class to demonstrate State Pattern (must be first for direct Java runner)
public class Q17_StatePatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q17: State Design Pattern Demo ===");

        Context context = new Context();

        // Perform action in StartState
        StartState startState = new StartState();
        startState.doAction(context);
        System.out.println("Current Context State: " + context.getState());

        System.out.println();

        // Perform action in StopState
        StopState stopState = new StopState();
        stopState.doAction(context);
        System.out.println("Current Context State: " + context.getState());
    }
}

// State Interface
interface State {
    void doAction(Context context);
    String toString();
}

// Concrete classes implementing State
class StartState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Start State";
    }
}

class StopState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Stop State";
    }
}

// Context class that carries the state reference
class Context {
    private State state;

    public Context() {
        state = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
