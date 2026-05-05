package school.fees;

import java.util.ArrayList;

public class FeeManagementSystem {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Payment> payments = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public Student findStudentByStudentId(String studentId) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}
