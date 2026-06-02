public class Q8_DecoratorPatternDemo {
    public static void main(String[] args) {
        Coffee simple = new SimpleCoffee();
        // Wrap simple coffee with milk decorator
        Coffee milkCoffee = new MilkDecorator(simple);
        
        System.out.println(milkCoffee.getDescription() + " $" + milkCoffee.getCost());
    }
}

// 1. Component Interface
interface Coffee {
    String getDescription();
    double getCost();
}

// 2. Concrete Component
class SimpleCoffee implements Coffee {
    public String getDescription() { return "Coffee"; }
    public double getCost() { return 2.0; }
}

// 3. Abstract Decorator class (delegates to wrapped component)
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee; // Reference to wrapped object
    public CoffeeDecorator(Coffee c) { coffee = c; }
    
    public String getDescription() { return coffee.getDescription(); }
    public double getCost() { return coffee.getCost(); }
}

// 4. Concrete Decorator (Adds new features/costs)
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee c) { super(c); }
    
    // Override methods to append extra decorations
    public String getDescription() { return super.getDescription() + ", Milk"; }
    public double getCost() { return super.getCost() + 0.5; }
}
