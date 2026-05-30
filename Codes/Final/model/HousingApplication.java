package weerasmus.model;

import java.util.Date;

public class HousingApplication {
    private int housingApplicationId;
    private int studentId;
    private int accommodationId;
    private Date submissionDate;
    private ApplicationStatus status;
    private double totalAmount;
    private String paymentMethod;
    private String rentalPeriod;

    public HousingApplication() {
        this.submissionDate = new Date();
        this.status = ApplicationStatus.PENDING;
    }

    public HousingApplication(int housingApplicationId, int studentId, int accommodationId,
                              double totalAmount, String paymentMethod, String rentalPeriod) {
        this.housingApplicationId = housingApplicationId;
        this.studentId = studentId;
        this.accommodationId = accommodationId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.rentalPeriod = rentalPeriod;
        this.submissionDate = new Date();
        this.status = ApplicationStatus.PENDING;
    }

    public void submit() {
        this.submissionDate = new Date();
        this.status = ApplicationStatus.PENDING;
    }

    public void approve() {
        this.status = ApplicationStatus.APPROVED;
    }

    public void reject() {
        this.status = ApplicationStatus.REJECTED;
    }

    public void cancel() {
        if (status == ApplicationStatus.PENDING) {
            this.status = ApplicationStatus.CANCELLED;
        }
    }

    public boolean isPending() {
        return status == ApplicationStatus.PENDING;
    }

    public int getHousingApplicationId() { return housingApplicationId; }
    public void setHousingApplicationId(int housingApplicationId) { this.housingApplicationId = housingApplicationId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public int getAccommodationId() { return accommodationId; }
    public void setAccommodationId(int accommodationId) { this.accommodationId = accommodationId; }
    public Date getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(Date submissionDate) { this.submissionDate = submissionDate; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getRentalPeriod() { return rentalPeriod; }
    public void setRentalPeriod(String rentalPeriod) { this.rentalPeriod = rentalPeriod; }
}
