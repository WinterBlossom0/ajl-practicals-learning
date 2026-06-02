public class Q11_ProxyPatternDemo {
    public static void main(String[] args) {
        Image image = new ProxyImage();
        image.display(); // Lazy-loads the image and displays it
    }
}

interface Image {
    void display();
}

class RealImage implements Image {
    public RealImage() { System.out.println("Loading from disk..."); }
    public void display() { System.out.println("Displaying Real Image"); }
}

class ProxyImage implements Image {
    private RealImage realImage;
    public void display() {
        if (realImage == null) realImage = new RealImage();
        realImage.display();
    }
}
