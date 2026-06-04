public class Q04_BuilderPatternDemo {
    public static void main(String[] args) {

        // Build Phone object step by step
        Phone p = new PhoneBuilder()
                .setBrand("Samsung")
                .setPrice(50000)
                .build();

        p.show();
    }
}

// Object to be created
class Phone {
    String brand;
    int price;

    // Constructor takes Builder object
    Phone(PhoneBuilder builder) {
        brand = builder.brand;
        price = builder.price;
    }

    void show() {
        System.out.println(brand + " " + price);
    }
}

// Builder creates Phone step by step
class PhoneBuilder {
    String brand;
    int price;

    // Set brand and return same builder
    PhoneBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    // Set price and return same builder
    PhoneBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    // Create final Phone object
    Phone build() {
        return new Phone(this);
    }
}
