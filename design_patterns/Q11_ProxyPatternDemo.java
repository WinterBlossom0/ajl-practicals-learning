public class Q11_ProxyPatternDemo {
    public static void main(String[] args) {

        // User talks to proxy, not real object directly
        Internet internet = new InternetProxy();

        internet.connect();
    }
}

// Common interface
interface Internet {
    void connect();
}

// Real object
class RealInternet implements Internet {
    public void connect() {
        System.out.println("Connected to internet");
    }
}

// Proxy object controls access to RealInternet
class InternetProxy implements Internet {
    RealInternet internet = new RealInternet();

    public void connect() {
        System.out.println("Proxy checking access");

        // Proxy allows the real object to work
        internet.connect();
    }
}
