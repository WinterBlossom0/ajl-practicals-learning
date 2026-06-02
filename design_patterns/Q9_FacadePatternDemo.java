public class Q9_FacadePatternDemo {
    public static void main(String[] args) {
        // Client interacts only with the Facade class
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}

// Subsystem Class 1
class CPU {
    void freeze() { System.out.println("CPU Freeze"); }
}

// Subsystem Class 2
class HardDrive {
    void read() { System.out.println("HD Read"); }
}

// Facade Class: Hides the complex interactions of subsystems behind a simple API
class ComputerFacade {
    private final CPU cpu = new CPU();
    private final HardDrive hd = new HardDrive();
    
    public void start() {
        cpu.freeze();
        hd.read();
    }
}
