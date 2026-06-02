public class Q12_CommandPatternDemo {
    public static void main(String[] args) {
        Light light = new Light(); // Receiver
        Command command = new LightOnCommand(light); // Command
        SimpleRemote remote = new SimpleRemote(command); // Invoker
        
        remote.pressButton(); // Invoker triggers execution: Light is ON
    }
}

// 1. Command Interface
interface Command {
    void execute();
}

// 2. Receiver (The object that performs the actual action)
class Light {
    public void turnOn() { System.out.println("Light is ON"); }
}

// 3. Concrete Command (Wraps receiver action inside execute method)
class LightOnCommand implements Command {
    private final Light light;
    public LightOnCommand(Light l) { light = l; }
    public void execute() { light.turnOn(); }
}

// 4. Invoker (Asks the command to carry out the request)
class SimpleRemote {
    private final Command command;
    public SimpleRemote(Command c) { command = c; }
    public void pressButton() { command.execute(); }
}
