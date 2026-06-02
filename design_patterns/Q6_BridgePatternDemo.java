public class Q6_BridgePatternDemo {
    public static void main(String[] args) {
        // Construct refined abstraction with concrete state implementation
        Device tv = new TV(new OnState());
        tv.pressButton(); // TV is: ON
    }
}

// 1. Implementor Interface
interface State {
    void handle();
}

// 2. Concrete Implementor
class OnState implements State {
    public void handle() { System.out.println("ON"); }
}

// 3. Abstraction (Contains the Bridge reference to Implementor)
abstract class Device {
    protected State state; // The Bridge
    protected Device(State s) { state = s; }
    abstract void pressButton();
}

// 4. Refined Abstraction
class TV extends Device {
    public TV(State s) { super(s); }
    void pressButton() { System.out.print("TV is: "); state.handle(); }
}
