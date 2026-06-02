public class Q11_ProxyPatternDemo {
    public static void main(String[] args) {
        // Instantiate the proxy instead of the real object
        Image image = new ProxyImage();
        
        // Real object is instantiated only when display is invoked (Lazy Loading)
        image.display(); 
    }
}

// Common Interface
interface Image {
    void display();
}

// Real Subject
class RealImage implements Image {
    public RealImage() { System.out.println("Loading from disk..."); }
    public void display() { System.out.println("Displaying Real Image"); }
}

// Proxy: Controls access to the Real Subject
class ProxyImage implements Image {
    private RealImage realImage; // Reference to the real object
    
    public void display() {
        // Instantiate real subject only when needed (Lazy Initialization)
        if (realImage == null) realImage = new RealImage();
        realImage.display();
    }
}
