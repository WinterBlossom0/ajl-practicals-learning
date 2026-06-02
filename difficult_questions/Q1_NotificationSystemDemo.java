package difficult_questions;

/**
 * Q1: Notification System demonstrating SOLID Principles (SRP & DIP)
 * 
 * - Single Responsibility Principle (SRP): Each sender class handles only one channel.
 * - Dependency Inversion Principle (DIP): NotificationService depends on the NotificationSender abstraction, not concrete classes.
 * - Open-Closed Principle (OCP): We can add SlackSender without modifying any existing classes.
 */

public class Q1_NotificationSystemDemo {
    public static void main(String[] args) {
        System.out.println("=== Q1: Notification System (SOLID) ===");
        String alertMsg = "Server is down!";

        // Send through Email
        NotificationService emailService = new NotificationService(new EmailSender());
        emailService.sendAlert(alertMsg, "admin@company.com");

        // Send through SMS
        NotificationService smsService = new NotificationService(new SmsSender());
        smsService.sendAlert(alertMsg, "+1234567890");

        // Send through Push
        NotificationService pushService = new NotificationService(new PushSender());
        pushService.sendAlert(alertMsg, "admin_phone_device");

        // Send through Slack (Newly added channel showing OCP)
        NotificationService slackService = new NotificationService(new SlackSender());
        slackService.sendAlert(alertMsg, "devops-alerts");
    }
}

// Interface representing the abstraction
interface NotificationSender {
    void send(String message, String recipient);
}

// Concrete Implementations of channels (SRP: Each class does one thing)
class EmailSender implements NotificationSender {
    @Override
    public void send(String message, String recipient) {
        System.out.println("[EMAIL] Sent to: " + recipient + " | Message: " + message);
    }
}

class SmsSender implements NotificationSender {
    @Override
    public void send(String message, String recipient) {
        System.out.println("[SMS] Sent to: " + recipient + " | Message: " + message);
    }
}

class PushSender implements NotificationSender {
    @Override
    public void send(String message, String recipient) {
        System.out.println("[PUSH] Sent to Device: " + recipient + " | Message: " + message);
    }
}

// NotificationService accepts any sender via constructor (Dependency Inversion)
class NotificationService {
    private final NotificationSender sender;

    public NotificationService(NotificationSender sender) {
        this.sender = sender;
    }

    public void sendAlert(String message, String recipient) {
        sender.send(message, recipient);
    }
}

// OCP Demonstration: Adding a new channel (Slack) without altering existing code
class SlackSender implements NotificationSender {
    @Override
    public void send(String message, String recipient) {
        System.out.println("[SLACK] Sent to Channel #" + recipient + " | Message: " + message);
    }
}
