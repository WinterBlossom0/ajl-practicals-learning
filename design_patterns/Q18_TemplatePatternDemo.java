public class Q18_TemplatePatternDemo {
    public static void main(String[] args) {
        Game game = new Chess();
        // Invoke template method to execute steps in order
        game.play(); 
    }
}

// Abstract class defining the template method pattern structure
abstract class Game {
    // Primitive operations implemented by subclasses
    abstract void initialize();
    abstract void start();
    abstract void end();
    
    // The Template Method: Final structure of algorithm steps
    public final void play() { 
        initialize(); 
        start(); 
        end(); 
    }
}

// Concrete subclass providing specific step details
class Chess extends Game {
    void initialize() { System.out.println("Chess Init"); }
    void start() { System.out.println("Chess Start"); }
    void end() { System.out.println("Chess End"); }
}
