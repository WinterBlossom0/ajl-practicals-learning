public class Q12_CommandPatternDemo {
    public static void main(String[] args) {
        Light light = new Light();
        Command command = new LightOnCommand(light);
        SimpleRemote remote = new SimpleRemote(command);
        remote.pressButton(); // Light is ON
    }
}

interface Command {
    void execute();
}

class Light {
    public void turnOn() { System.out.println("Light is ON"); }
}

class LightOnCommand implements Command {
    private final Light light;
    public LightOnCommand(Light l) { light = l; }
    public void execute() { light.turnOn(); }
}

class SimpleRemote {
    private final Command command;
    public SimpleRemote(Command c) { command = c; }
    public void pressButton() { command.execute(); }
}
