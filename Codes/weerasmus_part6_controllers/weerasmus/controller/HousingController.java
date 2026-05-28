package weerasmus.controller;

import weerasmus.dto.AccommodationListDTO;
import weerasmus.dto.AccommodationSearchCriteria;
import weerasmus.dto.HousingApplicationDTO;
import weerasmus.dto.ValidationResult;
import weerasmus.model.Accommodation;
import weerasmus.model.HousingApplication;
import weerasmus.repository.AccommodationRepository;
import weerasmus.repository.HousingApplicationRepository;
import weerasmus.service.AccommodationSearchService;
import weerasmus.service.HousingApplicationService;
import weerasmus.ui.AccommodationDetailsFrame;
import weerasmus.ui.HousingApplicationFrame;
import weerasmus.ui.HousingApplicationsFrame;
import weerasmus.ui.HousingSearchFrame;

public class HousingController {
    private final AccommodationRepository accommodationRepository;
    private final HousingApplicationRepository housingApplicationRepository;
    private final AccommodationSearchService accommodationSearchService;
    private final HousingApplicationService housingApplicationService;

    public HousingController(AccommodationRepository accommodationRepository,
                             HousingApplicationRepository housingApplicationRepository,
                             AccommodationSearchService accommodationSearchService,
                             HousingApplicationService housingApplicationService) {
        this.accommodationRepository = accommodationRepository;
        this.housingApplicationRepository = housingApplicationRepository;
        this.accommodationSearchService = accommodationSearchService;
        this.housingApplicationService = housingApplicationService;
    }

    public void openHousingSearchFrame(int studentId) {
        AccommodationListDTO recommendations = accommodationSearchService.getRecommendedAccommodations(studentId);
        HousingSearchFrame frame = new HousingSearchFrame(this, studentId);
        frame.showFrame(recommendations);
    }

    public AccommodationListDTO searchAccommodations(AccommodationSearchCriteria filters) {
        return accommodationSearchService.searchByFilters(filters);
    }

    public void openAccommodationDetailsFrame(int accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId);
        if (accommodation != null) {
            AccommodationDetailsFrame frame = new AccommodationDetailsFrame(this);
            frame.showFrame(accommodation);
        }
    }

    public void openHousingApplicationFrame(int accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId);
        if (accommodation != null) {
            HousingApplicationFrame frame = new HousingApplicationFrame(this, accommodationId);
            frame.showFrame(accommodation);
        }
    }

    public ValidationResult submitHousingApplication(int studentId, int accommodationId,
                                                     HousingApplicationDTO applicationData) {
        ValidationResult dataResult = housingApplicationService.validateApplicationData(applicationData);
        if (!dataResult.isValid()) {
            return dataResult;
        }

        ValidationResult periodResult = housingApplicationService.validateRentalPeriod(
                applicationData.getEntryDate(),
                applicationData.getExitDate()
        );
        if (!periodResult.isValid()) {
            return periodResult;
        }

        Accommodation accommodation = accommodationRepository.findById(accommodationId);
        if (accommodation == null) {
            return ValidationResult.invalid("Η κατοικία δεν βρέθηκε.");
        }

        boolean available = accommodationRepository.isAvailable(accommodationId, accommodation.getRentalPeriod());
        if (!available) {
            return ValidationResult.invalid("Η κατοικία δεν είναι διαθέσιμη για την περίοδο που επιλέξατε.");
        }

        HousingApplication application = new HousingApplication();
        application.setStudentId(studentId);
        application.setAccommodationId(accommodationId);
        application.setPaymentMethod(applicationData.getPaymentMethod());
        application.setTotalAmount(accommodation.getMonthlyCost());
        application.submit();

        housingApplicationRepository.save(application);

        HousingApplicationsFrame frame = new HousingApplicationsFrame(this);
        frame.showFrame(studentId);

        return ValidationResult.valid();
    }
}
