import java.util.ArrayList;
import java.util.List;

/**
 * Q7: Composite Design Pattern (Structural Pattern)
 * 
 * Explanation:
 * The Composite Pattern allows you to compose objects into tree structures 
 * to represent part-whole hierarchies. It lets clients treat individual objects 
 * and compositions of objects uniformly.
 * 
 * Real-world analogy: A computer file system where both Files and Folders 
 * (which can hold files and folders) are treated as file system resources.
 */

// Main class to demonstrate Composite Pattern (must be first for direct Java runner)
public class Q7_CompositePatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Q7: Composite Design Pattern Demo ===");

        // CEO of the company
        Employee CEO = new Employee("John Doe", "CEO", 300000);

        // Department heads
        Employee headSales = new Employee("Robert Smith", "Head Sales", 200000);
        Employee headMarketing = new Employee("Michel Jones", "Head Marketing", 200000);

        // Subordinates under department heads
        Employee clerk1 = new Employee("Laura Croft", "Marketing", 100000);
        Employee clerk2 = new Employee("Bob Johnson", "Marketing", 100000);

        Employee salesExecutive1 = new Employee("Richard Nixon", "Sales", 120000);
        Employee salesExecutive2 = new Employee("Rob Ford", "Sales", 120000);

        // Build the organizational hierarchy tree
        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        // Print the tree structure
        System.out.println(CEO);
        
        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println("  " + headEmployee);
            
            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println("    " + employee);
            }
        }
    }
}

// Employee class containing list of Employee objects (Part-Whole hierarchy)
class Employee {
    private String name;
    private String dept;
    private int salary;
    private List<Employee> subordinates;

    public Employee(String name, String dept, int sal) {
        this.name = name;
        this.dept = dept;
        this.salary = sal;
        subordinates = new ArrayList<>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public String toString() {
        return "Employee :[ Name : " + name + ", dept : " + dept + ", salary : " + salary + " ]";
    }
}
