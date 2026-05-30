package weerasmus.model;

import java.util.Date;

public class Installment extends Payment {
    private int installmentNumber;
    private Date dueDate;
    private double installmentAmount;
    private boolean paid;

    public Installment() {
        super();
    }

    public Installment(int paymentId, int studentId, int installmentNumber,
                       Date dueDate, double installmentAmount, String paymentMethod) {
        super(paymentId, studentId, installmentAmount, paymentMethod);
        this.installmentNumber = installmentNumber;
        this.dueDate = dueDate;
        this.installmentAmount = installmentAmount;
        this.paid = false;
    }

    public void markAsPaid() {
        if (pay()) {
            this.paid = true;
        }
    }

    public boolean isOverdue(Date currentDate) {
        return !paid && dueDate != null && currentDate != null && currentDate.after(dueDate);
    }

    public int getInstallmentNumber() { return installmentNumber; }
    public void setInstallmentNumber(int installmentNumber) { this.installmentNumber = installmentNumber; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public double getInstallmentAmount() { return installmentAmount; }
    public void setInstallmentAmount(double installmentAmount) { this.installmentAmount = installmentAmount; }
    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }
}
