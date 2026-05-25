package weerasmus.model;

import java.util.Date;

public class Payment {
    private int paymentId;
    private int studentId;
    private double amount;
    private Date paymentDate;
    private String paymentMethod;
    private PaymentStatus status;
    private String receiptNumber;

    public Payment() {
        this.status = PaymentStatus.PENDING;
    }

    public Payment(int paymentId, int studentId, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = PaymentStatus.PENDING;
    }

    public boolean pay() {
        if (amount <= 0) {
            this.status = PaymentStatus.FAILED;
            return false;
        }
        this.paymentDate = new Date();
        this.status = PaymentStatus.PAID;
        this.receiptNumber = generateReceipt();
        return true;
    }

    public void cancel() {
        if (status == PaymentStatus.PENDING) {
            this.status = PaymentStatus.CANCELLED;
        }
    }

    public String generateReceipt() {
        return "REC-" + paymentId + "-" + System.currentTimeMillis();
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    public String getReceiptNumber() { return receiptNumber; }
    public void setReceiptNumber(String receiptNumber) { this.receiptNumber = receiptNumber; }
}
