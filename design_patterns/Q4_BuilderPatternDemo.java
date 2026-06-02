/**
 * Q4: Builder Design Pattern (Creational Pattern)
 * 
 * Explanation:
 * The Builder Pattern is used to construct a complex object step-by-step.
 * It separates the construction of a complex object from its representation,
 * allowing the same construction process to create different representations.
 * It is especially useful when an object has many optional parameters.
 * 
 * Real-world analogy: Ordering a customized burger (with or without extra cheese,
 * lettuce, tomato, or bacon).
 */

// Main class to demonstrate the Builder Pattern (must be first for direct Java runner)
public class Q4_BuilderPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q4: Builder Design Pattern Demo ===");

        // Creating a computer with only required specifications
        Computer basicComputer = new Computer.ComputerBuilder("500 GB", "8 GB")
                .build();
        System.out.println("Basic " + basicComputer);

        // Creating a high-end gaming computer with optional configurations using method chaining
        Computer gamingComputer = new Computer.ComputerBuilder("2 TB", "32 GB")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .build();
        System.out.println("Gaming " + gamingComputer);
    }
}

class Computer {
    // Required parameters
    private String HDD;
    private String RAM;

    // Optional parameters
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;

    // Getter methods
    public String getHDD() {
        return HDD;
    }

    public String getRAM() {
        return RAM;
    }

    public boolean isGraphicsCardEnabled() {
        return isGraphicsCardEnabled;
    }

    public boolean isBluetoothEnabled() {
        return isBluetoothEnabled;
    }

    // Private constructor so that client must use the Builder to create instances
    Computer(ComputerBuilder builder) {
        this.HDD = builder.HDD;
        this.RAM = builder.RAM;
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    @Override
    public String toString() {
        return "Computer [HDD=" + HDD + ", RAM=" + RAM 
                + ", isGraphicsCardEnabled=" + isGraphicsCardEnabled 
                + ", isBluetoothEnabled=" + isBluetoothEnabled + "]";
    }

    // Static Builder Class
    public static class ComputerBuilder {
        // Required parameters
        private String HDD;
        private String RAM;

        // Optional parameters
        private boolean isGraphicsCardEnabled;
        private boolean isBluetoothEnabled;

        // Constructor for required parameters
        public ComputerBuilder(String hdd, String ram) {
            this.HDD = hdd;
            this.RAM = ram;
        }

        // Setter methods for optional parameters (returning 'this' for method chaining)
        public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        // The final build method to return the assembled Computer object
        public Computer build() {
            return new Computer(this);
        }
    }
}
