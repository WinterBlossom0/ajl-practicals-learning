public class Q12_CommandPatternDemo {
    public static void main(String[] args) {

        // Real object
        Light light = new Light();

        // Command object stores the action
        Command command = new LightOnCommand(light);

        // Remote executes the command
        Remote remote = new Remote(command);
        remote.pressButton();
    }
}

// Command interface
interface Command {
    void execute();
}

// Receiver: class that actually does the work
class Light {
    void turnOn() {
        System.out.println("Light is ON");
    }
}

// Concrete command: wraps the action
class LightOnCommand implements Command {
    Light light;

    LightOnCommand(Light light) {
        this.light = light;
    }

    // Calls the real action
    public void execute() {
        light.turnOn();
    }
}

// Invoker: uses the command
class Remote {
    Command command;

    Remote(Command command) {
        this.command = command;
    }

    void pressButton() {
        command.execute();
    }
}
