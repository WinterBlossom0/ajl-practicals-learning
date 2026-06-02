public class Q05_AdapterPatternDemo {
    public static void main(String[] args) {
        ToyDuck toy = new PlasticToyDuck();
        // Wrap sparrow in adapter to make it look like a ToyDuck
        ToyDuck adapter = new BirdAdapter(new Sparrow());
        
        toy.squeak();
        adapter.squeak(); // Sparrow chirps instead of squeaking
    }
}

// 1. Target Interface (What the client expects)
interface ToyDuck {
    void squeak();
}

// 2. Concrete implementation of Target
class PlasticToyDuck implements ToyDuck {
    public void squeak() { System.out.println("Squeak"); }
}

// 3. Adaptee Interface (Incompatible interface that we want to adapt)
interface Bird {
    void makeSound();
}

// 4. Concrete implementation of Adaptee
class Sparrow implements Bird {
    public void makeSound() { System.out.println("Chirp Chirp"); }
}

// 5. Adapter Class: Bridges ToyDuck and Bird
class BirdAdapter implements ToyDuck {
    private final Bird bird;
    public BirdAdapter(Bird bird) { this.bird = bird; }
    
    // Convert squeak action to makeSound
    public void squeak() { bird.makeSound(); }
}
