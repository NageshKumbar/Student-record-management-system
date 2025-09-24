import java.util.ArrayList;
import java.util.Scanner;


class Student {
    private int id;
    private String name;
    private double marks;
    private String grade;
    
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }
    

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getMarks() {
        return marks;
    }
    
    public String getGrade() {
        return grade;
    }
    

    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setMarks(double marks) {
        this.marks = marks;
        this.grade = calculateGrade(marks); 
    }
    

    private String calculateGrade(double marks) {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 60) return "C";
        else if (marks >= 50) return "D";
        else return "F";
    }
    
    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-20s | Marks: %-6.2f | Grade: %s", 
                           id, name, marks, grade);
    }
}

public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void displayMenu() {
        System.out.println("\n" + repeatChar('=', 50));
        System.out.println("       STUDENT RECORD MANAGEMENT SYSTEM");
        System.out.println(repeatChar('=', 50));
        System.out.println("1. Add Student Record");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student Record");
        System.out.println("5. Delete Student Record");
        System.out.println("6. Display Statistics");
        System.out.println("7. Exit");
        System.out.println(repeatChar('=', 50));
        System.out.print("Choose an option (1-7): ");
    }
    
    public static void addStudent() {
        System.out.println("\n--- ADD NEW STUDENT ---");
        
        try {
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 
            
            if (findStudentById(id) != null) {
                System.out.println("Error: Student with ID " + id + " already exists!");
                return;
            }
            
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine().trim();
            
            if (name.isEmpty()) {
                System.out.println("Error: Name cannot be empty!");
                return;
            }
            
            System.out.print("Enter Student Marks (0-100): ");
            double marks = scanner.nextDouble();
            
            if (marks < 0 || marks > 100) {
                System.out.println("Error: Marks must be between 0 and 100!");
                return;
            }
            
            Student newStudent = new Student(id, name, marks);
            students.add(newStudent);
            
            System.out.println("✓ Student added successfully!");
            System.out.println("Details: " + newStudent);
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input! Please try again.");
            scanner.nextLine(); 
        }
    }
    
    public static void viewAllStudents() {
        System.out.println("\n--- ALL STUDENT RECORDS ---");
        
        if (students.isEmpty()) {
            System.out.println("No student records found!");
            return;
        }
        
        System.out.println(repeatChar('-', 70));
        System.out.printf("%-5s | %-20s | %-6s | %-5s%n", "ID", "NAME", "MARKS", "GRADE");
        System.out.println(repeatChar('-', 70));
        
        for (Student student : students) {
            System.out.println(student);
        }
        
        System.out.println(repeatChar('-', 70));
        System.out.println("Total Students: " + students.size());
    }
    

    public static void searchStudentById() {
        System.out.println("\n--- SEARCH STUDENT ---");
        
        try {
            System.out.print("Enter Student ID to search: ");
            int id = scanner.nextInt();
            
            Student student = findStudentById(id);
            if (student != null) {
                System.out.println("Student Found:");
                System.out.println(repeatChar('-', 70));
                System.out.printf("%-5s | %-20s | %-6s | %-5s%n", "ID", "NAME", "MARKS", "GRADE");
                System.out.println(repeatChar('-', 70));
                System.out.println(student);
            } else {
                System.out.println("Student with ID " + id + " not found!");
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid input!");
            scanner.nextLine();
        }
    }
    
    public static void updateStudent() {
        System.out.println("\n--- UPDATE STUDENT RECORD ---");
        
        try {
            System.out.print("Enter Student ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            Student student = findStudentById(id);
            if (student == null) {
                System.out.println("Student with ID " + id + " not found!");
                return;
            }
            
            System.out.println("Current Details: " + student);
            System.out.println("\nWhat would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Marks");
            System.out.println("3. Both Name and Marks");
            System.out.print("Choose option (1-3): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine().trim();
                    if (!newName.isEmpty()) {
                        student.setName(newName);
                        System.out.println("✓ Name updated successfully!");
                    } else {
                        System.out.println("Error: Name cannot be empty!");
                        return;
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter new marks (0-100): ");
                    double newMarks = scanner.nextDouble();
                    if (newMarks >= 0 && newMarks <= 100) {
                        student.setMarks(newMarks);
                        System.out.println("✓ Marks updated successfully!");
                    } else {
                        System.out.println("Error: Marks must be between 0 and 100!");
                        return;
                    }
                    break;
                    
                case 3:
                    System.out.print("Enter new name: ");
                    String name = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("Error: Name cannot be empty!");
                        return;
                    }
                    
                    System.out.print("Enter new marks (0-100): ");
                    double marks = scanner.nextDouble();
                    if (marks < 0 || marks > 100) {
                        System.out.println("Error: Marks must be between 0 and 100!");
                        return;
                    }
                    
                    student.setName(name);
                    student.setMarks(marks);
                    System.out.println("✓ Name and marks updated successfully!");
                    break;
                    
                default:
                    System.out.println("Invalid option!");
                    return;
            }
            
            System.out.println("Updated Details: " + student);
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input!");
            scanner.nextLine();
        }
    }
    
    public static void deleteStudent() {
        System.out.println("\n--- DELETE STUDENT RECORD ---");
        
        try {
            System.out.print("Enter Student ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 
            
            Student student = findStudentById(id);
            if (student == null) {
                System.out.println("Student with ID " + id + " not found!");
                return;
            }
            
            System.out.println("Student to delete: " + student);
            System.out.print("Are you sure you want to delete this record? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (confirmation.equals("y") || confirmation.equals("yes")) {
                students.remove(student);
                System.out.println("✓ Student record deleted successfully!");
            } else {
                System.out.println("Delete operation cancelled.");
            }
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input!");
            scanner.nextLine();
        }
    }
    
    public static void displayStatistics() {
        System.out.println("\n--- STUDENT STATISTICS ---");
        
        if (students.isEmpty()) {
            System.out.println("No student records available for statistics!");
            return;
        }
        
        double totalMarks = 0;
        double highestMarks = students.get(0).getMarks();
        double lowestMarks = students.get(0).getMarks();
        Student topStudent = students.get(0);
        Student bottomStudent = students.get(0);
        
        int gradeA = 0, gradeB = 0, gradeC = 0, gradeD = 0, gradeF = 0;
        
        for (Student student : students) {
            double marks = student.getMarks();
            totalMarks += marks;
            
            if (marks > highestMarks) {
                highestMarks = marks;
                topStudent = student;
            }
            if (marks < lowestMarks) {
                lowestMarks = marks;
                bottomStudent = student;
            }
            
            String grade = student.getGrade();
            switch (grade.charAt(0)) {
                case 'A': gradeA++; break;
                case 'B': gradeB++; break;
                case 'C': gradeC++; break;
                case 'D': gradeD++; break;
                case 'F': gradeF++; break;
            }
        }
        
        double averageMarks = totalMarks / students.size();
        
        System.out.println(repeatChar('=', 60));
        System.out.println("Total Students: " + students.size());
        System.out.printf("Average Marks: %.2f%n", averageMarks);
        System.out.printf("Highest Marks: %.2f (ID: %d, Name: %s)%n", 
                         highestMarks, topStudent.getId(), topStudent.getName());
        System.out.printf("Lowest Marks: %.2f (ID: %d, Name: %s)%n", 
                         lowestMarks, bottomStudent.getId(), bottomStudent.getName());
        
        System.out.println("\nGrade Distribution:");
        System.out.println("A Grades: " + gradeA);
        System.out.println("B Grades: " + gradeB);
        System.out.println("C Grades: " + gradeC);
        System.out.println("D Grades: " + gradeD);
        System.out.println("F Grades: " + gradeF);
        System.out.println(repeatChar('=', 60));
    }
    
    private static Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    
    public static void initializeSampleData() {
        students.add(new Student(101, "Alice Johnson", 85.5));
        students.add(new Student(102, "Bob Smith", 92.0));
        students.add(new Student(103, "Charlie Brown", 78.5));
        System.out.println("Sample data loaded! (3 students added)");
    }
    
    private static String repeatChar(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Student Record Management System!");
        
        System.out.print("Load sample data for testing? (y/n): ");
        String loadSample = scanner.nextLine().trim().toLowerCase();
        if (loadSample.equals("y") || loadSample.equals("yes")) {
            initializeSampleData();
        }
        
        int choice;
        
        do {
            displayMenu();
            
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        searchStudentById();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deleteStudent();
                        break;
                    case 6:
                        displayStatistics();
                        break;
                    case 7:
                        System.out.println("\nEnd");
                        break;
                    default:
                        System.out.println("Invalid choice! Please select 1-7.");
                }
                
                if (choice != 7 && choice >= 1 && choice <= 6) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
                
            } catch (Exception e) {
                System.out.println("Error: Invalid input! Please enter a number (1-7).");
                scanner.nextLine(); 
                choice = 0; 
            }
            
        } while (choice != 7);
        
        scanner.close();
    }
}