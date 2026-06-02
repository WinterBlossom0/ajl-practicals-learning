package difficult_questions;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Q5: Student and Course Registration Microservices Simulator
 * 
 * To make this code run directly on any Java JRE without Spring Boot jar dependencies,
 * this demo implements two microservices using Java's built-in HttpServer and HttpClient.
 * It models Spring Boot controllers and endpoints exactly as specified:
 * 
 * - StudentService (Port 8081): 
 *   - POST /students (Register student)
 *   - GET /students/{id} (Check student details; returns 404 if not found)
 * 
 * - CourseService (Port 8082):
 *   - POST /enroll?courseId=X&studentId=Y (Enroll student; calls StudentService to verify existence)
 *   - GET /courses/{courseId}/students (List enrolled students)
 * 
 * Downtime handling: If StudentService is down (e.g. server offline), CourseService handles
 * the Connection Exception gracefully and returns a clean HTTP 503 Service Unavailable error.
 */
public class Q5_StudentCourseRegistrationDemo {

    public static void main(String[] args) {
        System.out.println("=== Q5: Student & Course Registration Microservices ===");

        // Step 1: Start Microservices
        StudentService studentService = new StudentService(8081);
        CourseService courseService = new CourseService(8082);

        studentService.start();
        courseService.start();
        System.out.println("Services started in background threads.");

        // Step 2: Test client operations using HttpClient
        HttpClient client = HttpClient.newHttpClient();
        try {
            // Test 2.1: Register student "Alice" (ID: 101) in StudentService
            System.out.println("\n--- Step 2.1: Registering Student 'Alice' (ID: 101) ---");
            HttpResponse<String> regResponse = client.send(
                HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8081/students"))
                    .POST(HttpRequest.BodyPublishers.ofString("id=101&name=Alice"))
                    .build(),
                HttpResponse.BodyHandlers.ofString()
            );
            System.out.println("StudentService Response Status: " + regResponse.statusCode());
            System.out.println("StudentService Response Body: " + regResponse.body());

            // Test 2.2: Enroll student Alice in course "Java-101" via CourseService
            System.out.println("\n--- Step 2.2: Enrolling Alice (ID: 101) in Course 'Java-101' ---");
            HttpResponse<String> enrollResponse = client.send(
                HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8082/enroll?courseId=Java-101&studentId=101"))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build(),
                HttpResponse.BodyHandlers.ofString()
            );
            System.out.println("CourseService Response Status: " + enrollResponse.statusCode());
            System.out.println("CourseService Response Body: " + enrollResponse.body());

            // Test 2.3: Enroll non-existent student (ID: 999) - Should return 400 Bad Request
            System.out.println("\n--- Step 2.3: Enrolling Invalid Student (ID: 999) ---");
            HttpResponse<String> badEnrollResponse = client.send(
                HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8082/enroll?courseId=Java-101&studentId=999"))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build(),
                HttpResponse.BodyHandlers.ofString()
            );
            System.out.println("CourseService Response Status: " + badEnrollResponse.statusCode());
            System.out.println("CourseService Response Body: " + badEnrollResponse.body());

            // Test 2.4: Stop StudentService (simulate downtime) and check CourseService handling
            System.out.println("\n--- Step 2.4: Simulating StudentService Downtime (Shutting down Port 8081) ---");
            studentService.stop();

            HttpResponse<String> downtimeResponse = client.send(
                HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8082/enroll?courseId=Java-101&studentId=101"))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build(),
                HttpResponse.BodyHandlers.ofString()
            );
            System.out.println("CourseService Response Status: " + downtimeResponse.statusCode());
            System.out.println("CourseService Response Body: " + downtimeResponse.body());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up and shutdown servers
            studentService.stop();
            courseService.stop();
            System.out.println("\nAll microservices shutdown successfully.");
        }
    }

    // ----------------------------------------------------
    // MICROSERVICE 1: STUDENT SERVICE (PORT 8081)
    // ----------------------------------------------------
    static class StudentService {
        private final int port;
        private HttpServer server;
        private final Map<String, String> studentDatabase = new ConcurrentHashMap<>();

        public StudentService(int port) {
            this.port = port;
        }

        public void start() {
            try {
                server = HttpServer.create(new InetSocketAddress(port), 0);
                
                // Route: GET /students/{id} or POST /students
                server.createContext("/students", new HttpHandler() {
                    @Override
                    public void handle(HttpExchange exchange) throws IOException {
                        String method = exchange.getRequestMethod();
                        String path = exchange.getRequestURI().getPath();

                        if ("POST".equalsIgnoreCase(method)) {
                            // Read post body (format: id=101&name=Alice)
                            String body = new String(exchange.getRequestBody().readAllBytes());
                            Map<String, String> params = parseQuery(body);
                            String id = params.get("id");
                            String name = params.get("name");

                            if (id != null && name != null) {
                                studentDatabase.put(id, name);
                                sendResponse(exchange, 201, "Student " + name + " registered successfully.");
                            } else {
                                sendResponse(exchange, 400, "Bad Request: Missing student id or name.");
                            }
                        } 
                        else if ("GET".equalsIgnoreCase(method)) {
                            // Extract ID from path e.g. /students/101
                            String[] segments = path.split("/");
                            if (segments.length > 2) {
                                String id = segments[2];
                                if (studentDatabase.containsKey(id)) {
                                    sendResponse(exchange, 200, "ID: " + id + ", Name: " + studentDatabase.get(id));
                                } else {
                                    sendResponse(exchange, 404, "Student Not Found");
                                }
                            } else {
                                sendResponse(exchange, 400, "Bad Request: Missing student ID.");
                            }
                        }
                    }
                });

                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void stop() {
            if (server != null) {
                server.stop(0);
            }
        }
    }

    // ----------------------------------------------------
    // MICROSERVICE 2: COURSE SERVICE (PORT 8082)
    // ----------------------------------------------------
    static class CourseService {
        private final int port;
        private HttpServer server;
        private final Map<String, List<String>> enrollments = new ConcurrentHashMap<>();

        public CourseService(int port) {
            this.port = port;
        }

        public void start() {
            try {
                server = HttpServer.create(new InetSocketAddress(port), 0);
                
                // Route: POST /enroll
                server.createContext("/enroll", new HttpHandler() {
                    @Override
                    public void handle(HttpExchange exchange) throws IOException {
                        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                            sendResponse(exchange, 405, "Method Not Allowed");
                            return;
                        }

                        // Parse query parameters
                        String query = exchange.getRequestURI().getQuery();
                        Map<String, String> params = parseQuery(query);
                        String courseId = params.get("courseId");
                        String studentId = params.get("studentId");

                        if (courseId == null || studentId == null) {
                            sendResponse(exchange, 400, "Bad Request: Missing courseId or studentId.");
                            return;
                        }

                        // Step 1: Inter-service HTTP call to StudentService (Port 8081)
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:8081/students/" + studentId))
                            .GET()
                            .build();

                        try {
                            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                            if (response.statusCode() == 404) {
                                // Graceful Error Handling: Student Service returned 404
                                sendResponse(exchange, 400, "Enrollment Failed: Student ID " + studentId + " does not exist in registry.");
                                return;
                            }
                        } catch (IOException | InterruptedException e) {
                            // Graceful Error Handling: Student Service is Down
                            sendResponse(exchange, 503, "Enrollment Failed: Student Registry Service is currently offline. Details: " + e.getMessage());
                            return;
                        }

                        // Step 2: Enroll the student since verification passed
                        enrollments.computeIfAbsent(courseId, k -> new ArrayList<>()).add(studentId);
                        sendResponse(exchange, 200, "Student " + studentId + " enrolled successfully in Course " + courseId);
                    }
                });

                // Route: GET /courses/{courseId}/students
                server.createContext("/courses", new HttpHandler() {
                    @Override
                    public void handle(HttpExchange exchange) throws IOException {
                        String path = exchange.getRequestURI().getPath();
                        String[] segments = path.split("/"); // e.g. ["", "courses", "Java-101", "students"]
                        
                        if (segments.length >= 4 && "students".equalsIgnoreCase(segments[3])) {
                            String courseId = segments[2];
                            List<String> studentList = enrollments.getOrDefault(courseId, Collections.emptyList());
                            sendResponse(exchange, 200, studentList.toString());
                        } else {
                            sendResponse(exchange, 400, "Bad Request: Invalid path layout.");
                        }
                    }
                });

                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void stop() {
            if (server != null) {
                server.stop(0);
            }
        }
    }

    // ----------------------------------------------------
    // UTILITY METHODS FOR HTTP SERVICES
    // ----------------------------------------------------
    private static void sendResponse(HttpExchange exchange, int statusCode, String responseText) throws IOException {
        byte[] bytes = responseText.getBytes();
        exchange.sendResponseHeaders(statusCode, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    private static Map<String, String> parseQuery(String query) {
        Map<String, String> result = new HashMap<>();
        if (query == null || query.isEmpty()) {
            return result;
        }
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }
}
