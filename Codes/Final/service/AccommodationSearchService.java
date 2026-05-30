package weerasmus.service;

import java.util.ArrayList;
import weerasmus.dto.AccommodationListDTO;
import weerasmus.dto.AccommodationSearchCriteria;
import weerasmus.model.Accommodation;
import weerasmus.repository.AccommodationRepository;

public class AccommodationSearchService {
    private AccommodationRepository accommodationRepository;

    public AccommodationSearchService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public AccommodationListDTO getRecommendedAccommodations(int studentId) {
        ArrayList<Accommodation> accommodations = accommodationRepository.findRecommended(studentId);
        return new AccommodationListDTO(accommodations, "Προτεινόμενες κατοικίες.");
    }

    public AccommodationListDTO searchByFilters(AccommodationSearchCriteria filters) {
        ArrayList<Accommodation> accommodations = accommodationRepository.findByFilters(filters);
        String message = accommodations.isEmpty()
                ? "Δεν υπάρχει διαθέσιμη κατοικία με βάση τα φίλτρα."
                : "Βρέθηκαν διαθέσιμες κατοικίες.";
        return new AccommodationListDTO(accommodations, message);
    }
}
