/**
 * Q28: Create Deadlock between Producer and Consumer Classes
 * 
 * Explanation:
 * A deadlock is a multithreading situation where two or more threads are blocked 
 * forever, waiting for each other to release locks.
 * Here, we create two resources: LockA (representing buffer/storage) and LockB (representing printer/resource).
 * - The Producer Thread locks LockA and tries to lock LockB.
 * - The Consumer Thread locks LockB and tries to lock LockA.
 * Because they acquire locks in circular order, they end up waiting forever, causing a deadlock.
 */
public class Q28_ProducerConsumerDeadlockDemo {

    // Two shared resources
    private static final Object LockA = new Object();
    private static final Object LockB = new Object();

    public static void main(String[] args) {
        System.out.println("=== Q28: Producer-Consumer Deadlock Demo ===");

        // Producer Thread
        Thread producer = new Thread(() -> {
            synchronized (LockA) {
                System.out.println("Producer: Acquired LockA. Waiting for LockB...");
                try {
                    Thread.sleep(100); // Allow consumer thread to run and grab LockB
                } catch (InterruptedException e) {}

                synchronized (LockB) {
                    System.out.println("Producer: Acquired LockB!");
                }
            }
        }, "Producer-Thread");

        // Consumer Thread
        Thread consumer = new Thread(() -> {
            synchronized (LockB) {
                System.out.println("Consumer: Acquired LockB. Waiting for LockA...");
                try {
                    Thread.sleep(100); // Allow producer thread to run and grab LockA
                } catch (InterruptedException e) {}

                synchronized (LockA) {
                    System.out.println("Consumer: Acquired LockA!");
                }
            }
        }, "Consumer-Thread");

        // Start both threads
        producer.start();
        consumer.start();

        // Safe exit daemon thread: since this program is in deadlock,
        // it will hang forever. We will shut it down after 3 seconds so execution continues.
        Thread safetyTimer = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("\n[System Info] Deadlock detected (no progress for 3 seconds). Terminating program.");
                System.exit(0);
            } catch (InterruptedException e) {}
        });
        safetyTimer.setDaemon(true);
        safetyTimer.start();
    }
}
