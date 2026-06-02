public class Q6_BridgePatternDemo {
    public static void main(String[] args) {
        Device tv = new TV(new OnState());
        tv.pressButton(); // TV is ON
    }
}

interface State {
    void handle();
}

class OnState implements State {
    public void handle() { System.out.println("ON"); }
}

abstract class Device {
    protected State state;
    protected Device(State s) { state = s; }
    abstract void pressButton();
}

class TV extends Device {
    public TV(State s) { super(s); }
    void pressButton() { System.out.print("TV is: "); state.handle(); }
}
