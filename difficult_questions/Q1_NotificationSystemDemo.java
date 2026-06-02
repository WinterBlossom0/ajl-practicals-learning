package difficult_questions;

// Interface representing the abstraction
interface NotificationSender {
    void send(String message, String recipient);
}

// Concrete channels (Each does only one thing - SRP)
class EmailSender implements NotificationSender {
    public void send(String msg, String rec) { System.out.println("Email to " + rec + ": " + msg); }
}

class SmsSender implements NotificationSender {
    public void send(String msg, String rec) { System.out.println("SMS to " + rec + ": " + msg); }
}

class PushSender implements NotificationSender {
    public void send(String msg, String rec) { System.out.println("Push to " + rec + ": " + msg); }
}

// NotificationService depends on the interface abstraction (DIP)
class NotificationService {
    private final NotificationSender sender;
    public NotificationService(NotificationSender sender) { this.sender = sender; }
    public void alert(String msg, String rec) { sender.send(msg, rec); }
}

// Slack extension showing Open-Closed Principle (OCP)
class SlackSender implements NotificationSender {
    public void send(String msg, String rec) { System.out.println("Slack to " + rec + ": " + msg); }
}

public class Q1_NotificationSystemDemo {
    public static void main(String[] args) {
        String msg = "Server is down!";

        // Send alert through all 3 required channels
        new NotificationService(new EmailSender()).alert(msg, "admin@test.com");
        new NotificationService(new SmsSender()).alert(msg, "99999");
        new NotificationService(new PushSender()).alert(msg, "device_id");

        // Send through Slack (Newly added channel - OCP)
        new NotificationService(new SlackSender()).alert(msg, "alerts");
    }
}
