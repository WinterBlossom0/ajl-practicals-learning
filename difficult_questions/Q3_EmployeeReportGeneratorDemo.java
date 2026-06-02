package difficult_questions;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Q3: Employee Report Generator using Streams API
 * 
 * Objectives:
 * 1. Average salary per department
 * 2. Highest paid employee in each department
 * 3. Comma-separated list of names of employees who joined after 2019, sorted alphabetically
 */

public class Q3_EmployeeReportGeneratorDemo {
    public static void main(String[] args) {
        System.out.println("=== Q3: Employee Report Generator (Streams API) ===");

        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", 95000, 2018),
            new Employee("Bob", "Marketing", 60000, 2021),
            new Employee("Carol", "Engineering", 112000, 2020),
            new Employee("Dave", "HR", 52000, 2019),
            new Employee("Eve", "Marketing", 78000, 2022),
            new Employee("Frank", "Engineering", 88000, 2021)
        );

        // 1. Average salary per department
        Map<String, Double> avgSalaryPerDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
            ));
        System.out.println("\n1. Average Salary per Department:");
        avgSalaryPerDept.forEach((dept, avgSal) -> System.out.printf("   %s: $%.2f\n", dept, avgSal));

        // 2. Highest paid employee in each department
        Map<String, Optional<Employee>> highestPaidPerDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
            ));
        System.out.println("\n2. Highest Paid Employee in each Department:");
        highestPaidPerDept.forEach((dept, empOpt) -> 
            System.out.println("   " + dept + ": " + empOpt.orElse(null))
        );

        // 3. Comma-separated list of names of employees who joined after 2019, sorted alphabetically
        String namesJoinedAfter2019 = employees.stream()
            .filter(e -> e.getYearJoined() > 2019)
            .map(Employee::getName)
            .sorted()
            .collect(Collectors.joining(", "));
        System.out.println("\n3. Employees who joined after 2019 (sorted alphabetically):");
        System.out.println("   " + namesJoinedAfter2019);
    }
}

class Employee {
    private final String name;
    private final String department;
    private final double salary;
    private final int yearJoined;

    public Employee(String name, String department, double salary, int yearJoined) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.yearJoined = yearJoined;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getYearJoined() {
        return yearJoined;
    }

    @Override
    public String toString() {
        return name + " ($" + salary + ")";
    }
}
