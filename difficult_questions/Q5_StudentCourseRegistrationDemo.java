package difficult_questions;

import java.util.*;

/**
 * Q5: Student and Course Registration Microservices
 * 
 * ===================================================================
 * FOR EXAMS: WRITE THE STUDENTCONTROLLER AND COURSECONTROLLER CLASSES.
 * ===================================================================
 * 
 * - StudentService (Port 8081): Registers students and gets their details.
 * - CourseService (Port 8082): Calls StudentService to verify student existence using RestTemplate.
 * - Downtime Handling: Catches connection or client exceptions and returns clean 400/503 statuses instead of stack traces.
 */

public class Q5_StudentCourseRegistrationDemo {
    public static void main(String[] args) {
        System.out.println("=== Q5: Student & Course Registration Simulator ===");

        // Setup the mock environment references
        StudentController studentService = new StudentController();
        CourseController courseService = new CourseController();
        RestTemplate.studentControllerRef = studentService;

        // 1. Register student "Alice" (ID: 101)
        System.out.println("\n--- 1. Registering Alice (ID: 101) in StudentService ---");
        String regResult = studentService.registerStudent("101", "Alice");
        System.out.println("Response: " + regResult);

        // 2. Enroll Alice in course "Java-101" via CourseService (Verification Success)
        System.out.println("\n--- 2. Enrolling Alice (ID: 101) in Course 'Java-101' ---");
        ResponseEntity<String> enrollResult = courseService.enroll("Java-101", "101");
        System.out.println("Response: " + enrollResult);

        // 3. Enroll non-existent student (ID: 999) - Handles bad ID gracefully
        System.out.println("\n--- 3. Enrolling Non-Existent Student (ID: 999) ---");
        ResponseEntity<String> badEnrollResult = courseService.enroll("Java-101", "999");
        System.out.println("Response: " + badEnrollResult);

        // 4. Simulate StudentService downtime - Handles offline service gracefully
        System.out.println("\n--- 4. Simulating StudentService Downtime (Service Offline) ---");
        RestTemplate.isServiceDown = true;
        ResponseEntity<String> downtimeResult = courseService.enroll("Java-101", "101");
        System.out.println("Response: " + downtimeResult);
    }
}

// ===================================================================
// STUDY THIS FOR EXAMS: Clean & Simple Spring Boot Microservices
// ===================================================================

// Student Microservice Controller (Runs on Port 8081)
@RestController
@RequestMapping("/students")
class StudentController {
    // In-memory Database
    private final Map<String, String> studentDb = new HashMap<>();

    @PostMapping
    public String registerStudent(@RequestParam String id, @RequestParam String name) {
        studentDb.put(id, name);
        return "Student registered successfully.";
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getStudent(@PathVariable String id) {
        if (!studentDb.containsKey(id)) {
            return ResponseEntity.status(404).body("Student Not Found"); // Return 404
        }
        return ResponseEntity.ok(studentDb.get(id));
    }
}

// Course Microservice Controller (Runs on Port 8082)
@RestController
class CourseController {
    private final Map<String, List<String>> courseEnrollments = new HashMap<>();
    private final RestTemplate restTemplate = new RestTemplate(); // RestTemplate to call StudentService

    @PostMapping("/enroll")
    public ResponseEntity<String> enroll(@RequestParam String courseId, @RequestParam String studentId) {
        try {
            // Step 1: Call StudentService to verify student exists
            String studentServiceUrl = "http://localhost:8081/students/" + studentId;
            
            // Call service; throws exception if student not found (404) or service is down
            restTemplate.getForObject(studentServiceUrl, String.class);

            // Step 2: Proceed with enrollment
            courseEnrollments.computeIfAbsent(courseId, k -> new ArrayList<>()).add(studentId);
            return ResponseEntity.ok("Student enrolled successfully.");

        } catch (StudentNotFoundException e) {
            // Graceful error handling for invalid student ID
            return ResponseEntity.status(400).body("Enrollment Failed: Invalid Student ID.");
        } catch (ServiceUnavailableException e) {
            // Graceful error handling for downtime
            return ResponseEntity.status(503).body("Enrollment Failed: Student Service is currently offline.");
        }
    }
}

// ===================================================================
// MOCK LAYER: Only to allow compilation and run without external Jars
// ===================================================================

// Spring Boot Annotations Placeholder
@interface RestController {}
@interface RequestMapping { String value(); }
@interface PostMapping { String value() default ""; }
@interface GetMapping { String value() default ""; }
@interface RequestParam {}
@interface PathVariable {}

// Mock ResponseEntity representation
class ResponseEntity<T> {
    private final int status;
    private final T body;

    public ResponseEntity(int status, T body) {
        this.status = status;
        this.body = body;
    }

    public static <T> ResponseEntity<T> ok(T body) {
        return new ResponseEntity<>(200, body);
    }

    public static <T> ResponseEntityBuilder<T> status(int status) {
        return new ResponseEntityBuilder<>(status);
    }

    @Override
    public String toString() {
        return "HTTP " + status + " | Body: " + body;
    }

    static class ResponseEntityBuilder<T> {
        private final int status;
        public ResponseEntityBuilder(int status) { this.status = status; }
        public <R> ResponseEntity<R> body(R body) { return new ResponseEntity<>(status, body); }
    }
}

// Mock Exceptions
class StudentNotFoundException extends RuntimeException {}
class ServiceUnavailableException extends RuntimeException {}

// Mock RestTemplate class simulating API calls
class RestTemplate {
    public static StudentController studentControllerRef;
    public static boolean isServiceDown = false;

    public String getForObject(String url, Class<String> responseType) {
        if (isServiceDown) {
            throw new ServiceUnavailableException(); // Simulates connection error
        }
        
        // Extract student ID from the end of url
        String id = url.substring(url.lastIndexOf("/") + 1);
        ResponseEntity<String> response = studentControllerRef.getStudent(id);
        
        if (response.toString().contains("404")) {
            throw new StudentNotFoundException(); // Simulates HTTP 404
        }
        return response.toString();
    }
}
