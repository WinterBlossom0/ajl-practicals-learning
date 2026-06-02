public class Q17_StatePatternDemo {
    public static void main(String[] args) {
        Context context = new Context();
        State start = new StartState();
        start.doAction(context);
        System.out.println(context.getState()); // Start State
    }
}

interface State {
    void doAction(Context c);
}

class StartState implements State {
    public void doAction(Context c) { c.setState(this); }
    public String toString() { return "Start State"; }
}

class Context {
    private State state;
    public void setState(State s) { state = s; }
    public State getState() { return state; }
}
