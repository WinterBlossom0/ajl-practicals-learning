public class Q02_AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Abstract Factory Pattern ===");

        // Create MSI products using MSI Factory
        Company msi = new MsiManufacturer();
        Gpu msiGpu = msi.createGpu();
        Monitor msiMonitor = msi.createMonitor();
        msiGpu.assemble();
        msiMonitor.display();

        // Create Asus products using Asus Factory
        Company asus = new AsusManufacturer();
        Gpu asusGpu = asus.createGpu();
        Monitor asusMonitor = asus.createMonitor();
        asusGpu.assemble();
        asusMonitor.display();
    }
}

// Product 1 Interface
interface Gpu {
    void assemble();
}

// Product 2 Interface
interface Monitor {
    void display();
}

// Concrete MSI Products
class MsiGpu implements Gpu {
    @Override
    public void assemble() {
        System.out.println("Assembling MSI GPU.");
    }
}

class MsiMonitor implements Monitor {
    @Override
    public void display() {
        System.out.println("Displaying MSI Monitor.");
    }
}

// Concrete Asus Products
class AsusGpu implements Gpu {
    @Override
    public void assemble() {
        System.out.println("Assembling Asus GPU.");
    }
}

class AsusMonitor implements Monitor {
    @Override
    public void display() {
        System.out.println("Displaying Asus Monitor.");
    }
}

// Abstract Factory: Defines interface for creating families of products
abstract class Company {
    public abstract Gpu createGpu();
    public abstract Monitor createMonitor();
}

// Concrete Factory 1: MSI Manufacturer
class MsiManufacturer extends Company {
    @Override
    public Gpu createGpu() {
        return new MsiGpu();
    }

    @Override
    public Monitor createMonitor() {
        return new MsiMonitor();
    }
}

// Concrete Factory 2: Asus Manufacturer
class AsusManufacturer extends Company {
    @Override
    public Gpu createGpu() {
        return new AsusGpu();
    }

    @Override
    public Monitor createMonitor() {
        return new AsusMonitor();
    }
}
