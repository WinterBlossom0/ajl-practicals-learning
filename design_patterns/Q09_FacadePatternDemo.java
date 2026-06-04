public class Q09_FacadePatternDemo {
    public static void main(String[] args) {

        // User only talks to Facade
        Computer computer = new Computer();

        // One simple call starts everything
        computer.startComputer();
    }
}

// Subsystem class 1
class CPU {
    void start() {
        System.out.println("CPU started");
    }
}

// Subsystem class 2
class RAM {
    void load() {
        System.out.println("RAM loaded");
    }
}

// Subsystem class 3
class HardDisk {
    void read() {
        System.out.println("Hard Disk reading");
    }
}

// Facade class: gives one simple method
class Computer {
    CPU cpu = new CPU();
    RAM ram = new RAM();
    HardDisk hardDisk = new HardDisk();

    // One method hides all internal steps
    void startComputer() {
        cpu.start();
        ram.load();
        hardDisk.read();
    }
}
