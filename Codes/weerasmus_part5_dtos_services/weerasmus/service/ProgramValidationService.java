package weerasmus.service;

import java.util.Date;
import weerasmus.dto.ErasmusProgramDTO;
import weerasmus.dto.ProgramBasicInfoDTO;
import weerasmus.dto.ProgramTimeInfoDTO;
import weerasmus.dto.ValidationResult;

public class ProgramValidationService {
    public ValidationResult validateBasicInfo(ProgramBasicInfoDTO basicInfo) {
        ValidationResult result = ValidationResult.valid();

        if (basicInfo == null) {
            return ValidationResult.invalid("Δεν έχουν συμπληρωθεί τα βασικά στοιχεία.");
        }

        if (isBlank(basicInfo.getUniversityName())) result.addError("Το πανεπιστήμιο είναι υποχρεωτικό.");
        if (isBlank(basicInfo.getDepartment())) result.addError("Το τμήμα είναι υποχρεωτικό.");
        if (isBlank(basicInfo.getCountry())) result.addError("Η χώρα είναι υποχρεωτική.");
        if (basicInfo.getStudyLevel() == null) result.addError("Το επίπεδο σπουδών είναι υποχρεωτικό.");

        return result;
    }

    public ValidationResult validateTimeInfo(ProgramTimeInfoDTO timeInfo) {
        ValidationResult result = ValidationResult.valid();

        if (timeInfo == null) {
            return ValidationResult.invalid("Δεν έχουν συμπληρωθεί οι χρονικές πληροφορίες.");
        }

        if (timeInfo.getDuration() <= 0) result.addError("Η διάρκεια πρέπει να είναι θετική.");
        if (isBlank(timeInfo.getPeriod())) result.addError("Η χρονική περίοδος είναι υποχρεωτική.");
        if (timeInfo.getApplicationDeadline() == null) {
            result.addError("Η προθεσμία υποβολής αίτησης είναι υποχρεωτική.");
        } else if (timeInfo.getApplicationDeadline().before(new Date())) {
            result.addError("Η προθεσμία υποβολής αίτησης δεν μπορεί να είναι προγενέστερη της σημερινής ημερομηνίας.");
        }

        return result;
    }

    public ValidationResult validateProgramData(ErasmusProgramDTO programData) {
        ValidationResult result = ValidationResult.valid();

        if (programData == null) {
            return ValidationResult.invalid("Τα στοιχεία του προγράμματος δεν είναι έγκυρα.");
        }

        if (isBlank(programData.getUniversityName())) result.addError("Το πανεπιστήμιο είναι υποχρεωτικό.");
        if (isBlank(programData.getDepartment())) result.addError("Το τμήμα είναι υποχρεωτικό.");
        if (programData.getAvailablePositions() <= 0) result.addError("Οι διαθέσιμες θέσεις πρέπει να είναι θετικές.");

        return result;
    }

    public ValidationResult validateDeadline(Date newDeadline) {
        if (newDeadline == null || newDeadline.before(new Date())) {
            return ValidationResult.invalid("Η νέα προθεσμία δεν μπορεί να είναι προγενέστερη της σημερινής ημερομηνίας.");
        }
        return ValidationResult.valid();
    }

    public ValidationResult validateAvailablePositions(int newAvailablePositions, int approvedCount) {
        if (newAvailablePositions < approvedCount) {
            return ValidationResult.invalid("Οι διαθέσιμες θέσεις δεν μπορούν να είναι λιγότερες από τις ήδη εγκεκριμένες αιτήσεις.");
        }
        return ValidationResult.valid();
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
