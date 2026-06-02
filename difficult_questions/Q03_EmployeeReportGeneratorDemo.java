package difficult_questions;

import java.util.*;
import java.util.stream.Collectors;

// Records automatically generate constructor, getters (name(), department(), etc.), and toString()
record Employee(String name, String department, double salary, int yearJoined) {}

public class Q03_EmployeeReportGeneratorDemo {
    public static void main(String[] args) {
        List<Employee> list = Arrays.asList(
            new Employee("Alice", "Engineering", 95000, 2018),
            new Employee("Bob", "Marketing", 60000, 2021),
            new Employee("Carol", "Engineering", 112000, 2020),
            new Employee("Dave", "HR", 52000, 2019),
            new Employee("Eve", "Marketing", 78000, 2022),
            new Employee("Frank", "Engineering", 88000, 2021)
        );

        // 1. Average salary per department
        System.out.println("1. Avg Salary per Dept: " + list.stream()
            .collect(Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::salary))));

        // 2. Highest paid employee in each department
        System.out.println("2. Highest Paid per Dept: " + list.stream()
            .collect(Collectors.groupingBy(Employee::department, 
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::salary)), Optional::get))));

        // 3. Names of employees joining after 2019, sorted alphabetically and comma-separated
        System.out.println("3. Joined after 2019: " + list.stream()
            .filter(e -> e.yearJoined() > 2019)
            .map(Employee::name)
            .sorted()
            .collect(Collectors.joining(", ")));
    }
}
