public class Q17_StatePatternDemo {
    public static void main(String[] args) {
        Fan fan = new Fan();

        fan.pressButton();
        fan.pressButton();
    }
}

// State interface: every state must define button behavior
interface FanState {
    void pressButton(Fan fan);
}

// Concrete State 1: OFF state
class OffState implements FanState {
    public void pressButton(Fan fan) {
        System.out.println("Fan is now ON");

        // Change state from OFF to ON
        fan.state = new OnState();
    }
}

// Concrete State 2: ON state
class OnState implements FanState {
    public void pressButton(Fan fan) {
        System.out.println("Fan is now OFF");

        // Change state from ON to OFF
        fan.state = new OffState();
    }
}

// Context class: object whose behavior changes
class Fan {
    FanState state = new OffState(); // initial state

    void pressButton() {
        // Current state decides what happens
        state.pressButton(this);
    }
}
