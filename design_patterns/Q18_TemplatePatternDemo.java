public class Q18_TemplatePatternDemo {
    public static void main(String[] args) {

        // Same template, different item
        Drink drink = new Tea();
        drink.makeDrink();

        System.out.println();

        drink = new Coffee();
        drink.makeDrink();
    }
}

// Abstract class: contains fixed algorithm
abstract class Drink {

    // Template method: fixed steps
    final void makeDrink() {
        boilWater();
        addItem();
        serve();
    }

    void boilWater() {
        System.out.println("Boiling water");
    }

    // Child class will define this step
    abstract void addItem();

    void serve() {
        System.out.println("Serving drink");
    }
}

// Child class 1
class Tea extends Drink {
    void addItem() {
        System.out.println("Adding tea leaves");
    }
}

// Child class 2
class Coffee extends Drink {
    void addItem() {
        System.out.println("Adding coffee powder");
    }
}
