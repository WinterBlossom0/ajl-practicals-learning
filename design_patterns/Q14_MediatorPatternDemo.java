public class Q14_MediatorPatternDemo {
    public static void main(String[] args) {
        User robert = new User("Robert");
        // User communicates through Mediator instead of contacting others directly
        robert.sendMessage("Hi!");
    }
}

// 1. Mediator class: centralizes communication logic between users
class ChatRoom {
    public static void showMessage(User user, String msg) {
        System.out.println(user.name + ": " + msg);
    }
}

// 2. Colleague Class (interacts with the Mediator)
class User {
    String name;
    public User(String n) { name = n; }
    
    // Delegate communication routing to Mediator ChatRoom
    public void sendMessage(String msg) { ChatRoom.showMessage(this, msg); }
}
