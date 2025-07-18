import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    private List<Student> students;
    private final String filePath = "students.txt";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        if (getStudentByRoll(student.getRollNumber()) == null) {
            students.add(student);
            saveToFile();
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student with this roll number already exists.");
        }
    }

    public void removeStudent(int rollNumber) {
        Student student = getStudentByRoll(rollNumber);
        if (student != null) {
            students.remove(student);
            saveToFile();
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public Student getStudentByRoll(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) return s;
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public void updateStudent(int rollNumber, String newName, String newGrade, String newEmail) {
        Student student = getStudentByRoll(rollNumber);
        if (student != null) {
            student.setName(newName);
            student.setGrade(newGrade);
            student.setEmail(newEmail);
            saveToFile();
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Student s : students) {
                writer.println(s.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    private void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                students.add(Student.fromCSV(scanner.nextLine()));
            }
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }
    }
}
