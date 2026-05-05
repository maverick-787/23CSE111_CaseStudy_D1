package school.fees;

import java.util.ArrayList;

public class Admin extends Person {
    private String role;

    public Admin(String id, String name, String phone, String role) {
        super(id, name, phone);
        this.role = role;
    }

    public ArrayList<Student> trackDefaulters(ArrayList<Student> students) {
        ArrayList<Student> defaulters = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getDueAmount() > 0) {
                defaulters.add(student);
            }
        }
        return defaulters;
    }

    public void viewAllPayments(ArrayList<Payment> payments) {
        if (payments.isEmpty()) {
            System.out.println("No payments recorded yet.");
            return;
        }
        for (int i = 0; i < payments.size(); i++) {
            System.out.println(payments.get(i).toDisplayString());
        }
    }
}
