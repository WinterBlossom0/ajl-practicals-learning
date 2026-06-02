public class Q4_BuilderPatternDemo {
    public static void main(String[] args) {
        User u = new User.UserBuilder("John", "Doe").age(25).build();
        System.out.println(u.firstName + " " + u.lastName + ", Age: " + u.age);
    }
}

class User {
    String firstName, lastName;
    int age;

    private User(UserBuilder b) {
        this.firstName = b.firstName;
        this.lastName = b.lastName;
        this.age = b.age;
    }

    public static class UserBuilder {
        String firstName, lastName;
        int age;

        public UserBuilder(String fn, String ln) {
            this.firstName = fn;
            this.lastName = ln;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
