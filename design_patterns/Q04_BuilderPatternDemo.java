public class Q04_BuilderPatternDemo {
    public static void main(String[] args) {
        // Construct the complex User object step-by-step using method chaining
        User u = new User.UserBuilder("John", "Doe").age(25).build();
        System.out.println(u.firstName + " " + u.lastName + ", Age: " + u.age);
    }
}

class User {
    // Required fields
    String firstName, lastName;
    // Optional field
    int age;

    // Private constructor so object can only be created via the Builder
    private User(UserBuilder b) {
        this.firstName = b.firstName;
        this.lastName = b.lastName;
        this.age = b.age;
    }

    // Static nested Builder class
    public static class UserBuilder {
        String firstName, lastName;
        int age;

        // Constructor handles required fields
        public UserBuilder(String fn, String ln) {
            this.firstName = fn;
            this.lastName = ln;
        }

        // Setter method for optional field (returns 'this' for method chaining)
        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        // Builds and returns the final complex object
        public User build() {
            return new User(this);
        }
    }
}
