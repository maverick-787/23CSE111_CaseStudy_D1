package school.fees;

public class Student extends Person {
    private String studentId;
    private String className;
    private double totalFee;
    private double paidAmount;

    public Student(String id, String name, String phone, String studentId, String className, double totalFee) {
        super(id, name, phone);
        this.studentId = studentId;
        this.className = className;
        this.totalFee = totalFee;
        this.paidAmount = 0;
    }

    public String getStudentId() {
        return studentId;
    }

    public double getDueAmount() {
        return totalFee - paidAmount;
    }

    public void payFee(double amount) {
        paidAmount += amount;
        if (paidAmount > totalFee) {
            paidAmount = totalFee;
        }
    }

    public String viewFeeDetails() {
        return "Student ID: " + studentId
                + " | Name: " + name
                + " | Class: " + className
                + " | Total: " + totalFee
                + " | Paid: " + paidAmount
                + " | Due: " + getDueAmount();
    }
}
