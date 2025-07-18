import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter roll number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    if (name.isEmpty() || grade.isEmpty() || email.isEmpty()) {
                        System.out.println("Fields cannot be empty.");
                    } else {
                        sms.addStudent(new Student(name, roll, grade, email));
                    }
                }
                case 2 -> {
                    System.out.print("Enter roll number of student to edit: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new grade: ");
                    String grade = sc.nextLine();
                    System.out.print("Enter new email: ");
                    String email = sc.nextLine();
                    if (name.isEmpty() || grade.isEmpty() || email.isEmpty()) {
                        System.out.println("Fields cannot be empty.");
                    } else {
                        sms.updateStudent(roll, name, grade, email);
                    }
                }
                case 3 -> {
                    System.out.print("Enter roll number to remove: ");
                    int roll = sc.nextInt();
                    sms.removeStudent(roll);
                }
                case 4 -> {
                    System.out.print("Enter roll number to search: ");
                    int roll = sc.nextInt();
                    Student student = sms.getStudentByRoll(roll);
                    if (student != null) {
                        System.out.println("Student Found: " + student);
                    } else {
                        System.out.println("Student not found.");
                    }
                }
                case 5 -> sms.displayAllStudents();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }
}
