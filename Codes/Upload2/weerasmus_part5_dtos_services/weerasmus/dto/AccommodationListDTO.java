package weerasmus.dto;

import java.util.ArrayList;
import weerasmus.model.Accommodation;

public class AccommodationListDTO {
    private ArrayList<Accommodation> accommodations;
    private String message;

    public AccommodationListDTO() {
        this.accommodations = new ArrayList<>();
    }

    public AccommodationListDTO(ArrayList<Accommodation> accommodations, String message) {
        this.accommodations = accommodations == null ? new ArrayList<>() : accommodations;
        this.message = message;
    }

    public boolean hasResults() {
        return accommodations != null && !accommodations.isEmpty();
    }

    public ArrayList<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(ArrayList<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    public String getMessage() {
        return message;
    }
}
