public class Q18_TemplatePatternDemo {
    public static void main(String[] args) {
        Game game = new Chess();
        game.play(); // Runs initialize -> start -> end
    }
}

abstract class Game {
    abstract void initialize();
    abstract void start();
    abstract void end();
    public final void play() { initialize(); start(); end(); } // Template Method
}

class Chess extends Game {
    void initialize() { System.out.println("Chess Init"); }
    void start() { System.out.println("Chess Start"); }
    void end() { System.out.println("Chess End"); }
}
