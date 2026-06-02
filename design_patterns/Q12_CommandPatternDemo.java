import java.util.ArrayList;
import java.util.List;

/**
 * Q12: Command Design Pattern (Behavioral Pattern)
 * 
 * Explanation:
 * The Command Pattern encapsulates a request as an object, thereby letting you 
 * parameterize clients with different requests, queue or log requests, 
 * and support undoable operations. It decouples the invoker from the receiver.
 * 
 * Real-world analogy: In a restaurant, the waiter (Invoker) takes your order (Command) 
 * and gives it to the chef (Receiver) to prepare. The waiter doesn't need to know 
 * how to cook, and the chef doesn't need to know who ordered it.
 */

// Main class to demonstrate Command Pattern (must be first for direct Java runner)
public class Q12_CommandPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q12: Command Design Pattern Demo ===");

        // Receiver
        Stock stock = new Stock();

        // Concrete Commands
        BuyStock buyStockOrder = new BuyStock(stock);
        SellStock sellStockOrder = new SellStock(stock);

        // Invoker
        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        // Execute orders
        broker.placeOrders();
    }
}

// Request / Command Interface
interface Order {
    void execute();
}

// Receiver class that knows how to perform the actual operation
class Stock {
    private String name = "Google Stock";
    private int quantity = 10;

    public void buy() {
        System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] bought");
    }

    public void sell() {
        System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] sold");
    }
}

// Concrete Command classes implementing the Order interface
class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.buy();
    }
}

class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.sell();
    }
}

// Invoker class that stores and executes commands
class Broker {
    private List<Order> orderList = new ArrayList<>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
