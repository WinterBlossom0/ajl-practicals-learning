public class Q14_MediatorPatternDemo {
    public static void main(String[] args) {
        User robert = new User("Robert");
        robert.sendMessage("Hi!");
    }
}

class ChatRoom {
    public static void showMessage(User user, String msg) {
        System.out.println(user.name + ": " + msg);
    }
}

class User {
    String name;
    public User(String n) { name = n; }
    public void sendMessage(String msg) { ChatRoom.showMessage(this, msg); }
}
