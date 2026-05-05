package school.fees;

public class Payment {
    private String paymentId;
    private String studentId;
    private double amount;
    private String status;
    private String dateTime;   

    public Payment(String paymentId, String studentId, double amount, String status, String dateTime) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.amount = amount;
        this.status = status;
        this.dateTime = dateTime;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getDateTime() {  
        return dateTime;
    }

    public String toFileLine() {
        return paymentId + "," + studentId + "," + amount + "," + status + "," + dateTime;
    }

    public String toDisplayString() {
        return "Date & Time: " + dateTime
                + "\nPayment ID: " + paymentId
                + " | Student ID: " + studentId
                + " | Amount: " + amount
                + " | Status: " + status;
    }
}