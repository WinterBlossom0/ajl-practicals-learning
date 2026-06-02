import sys
from reportlab.lib.pagesizes import letter
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle, KeepTogether, Preformatted
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib import colors
from reportlab.pdfgen import canvas

class NumberedCanvas(canvas.Canvas):
    """
    Two-pass canvas to calculate the total page count dynamically 
    and render elegant headers/footers.
    """
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self._saved_page_states = []

    def showPage(self):
        self._saved_page_states.append(dict(self.__dict__))
        self._startPage()

    def save(self):
        num_pages = len(self._saved_page_states)
        for state in self._saved_page_states:
            self.__dict__.update(state)
            self.draw_page_decorations(num_pages)
            super().showPage()
        super().save()

    def draw_page_decorations(self, page_count):
        self.saveState()
        
        # 1. Header (Rendered on all pages except the cover/first page if desired, but we render on all here)
        self.setFont("Helvetica-Bold", 8)
        self.setFillColor(colors.HexColor('#1A365D')) # Deep Blue Primary
        self.drawString(54, 755, "ADVANCED JAVA LABORATORY (AJL) VIVA GUIDE")
        
        self.setFont("Helvetica", 8)
        self.setFillColor(colors.HexColor('#718096')) # Slate gray
        self.drawRightString(558, 755, "SOLID, STREAMS, & MICROSERVICES")
        
        # Header Thin Line
        self.setStrokeColor(colors.HexColor('#E2E8F0'))
        self.setLineWidth(0.75)
        self.line(54, 747, 558, 747)
        
        # 2. Footer (Rendered on all pages)
        # Footer Thin Line
        self.line(54, 55, 558, 55)
        
        self.drawString(54, 40, "GitHub: WinterBlossom0/ajl-practicals-learning")
        
        page_text = f"Page {self._pageNumber} of {page_count}"
        self.drawRightString(558, 40, page_text)
        
        self.restoreState()

def create_viva_pdf(output_filename):
    # Setup document template with 0.75in margins (54pt)
    # Extra top/bottom margins (72pt) to clear headers and footers
    doc = SimpleDocTemplate(
        output_filename,
        pagesize=letter,
        leftMargin=54,
        rightMargin=54,
        topMargin=72,
        bottomMargin=72
    )

    styles = getSampleStyleSheet()
    
    # Custom styles to achieve premium look
    title_style = ParagraphStyle(
        'DocTitle',
        parent=styles['Normal'],
        fontName='Helvetica-Bold',
        fontSize=24,
        leading=28,
        textColor=colors.HexColor('#1A365D'),
        spaceAfter=15
    )
    
    subtitle_style = ParagraphStyle(
        'DocSubtitle',
        parent=styles['Normal'],
        fontName='Helvetica',
        fontSize=12,
        leading=16,
        textColor=colors.HexColor('#4A5568'),
        spaceAfter=30
    )
    
    h1_style = ParagraphStyle(
        'Heading1',
        parent=styles['Normal'],
        fontName='Helvetica-Bold',
        fontSize=16,
        leading=20,
        textColor=colors.HexColor('#1A365D'),
        spaceBefore=15,
        spaceAfter=8,
        keepWithNext=True
    )
    
    h2_style = ParagraphStyle(
        'Heading2',
        parent=styles['Normal'],
        fontName='Helvetica-Bold',
        fontSize=11,
        leading=15,
        textColor=colors.HexColor('#2B6CB0'), # Medium Blue
        spaceBefore=10,
        spaceAfter=4,
        keepWithNext=True
    )
    
    body_style = ParagraphStyle(
        'Body',
        parent=styles['Normal'],
        fontName='Helvetica',
        fontSize=9.5,
        leading=13.5,
        textColor=colors.HexColor('#2D3748'),
        spaceAfter=8
    )

    bullet_style = ParagraphStyle(
        'Bullet',
        parent=body_style,
        leftIndent=15,
        firstLineIndent=-10,
        spaceAfter=4
    )

    code_style = ParagraphStyle(
        'CodeText',
        parent=styles['Normal'],
        fontName='Courier',
        fontSize=7.5,
        leading=9.5,
        textColor=colors.HexColor('#1A202C'),
    )

    story = []

    # Title & Metadata
    story.append(Paragraph("AJL Viva Q&A Reference Guide", title_style))
    story.append(Paragraph("Study companion covering SOLID Principles, Streams API, and Spring Boot Microservices.<br/>Compiled for quick revision and oral presentation.", subtitle_style))
    story.append(Spacer(1, 10))

    # Helper function to add a code block inside a table with a background
    def add_code_block(code_text):
        # We replace tabs with spaces for consistent layout
        clean_code = code_text.replace('\t', '    ')
        p = Preformatted(clean_code, code_style)
        t = Table([[p]], colWidths=[504])
        t.setStyle(TableStyle([
            ('BACKGROUND', (0,0), (-1,-1), colors.HexColor('#F7FAFC')),
            ('BOX', (0,0), (-1,-1), 0.5, colors.HexColor('#E2E8F0')),
            ('PADDING', (0,0), (-1,-1), 8),
            ('TOPPADDING', (0,0), (-1,-1), 8),
            ('BOTTOMPADDING', (0,0), (-1,-1), 8),
        ]))
        story.append(t)
        story.append(Spacer(1, 10))

    # ----------------------------------------------------
    # Q1: SOLID Principles - Notification System
    # ----------------------------------------------------
    story.append(Paragraph("Q1) SOLID Principles: Design a Notification System", h1_style))
    story.append(Paragraph("<b>Question:</b> Create a notification system that sends alerts via Email, SMS, and Push. It should be easy to add new channels (like Slack) without changing existing code.", body_style))
    
    story.append(Paragraph("<b>Core Concepts & SOLID Alignment:</b>", h2_style))
    story.append(Paragraph("&bull; <b>Single Responsibility Principle (SRP):</b> Each sender class (<code>EmailSender</code>, <code>SmsSender</code>, <code>PushSender</code>) has only one reason to change, which is the modification of its respective channel configuration or communication mechanism.", bullet_style))
    story.append(Paragraph("&bull; <b>Dependency Inversion Principle (DIP):</b> The high-level module (<code>NotificationService</code>) does not depend on low-level concrete implementations (like <code>EmailSender</code>). Instead, it depends on the abstraction <code>NotificationSender</code>. We pass the interface implementation via the constructor (Constructor Injection).", bullet_style))
    story.append(Paragraph("&bull; <b>Open-Closed Principle (OCP):</b> The system is open for extension but closed for modification. We can easily add a new channel like <code>SlackSender</code> by implementing the <code>NotificationSender</code> interface, without altering the existing <code>NotificationService</code> or other classes.", bullet_style))

    story.append(Paragraph("<b>Java Implementation:</b>", h2_style))
    q1_code = """// Interface representing the abstract channel
interface NotificationSender {
    void send(String message, String recipient);
}

// Concrete Implementations of channels (each has a single responsibility)
class EmailSender implements NotificationSender {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Email sent to " + recipient + " | Message: " + message);
    }
}

class SmsSender implements NotificationSender {
    @Override
    public void send(String message, String recipient) {
        System.out.println("SMS sent to " + recipient + " | Message: " + message);
    }
}

class PushSender implements NotificationSender {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Push notification sent to " + recipient + " | Message: " + message);
    }
}

// NotificationService depends on abstraction (DIP)
class NotificationService {
    private final NotificationSender sender;

    // Constructor injection of the dependency
    public NotificationService(NotificationSender sender) {
        this.sender = sender;
    }

    public void alert(String message, String recipient) {
        sender.send(message, recipient);
    }
}

// Easy to extend for Slack (OCP)
class SlackSender implements NotificationSender {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Slack message sent to channel #" + recipient + " | Message: " + message);
    }
}"""
    add_code_block(q1_code)

    story.append(Paragraph("<b>Test / Demonstration Block:</b>", h2_style))
    q1_test_code = """public class NotificationDemo {
    public static void main(String[] args) {
        String msg = "Server is down!";
        
        // Testing all three channels using polymorphism
        NotificationService emailService = new NotificationService(new EmailSender());
        emailService.alert(msg, "admin@company.com");

        NotificationService smsService = new NotificationService(new SmsSender());
        smsService.alert(msg, "+1234567890");

        NotificationService pushService = new NotificationService(new PushSender());
        pushService.alert(msg, "Device_ID_9876");

        // Testing the extension (Slack)
        NotificationService slackService = new NotificationService(new SlackSender());
        slackService.alert(msg, "alerts-channel");
    }
}"""
    add_code_block(q1_test_code)
    story.append(Spacer(1, 10))

    # ----------------------------------------------------
    # Q2: SOLID Principles - Shape Area Calculator
    # ----------------------------------------------------
    story.append(Paragraph("Q2) SOLID Principles: Shape Area Calculator", h1_style))
    story.append(Paragraph("<b>Question:</b> Build a shape area calculator that can handle new shapes without modifying the calculator class.", body_style))
    
    story.append(Paragraph("<b>Core Concepts & SOLID Alignment:</b>", h2_style))
    story.append(Paragraph("&bull; <b>Open-Closed Principle (OCP):</b> The <code>AreaCalculator</code> class is closed for modification. Its <code>calculateTotalArea</code> method accepts a list of <code>Shape</code> objects and uses runtime polymorphism to evaluate individual areas. We can add <code>Pentagon</code> or any future shape without editing a single line of the calculator class.", bullet_style))
    story.append(Paragraph("&bull; <b>Polymorphism:</b> By defining an abstract method <code>area()</code> in the <code>Shape</code> interface, we hide specific calculation logic (like πr² for circles or 0.5 * b * h for triangles) behind a common method contract.", bullet_style))

    story.append(Paragraph("<b>Java Implementation:</b>", h2_style))
    q2_code = """import java.util.List;

// Shape abstraction interface
interface Shape {
    double area();
}

// Concrete Shape classes
class Circle implements Shape {
    private final double radius;
    public Circle(double radius) { this.radius = radius; }
    @Override public double area() { return Math.PI * radius * radius; }
}

class Rectangle implements Shape {
    private final double width, height;
    public Rectangle(double w, double h) { this.width = w; this.height = h; }
    @Override public double area() { return width * height; }
}

class Triangle implements Shape {
    private final double base, height;
    public Triangle(double b, double h) { this.base = b; this.height = h; }
    @Override public double area() { return 0.5 * base * height; }
}

// AreaCalculator is closed for modification
class AreaCalculator {
    public double calculateTotalArea(List<Shape> shapes) {
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.area(); // Polymorphic method call
        }
        return total;
    }
}

// Extension: Adding Pentagon without modifying AreaCalculator class
class Pentagon implements Shape {
    private final double side;
    public Pentagon(double side) { this.side = side; }
    @Override
    public double area() {
        // Area of a regular pentagon = 0.25 * sqrt(5 * (5 + 2 * sqrt(5))) * s^2
        return 0.25 * Math.sqrt(5 * (5 + 2 * Math.sqrt(5))) * side * side;
    }
}"""
    add_code_block(q2_code)

    story.append(Paragraph("<b>Test / Demonstration Block:</b>", h2_style))
    q2_test_code = """public class ShapeDemo {
    public static void main(String[] args) {
        List<Shape> shapes = List.of(
            new Circle(5),       // Area ~ 78.54
            new Rectangle(4, 5), // Area = 20
            new Triangle(3, 4),  // Area = 6
            new Pentagon(4)      // Area ~ 27.52
        );

        AreaCalculator calculator = new AreaCalculator();
        double totalArea = calculator.calculateTotalArea(shapes);
        System.out.printf("Total Area of mixed shapes: %.2f\\n", totalArea);
    }
}"""
    add_code_block(q2_test_code)
    story.append(Spacer(1, 10))

    # ----------------------------------------------------
    # Q3: Streams API - Employee Report Generator
    # ----------------------------------------------------
    story.append(Paragraph("Q3) Streams API: Employee Report Generator", h1_style))
    story.append(Paragraph("<b>Question:</b> Given a list of employees (Name, Department, Salary, Year Joined), produce a report containing average salary per department, highest paid employee in each department, and a comma-separated list of names of employees who joined after 2019 sorted alphabetically.", body_style))
    
    story.append(Paragraph("<b>Stream API Operations Used:</b>", h2_style))
    story.append(Paragraph("&bull; <code>Collectors.groupingBy()</code>: Groups elements by a classification function (department).", bullet_style))
    story.append(Paragraph("&bull; <code>Collectors.averagingDouble()</code>: Calculates the average of a double-valued property of objects.", bullet_style))
    story.append(Paragraph("&bull; <code>Collectors.collectingAndThen()</code>: Adapts a collector to perform an additional finishing transformation (extracting values from Optionals).", bullet_style))
    story.append(Paragraph("&bull; <code>Collectors.maxBy()</code>: Finds the maximum element according to a comparator (salary).", bullet_style))
    story.append(Paragraph("&bull; <code>filter()</code>, <code>map()</code>, <code>sorted()</code>, <code>Collectors.joining(\", \")</code>: Processes pipelines for filtering by year, extracting names, sorting alphabetically, and stitching into a comma-separated string.", bullet_style))

    story.append(Paragraph("<b>Java Implementation:</b>", h2_style))
    q3_code = """import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name, department;
    double salary;
    int yearJoined;

    public Employee(String name, String dept, double sal, int year) {
        this.name = name; this.department = dept; this.salary = sal; this.yearJoined = year;
    }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getYearJoined() { return yearJoined; }

    @Override
    public String toString() { return name + " ($" + salary + ")"; }
}

public class EmployeeReportGenerator {
    public static void main(String[] args) {
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
        System.out.println("Average Salary Per Department: " + avgSalaryPerDept);

        // 2. Highest paid employee in each department
        Map<String, Employee> highestPaidPerDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                    Optional::get
                )
            ));
        System.out.println("Highest Paid in Each Department: " + highestPaidPerDept);

        // 3. Comma-separated list of names of employees who joined after 2019, sorted alphabetically
        String joinedAfter2019 = employees.stream()
            .filter(e -> e.getYearJoined() > 2019)
            .map(Employee::getName)
            .sorted()
            .collect(Collectors.joining(", "));
        System.out.println("Employees who joined after 2019: " + joinedAfter2019);
    }
}"""
    add_code_block(q3_code)
    story.append(Spacer(1, 10))

    # ----------------------------------------------------
    # Q4: Streams API - File Word Frequency Counter
    # ----------------------------------------------------
    story.append(Paragraph("Q4) Streams API: File Word Frequency Counter", h1_style))
    story.append(Paragraph("<b>Question:</b> Read a text file and use the Streams API to: 1. Count the frequency of each word (case-insensitive, ignore punctuation), 2. Print top 10 most frequent words, 3. Find words appearing exactly once, and 4. Count unique words longer than 6 characters.", body_style))
    
    story.append(Paragraph("<b>Key Stream Operations:</b>", h2_style))
    story.append(Paragraph("&bull; <code>Files.lines()</code>: Reads lines from a file as a <code>Stream&lt;String&gt;</code> in a memory-efficient, lazy-loaded fashion.", bullet_style))
    story.append(Paragraph("&bull; <code>flatMap()</code>: Flattens a stream of arrays/streams (individual lines split into arrays of words) into a single continuous stream of elements.", bullet_style))
    story.append(Paragraph("&bull; <code>Collectors.toMap(..., Integer::sum)</code>: Aggregates list contents into maps while resolving key collisions by executing a merge function (counting frequencies).", bullet_style))

    story.append(Paragraph("<b>Java Implementation:</b>", h2_style))
    q4_code = """import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        String filePath = "sample.txt"; // Target test text file

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            // Process lines into a frequency map
            Map<String, Long> wordFrequencies = lines
                .flatMap(line -> Arrays.stream(line.split("\\\\W+"))) // Split by non-word characters
                .map(String::toLowerCase)                             // Convert to lowercase (case-insensitive)
                .filter(word -> !word.isEmpty())                     // Exclude empty tokens
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            // 1. Print all frequencies (brief display or total size)
            System.out.println("Total Unique Words Found: " + wordFrequencies.size());

            // 2. Print top 10 most frequent words
            System.out.println("\\n--- Top 10 Most Frequent Words ---");
            wordFrequencies.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

            // 3. Find all words that appear exactly once
            List<String> onceAppearingWords = wordFrequencies.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
            System.out.println("\\nWords appearing exactly once: " + onceAppearingWords);

            // 4. Count how many unique words are longer than 6 characters
            long longWordsCount = wordFrequencies.keySet().stream()
                .filter(word -> word.length() > 6)
                .count();
            System.out.println("\\nUnique words longer than 6 chars: " + longWordsCount);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}"""
    add_code_block(q4_code)
    story.append(Spacer(1, 10))

    # ----------------------------------------------------
    # Q5: Microservices - Student & Course Registration System
    # ----------------------------------------------------
    story.append(Paragraph("Q5) Microservices: Student & Course Registration", h1_style))
    story.append(Paragraph("<b>Question:</b> Build two Spring Boot microservices that talk to each other: StudentService (port 8081) and CourseService (port 8082). CourseService calls StudentService to verify student existence during enrollment, handling service downtime and bad IDs gracefully.", body_style))
    
    story.append(Paragraph("<b>Core Concepts & Architectural Workflow:</b>", h2_style))
    story.append(Paragraph("&bull; <b>Inter-service Communication:</b> CourseService makes an HTTP GET request to <code>http://localhost:8081/students/{id}</code> using Spring's <code>RestTemplate</code> or <code>WebClient</code>.", bullet_style))
    story.append(Paragraph("&bull; <b>Resilience and Fallbacks:</b> When the StudentService is down, the RestTemplate calls throw a <code>ResourceAccessException</code>. We wrap the HTTP request in a try-catch block to return a clean, friendly error message instead of letting Spring dump a stack trace to the user.", bullet_style))
    story.append(Paragraph("&bull; <b>Client Error Handling:</b> If the student does not exist, StudentService returns HTTP 404. CourseService catches <code>HttpClientErrorException.NotFound</code> to return a structured JSON response (e.g. 'Cannot enroll: Student ID not found').", bullet_style))

    story.append(Paragraph("<b>StudentService Controller (Port 8081):</b>", h2_style))
    student_service_code = """@RestController
@RequestMapping("/students")
public class StudentController {
    // In-memory storage mapping studentId -> name
    private final Map<String, String> studentDb = new ConcurrentHashMap<>();

    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
        studentDb.put(student.getId(), student.getName());
        return ResponseEntity.ok("Student registered successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@pathVariable String id) {
        if (!studentDb.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
        return ResponseEntity.ok(new Student(id, studentDb.get(id)));
    }
}"""
    add_code_block(student_service_code)

    story.append(Paragraph("<b>CourseService Controller & Inter-service Communication (Port 8082):</b>", h2_style))
    course_service_code = """@RestController
public class CourseController {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String STUDENT_SERVICE_URL = "http://localhost:8081/students/";
    private final Map<String, List<String>> courseEnrollments = new ConcurrentHashMap<>(); // courseId -> studentIds

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollStudent(@RequestParam String courseId, @RequestParam String studentId) {
        // Step 1: Call StudentService to verify student exists
        try {
            String targetUrl = STUDENT_SERVICE_URL + studentId;
            // Calls StudentService. Returns student details or throws 404
            restTemplate.getForObject(targetUrl, Object.class); 
        } 
        catch (HttpClientErrorException.NotFound e) {
            // Graceful handling of invalid Student ID
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Enrollment Failed: Student ID " + studentId + " does not exist.");
        } 
        catch (ResourceAccessException e) {
            // Graceful handling of StudentService downtime
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                                 .body("Enrollment Failed: Student Verification Service is currently offline. Please try again later.");
        }

        // Step 2: Proceed with enrollment if no exception was thrown
        courseEnrollments.computeIfAbsent(courseId, k -> new ArrayList<>()).add(studentId);
        return ResponseEntity.ok("Student " + studentId + " enrolled successfully in " + courseId + ".");
    }
}"""
    add_code_block(course_service_code)

    # Build the document
    doc.build(story, canvasmaker=NumberedCanvas)

if __name__ == "__main__":
    output_pdf = "ajl_viva_preparation.pdf"
    if len(sys.argv) > 1:
        output_pdf = sys.argv[1]
    create_viva_pdf(output_pdf)
    print(f"PDF successfully generated: {output_pdf}")
