package school.fees;

import java.util.ArrayList;

public class Parent extends Person {
    private String parentId;
    private String studentId;

    public Parent(String id, String name, String phone, String parentId, String studentId) {
        super(id, name, phone);
        this.parentId = parentId;
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getParentId() {
        return parentId;
    }

    public Payment payFees(Student student, String paymentId, double amount, ArrayList<Payment> payments) {
        if (!student.getStudentId().equals(studentId)) {
            System.out.println("This parent is not linked to that student.");
            return null;
        }
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return null;
        }
        student.payFee(amount);
        Payment payment = new Payment(paymentId, studentId, amount, "PAID");
        payments.add(payment);
        return payment;
    }
}
