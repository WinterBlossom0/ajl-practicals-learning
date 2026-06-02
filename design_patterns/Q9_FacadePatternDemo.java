public class Q9_FacadePatternDemo {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}

class CPU {
    void freeze() { System.out.println("CPU Freeze"); }
}

class HardDrive {
    void read() { System.out.println("HD Read"); }
}

class ComputerFacade {
    private final CPU cpu = new CPU();
    private final HardDrive hd = new HardDrive();
    public void start() {
        cpu.freeze();
        hd.read();
    }
}
