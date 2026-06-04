import java.util.*;

public class Q16_ObserverPatternDemo {
    public static void main(String[] args) {

        // Create subject
        Channel channel = new Channel();

        // Add observers
        channel.subscribe(new Subscriber("Raj"));
        channel.subscribe(new Subscriber("Aman"));

        // One change notifies all observers
        channel.uploadVideo("Java Observer Pattern");
    }
}

// Observer interface: all observers must have update()
interface Observer {
    void update(String video);
}

// Concrete Observer: gets notified
class Subscriber implements Observer {
    String name;

    Subscriber(String name) {
        this.name = name;
    }

    public void update(String video) {
        System.out.println(name + " got notification: " + video);
    }
}

// Subject: stores observers and notifies them
class Channel {
    ArrayList<Observer> subscribers = new ArrayList<>();

    // Add observer
    void subscribe(Observer observer) {
        subscribers.add(observer);
    }

    // Notify all observers
    void uploadVideo(String video) {
        for (Observer observer : subscribers) {
            observer.update(video);
        }
    }
}
