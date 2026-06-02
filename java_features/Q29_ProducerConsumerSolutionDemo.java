import java.util.LinkedList;
import java.util.Queue;

/**
 * Q29: Solve Deadlock between Producer and Consumer using wait-notify mechanism
 * 
 * Explanation:
 * To prevent deadlocks and orchestrate communication between Producer and Consumer 
 * threads, we use Java's wait-notify mechanism on a shared buffer.
 * - The Producer yields and waits when the buffer is full.
 * - The Consumer yields and waits when the buffer is empty.
 * - When a Producer adds an item, it calls notify() to wake up the waiting Consumer.
 * - When a Consumer takes an item, it calls notify() to wake up the waiting Producer.
 * This prevents deadlocks and ensures efficient resource sharing.
 */

// Main class to demonstrate the solution (must be first for direct Java runner)
public class Q29_ProducerConsumerSolutionDemo {
    public static void main(String[] args) {
        System.out.println("=== Q29: Producer-Consumer Solution (Wait-Notify) ===");

        SharedBuffer buffer = new SharedBuffer();

        // Producer Thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Consumer Thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.consume();
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Execution Completed Successfully.");
    }
}

// SharedBuffer class
class SharedBuffer {
    private Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 3;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == CAPACITY) {
            System.out.println("Buffer full. Producer is waiting...");
            wait();
        }

        queue.add(value);
        System.out.println("Produced: " + value);
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Buffer empty. Consumer is waiting...");
            wait();
        }

        int value = queue.poll();
        System.out.println("Consumed: " + value);
        notify();
        return value;
    }
}
