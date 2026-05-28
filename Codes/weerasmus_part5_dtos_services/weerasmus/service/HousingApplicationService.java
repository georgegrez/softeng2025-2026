package weerasmus.service;

import java.util.Date;
import weerasmus.dto.HousingApplicationDTO;
import weerasmus.dto.ValidationResult;

public class HousingApplicationService {
    public ValidationResult validateApplicationData(HousingApplicationDTO applicationData) {
        ValidationResult result = ValidationResult.valid();

        if (applicationData == null) {
            return ValidationResult.invalid("Δεν έχουν συμπληρωθεί όλα τα απαιτούμενα στοιχεία.");
        }

        if (applicationData.getEntryDate() == null) result.addError("Η ημερομηνία εισόδου είναι υποχρεωτική.");
        if (applicationData.getExitDate() == null) result.addError("Η ημερομηνία εξόδου είναι υποχρεωτική.");
        if (applicationData.getPaymentMethod() == null || applicationData.getPaymentMethod().isBlank()) {
            result.addError("Ο τρόπος πληρωμής είναι υποχρεωτικός.");
        }

        return result;
    }

    public ValidationResult validateRentalPeriod(Date entryDate, Date exitDate) {
        if (entryDate == null || exitDate == null) {
            return ValidationResult.invalid("Οι ημερομηνίες ενοικίασης είναι υποχρεωτικές.");
        }

        if (!entryDate.before(exitDate)) {
            return ValidationResult.invalid("Η ημερομηνία εισόδου πρέπει να είναι προγενέστερη της ημερομηνίας εξόδου.");
        }

        return ValidationResult.valid();
    }
}
