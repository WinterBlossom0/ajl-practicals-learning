import java.util.Date;

/**
 * Q14: Mediator Design Pattern (Behavioral Pattern)
 * 
 * Explanation:
 * The Mediator Pattern is used to reduce communication complexity between multiple 
 * objects or classes. It provides a mediator class that handles all communications 
 * between different classes, promoting loose coupling by keeping objects from 
 * referring to each other explicitly.
 * 
 * Real-world analogy: An Air Traffic Control tower acts as a mediator, coordinating 
 * airplanes so they don't land/take off at the same time or collide, rather than 
 * airplanes communicating directly with each other.
 */

// Main class to demonstrate Mediator Pattern (must be first for direct Java runner)
public class Q14_MediatorPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q14: Mediator Design Pattern Demo ===");

        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi John!");
        john.sendMessage("Hello Robert! How are you?");
    }
}

// Mediator class
class ChatRoom {
    public static void showMessage(User user, String message) {
        System.out.println(new Date().toString() + " [" + user.getName() + "] : " + message);
    }
}

// User class that uses the Mediator to communicate
class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public void sendMessage(String message) {
        ChatRoom.showMessage(this, message);
    }
}
