package weerasmus.repository;

import java.util.ArrayList;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.Accommodation;

public class AccommodationRepository {

    public ArrayList<Accommodation> findAll() {
        return new ArrayList<>(InMemoryDatabase.accommodations);
    }

    public Accommodation findById(int accommodationId) {
        for (Accommodation accommodation : InMemoryDatabase.accommodations) {
            if (accommodation.getAccommodationId() == accommodationId) {
                return accommodation;
            }
        }
        return null;
    }

    public Accommodation save(Accommodation accommodation) {
        if (accommodation == null) {
            return null;
        }

        if (accommodation.getAccommodationId() == 0) {
            accommodation.setAccommodationId(InMemoryDatabase.generateAccommodationId());
            InMemoryDatabase.accommodations.add(accommodation);
            return accommodation;
        }

        Accommodation existing = findById(accommodation.getAccommodationId());
        if (existing == null) {
            InMemoryDatabase.accommodations.add(accommodation);
        }

        return accommodation;
    }

    public ArrayList<Accommodation> findRecommended(int studentId) {
        return findAll();
    }

    public ArrayList<Accommodation> findByFilters(String area, Double maxMonthlyCost,
                                                  Boolean cohabitation, String rentalPeriod) {
        ArrayList<Accommodation> results = new ArrayList<>();

        for (Accommodation accommodation : InMemoryDatabase.accommodations) {
            boolean matchesArea = area == null || area.isBlank()
                    || accommodation.getArea().equalsIgnoreCase(area);

            boolean matchesCost = maxMonthlyCost == null
                    || accommodation.getMonthlyCost() <= maxMonthlyCost;

            boolean matchesCohabitation = cohabitation == null
                    || accommodation.isCohabitation() == cohabitation;

            boolean matchesPeriod = rentalPeriod == null || rentalPeriod.isBlank()
                    || accommodation.isAvailableFor(rentalPeriod);

            if (matchesArea && matchesCost && matchesCohabitation && matchesPeriod) {
                results.add(accommodation);
            }
        }

        return results;
    }

    public boolean isAvailable(int accommodationId, String rentalPeriod) {
        Accommodation accommodation = findById(accommodationId);
        return accommodation != null && accommodation.isAvailableFor(rentalPeriod);
    }
}
