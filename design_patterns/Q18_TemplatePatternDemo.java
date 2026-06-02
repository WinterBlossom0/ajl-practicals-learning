/**
 * Q18: Template Design Pattern (Behavioral Pattern)
 * 
 * Explanation:
 * The Template Method Pattern defines the skeleton of an algorithm in a method, 
 * deferring some steps to subclasses. It lets subclasses redefine certain steps 
 * of an algorithm without changing the algorithm's overall structure.
 * 
 * Real-world analogy: A recipe for baking bread. The main structure 
 * (mix ingredients, knead, bake) is the same, but the exact flour 
 * or baking duration can be customized by concrete bread types.
 */

// Main class to demonstrate Template Pattern (must be first for direct Java runner)
public class Q18_TemplatePatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q18: Template Design Pattern Demo ===");

        System.out.println("--- Playing Cricket ---");
        Game cricket = new Cricket();
        cricket.play();

        System.out.println("\n--- Playing Football ---");
        Game football = new Football();
        football.play();
    }
}

// Abstract class containing the Template Method (play)
abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    // Template method (declared final so subclasses cannot override its structure)
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
}

// Concrete classes extending Game
class Cricket extends Game {
    @Override
    void initialize() {
        System.out.println("Cricket Game: Initialized! Players registered.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game: Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket Game: Finished!");
    }
}

class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football Game: Initialized! Pitch ready.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game: Started. Match kick-off!");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game: Finished!");
    }
}
