package weerasmus.dto;

public class AccommodationSearchCriteria {
    private String area;
    private Double maxMonthlyCost;
    private String rentalPeriod;
    private String paymentMethod;
    private Double maxDistanceFromUniversity;
    private Boolean cohabitation;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getMaxMonthlyCost() {
        return maxMonthlyCost;
    }

    public void setMaxMonthlyCost(Double maxMonthlyCost) {
        this.maxMonthlyCost = maxMonthlyCost;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getMaxDistanceFromUniversity() {
        return maxDistanceFromUniversity;
    }

    public void setMaxDistanceFromUniversity(Double maxDistanceFromUniversity) {
        this.maxDistanceFromUniversity = maxDistanceFromUniversity;
    }

    public Boolean getCohabitation() {
        return cohabitation;
    }

    public void setCohabitation(Boolean cohabitation) {
        this.cohabitation = cohabitation;
    }
}
