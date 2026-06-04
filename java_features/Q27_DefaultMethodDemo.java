public class Q27_DefaultMethodDemo {
    public static void main(String[] args) {
        Car c = new Car();
        c.start();
    }
}

interface Vehicle {
    default void start() {
        System.out.println("Vehicle is starting");
    }
}

class Car implements Vehicle {
    // No need to override default method
}
