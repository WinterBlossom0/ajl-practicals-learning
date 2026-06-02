public class Q17_StatePatternDemo {
    public static void main(String[] args) {
        Context context = new Context();
        State start = new StartState();
        
        // Execute action which alters the context's internal state
        start.doAction(context);
        System.out.println(context.getState()); // Start State
    }
}

// 1. State Interface
interface State {
    void doAction(Context c);
}

// 2. Concrete State (changes Context state to itself)
class StartState implements State {
    public void doAction(Context c) { c.setState(this); }
    public String toString() { return "Start State"; }
}

// 3. Context (holds current active state)
class Context {
    private State state;
    public void setState(State s) { state = s; }
    public State getState() { return state; }
}
