public class Q5_AdapterPatternDemo {
    public static void main(String[] args) {
        ToyDuck toy = new PlasticToyDuck();
        ToyDuck adapter = new BirdAdapter(new Sparrow());
        
        toy.squeak();
        adapter.squeak(); // Chirps instead of squeak via adapter
    }
}

interface ToyDuck {
    void squeak();
}

class PlasticToyDuck implements ToyDuck {
    public void squeak() { System.out.println("Squeak"); }
}

interface Bird {
    void makeSound();
}

class Sparrow implements Bird {
    public void makeSound() { System.out.println("Chirp Chirp"); }
}

class BirdAdapter implements ToyDuck {
    private final Bird bird;
    public BirdAdapter(Bird bird) { this.bird = bird; }
    public void squeak() { bird.makeSound(); }
}
