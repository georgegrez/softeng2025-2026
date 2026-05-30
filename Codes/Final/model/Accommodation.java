package weerasmus.model;

import java.util.ArrayList;

public class Accommodation {
    private int accommodationId;
    private String title;
    private String type;
    private String area;
    private double monthlyCost;
    private String rentalPeriod;
    private String paymentMethod;
    private double distanceFromUniversity;
    private boolean cohabitation;
    private String hostInfo;
    private ArrayList<String> amenities;
    private ArrayList<String> photos;
    private String availabilityStatus;

    public Accommodation() {
        this.amenities = new ArrayList<>();
        this.photos = new ArrayList<>();
        this.availabilityStatus = "AVAILABLE";
    }

    public Accommodation(int accommodationId, String title, String type, String area,
                         double monthlyCost, String rentalPeriod, String paymentMethod,
                         double distanceFromUniversity, boolean cohabitation,
                         String hostInfo, String availabilityStatus) {
        this.accommodationId = accommodationId;
        this.title = title;
        this.type = type;
        this.area = area;
        this.monthlyCost = monthlyCost;
        this.rentalPeriod = rentalPeriod;
        this.paymentMethod = paymentMethod;
        this.distanceFromUniversity = distanceFromUniversity;
        this.cohabitation = cohabitation;
        this.hostInfo = hostInfo;
        this.availabilityStatus = availabilityStatus;
        this.amenities = new ArrayList<>();
        this.photos = new ArrayList<>();
    }

    public boolean isAvailableFor(String requestedPeriod) {
        if (requestedPeriod == null || requestedPeriod.isBlank()) {
            return false;
        }
        return "AVAILABLE".equalsIgnoreCase(availabilityStatus)
                && (rentalPeriod == null || rentalPeriod.isBlank()
                || rentalPeriod.equalsIgnoreCase(requestedPeriod));
    }

    public double calculateTotalCost(int months) {
        if (months <= 0) {
            return 0.0;
        }
        return monthlyCost * months;
    }

    public boolean matchesFilters(String area, Double maxMonthlyCost, Boolean cohabitation) {
        boolean areaMatches = area == null || area.isBlank() || this.area.equalsIgnoreCase(area);
        boolean costMatches = maxMonthlyCost == null || this.monthlyCost <= maxMonthlyCost;
        boolean cohabitationMatches = cohabitation == null || this.cohabitation == cohabitation;
        return areaMatches && costMatches && cohabitationMatches;
    }

    public void addAmenity(String amenity) {
        if (amenity != null && !amenity.isBlank()) {
            amenities.add(amenity);
        }
    }

    public void addPhoto(String photoPath) {
        if (photoPath != null && !photoPath.isBlank()) {
            photos.add(photoPath);
        }
    }

    public int getAccommodationId() { return accommodationId; }
    public void setAccommodationId(int accommodationId) { this.accommodationId = accommodationId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public double getMonthlyCost() { return monthlyCost; }
    public void setMonthlyCost(double monthlyCost) { this.monthlyCost = monthlyCost; }
    public String getRentalPeriod() { return rentalPeriod; }
    public void setRentalPeriod(String rentalPeriod) { this.rentalPeriod = rentalPeriod; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public double getDistanceFromUniversity() { return distanceFromUniversity; }
    public void setDistanceFromUniversity(double distanceFromUniversity) { this.distanceFromUniversity = distanceFromUniversity; }
    public boolean isCohabitation() { return cohabitation; }
    public void setCohabitation(boolean cohabitation) { this.cohabitation = cohabitation; }
    public String getHostInfo() { return hostInfo; }
    public void setHostInfo(String hostInfo) { this.hostInfo = hostInfo; }
    public ArrayList<String> getAmenities() { return amenities; }
    public void setAmenities(ArrayList<String> amenities) { this.amenities = amenities; }
    public ArrayList<String> getPhotos() { return photos; }
    public void setPhotos(ArrayList<String> photos) { this.photos = photos; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; }

    @Override
    public String toString() {
        return title + " - " + area + " (" + monthlyCost + "€/μήνα)";
    }
}
