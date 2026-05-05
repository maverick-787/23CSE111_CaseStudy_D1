package school.fees;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FeeManagementSystem system = new FeeManagementSystem();
        Admin admin = new Admin("A1", "School Admin", "9999999999", "Finance");
        System.out.println("Date & Time: " + getCurrentDateTime());

        boolean running = true;
        while (running) {
            System.out.println("\n=== School Fees Management ===");
            System.out.println("1. Add Student");
            System.out.println("2. Make Payment");
            System.out.println("3. View Students");
            System.out.println("4. View Payments");
            System.out.println("5. View Defaulters");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                addStudent(scanner, system);
            } else if (choice.equals("2")) {
                makePayment(scanner, system);
            } else if (choice.equals("3")) {
                viewStudents(system);
            } else if (choice.equals("4")) {
                admin.viewAllPayments(system.getPayments());
            } else if (choice.equals("5")) {
                viewDefaulters(admin, system);
            } else if (choice.equals("0")) {
                running = false;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    private static void addStudent(Scanner scanner, FeeManagementSystem system) {
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        if (system.findStudentByStudentId(studentId) != null) {
            System.out.println("Student ID already exists.");
            return;
        }

        System.out.print("Student Name: ");
        String studentName = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Class Name: ");
        String className = scanner.nextLine();
        System.out.print("Total Fee: ");
        double totalFee = readDouble(scanner);

        Student student = new Student(studentId, studentName, phone, studentId, className, totalFee);
        system.addStudent(student);
        System.out.println("Student added.");
    }

    private static void makePayment(Scanner scanner, FeeManagementSystem system) {
        System.out.print("Student ID for payment: ");
        String studentId = scanner.nextLine();
        Student student = system.findStudentByStudentId(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Parent ID: ");
        String parentId = scanner.nextLine();
        System.out.print("Parent Name: ");
        String parentName = scanner.nextLine();
        System.out.print("Parent Phone: ");
        String parentPhone = scanner.nextLine();
        Parent parent = new Parent(parentId, parentName, parentPhone, parentId, studentId);

        System.out.print("Payment ID: ");
        String paymentId = scanner.nextLine();
        System.out.print("Payment Amount: ");
        double amount = readDouble(scanner);

        Payment payment = parent.payFees(student, paymentId, amount, system.getPayments());
        if (payment != null) {
            savePaymentToFile(payment);
            System.out.println("Payment successful.");
            System.out.println(student.viewFeeDetails());
        }
    }

    private static void viewStudents(FeeManagementSystem system) {
        ArrayList<Student> students = system.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students added.");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).viewFeeDetails());
        }
    }

    private static void viewDefaulters(Admin admin, FeeManagementSystem system) {
        ArrayList<Student> defaulters = admin.trackDefaulters(system.getStudents());
        if (defaulters.isEmpty()) {
            System.out.println("No defaulters.");
            return;
        }
        for (int i = 0; i < defaulters.size(); i++) {
            System.out.println(defaulters.get(i).viewFeeDetails());
        }
    }

    private static void savePaymentToFile(Payment payment) {
        try {
            FileWriter writer = new FileWriter("payments.txt", true);
            writer.write(payment.toFileLine() + "," + getCurrentDateTime() + System.lineSeparator());
            writer.close();
            System.out.println("Saved to payments.txt");
            System.out.println("Saved at: " + getCurrentDateTime());
        } catch (IOException e) {
            System.out.println("Error saving payment: " + e.getMessage());
        }
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Enter again: ");
            }
        }
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(new Date());
    }
}
