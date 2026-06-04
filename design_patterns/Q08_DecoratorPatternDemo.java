public class Q08_DecoratorPatternDemo {
    public static void main(String[] args) {

        // Original coffee
        Coffee coffee = new SimpleCoffee();

        // Add milk using decorator
        coffee = new MilkDecorator(coffee);

        // Show final result
        System.out.println(coffee.getDescription());
    }
}

// Common interface
interface Coffee {
    String getDescription();
}

// Original object
class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Coffee";
    }
}

// Decorator also implements same interface
class MilkDecorator implements Coffee {
    Coffee coffee;

    // Take existing coffee object
    MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    // Add extra feature to existing object
    public String getDescription() {
        return coffee.getDescription() + " + Milk";
    }
}
