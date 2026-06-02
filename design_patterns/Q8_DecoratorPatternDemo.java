public class Q8_DecoratorPatternDemo {
    public static void main(String[] args) {
        Coffee simple = new SimpleCoffee();
        Coffee milkCoffee = new MilkDecorator(simple);
        System.out.println(milkCoffee.getDescription() + " $" + milkCoffee.getCost());
    }
}

interface Coffee {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee {
    public String getDescription() { return "Coffee"; }
    public double getCost() { return 2.0; }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    public CoffeeDecorator(Coffee c) { coffee = c; }
    public String getDescription() { return coffee.getDescription(); }
    public double getCost() { return coffee.getCost(); }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee c) { super(c); }
    public String getDescription() { return super.getDescription() + ", Milk"; }
    public double getCost() { return super.getCost() + 0.5; }
}
