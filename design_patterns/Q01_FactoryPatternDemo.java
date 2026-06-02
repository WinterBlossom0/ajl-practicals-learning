public class Q01_FactoryPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern ===");

        Restaurant beefRest = new BeefBurgerRestaurant();
        beefRest.orderBurger();

        Restaurant veggieRest = new VeggieBurgerRestaurant();
        veggieRest.orderBurger();
    }
}

// Product Interface
interface Burger {
    void prepare();
}

// Concrete Products
class BeefBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Beef Burger.");
    }
}

class VeggieBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Veggie Burger.");
    }
}

// Creator
abstract class Restaurant {
    public Burger orderBurger() {
        Burger burger = createBurger();
        burger.prepare();
        return burger;
    }
    
    public abstract Burger createBurger();
}

// Concrete Creators
class BeefBurgerRestaurant extends Restaurant {
    @Override
    public Burger createBurger() {
        return new BeefBurger();
    }
}

class VeggieBurgerRestaurant extends Restaurant {
    @Override
    public Burger createBurger() {
        return new VeggieBurger();
    }
}
