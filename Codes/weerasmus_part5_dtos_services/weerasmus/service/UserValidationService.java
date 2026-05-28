package weerasmus.service;

import weerasmus.dto.ValidationResult;
import weerasmus.model.Student;

public class UserValidationService {
    public ValidationResult validateRequiredFields(Student student) {
        ValidationResult result = ValidationResult.valid();

        if (student == null) {
            result.addError("Δεν έχουν συμπληρωθεί τα στοιχεία του φοιτητή.");
            return result;
        }

        if (isBlank(student.getFirstName())) result.addError("Το όνομα είναι υποχρεωτικό.");
        if (isBlank(student.getLastName())) result.addError("Το επώνυμο είναι υποχρεωτικό.");
        if (isBlank(student.getUsername())) result.addError("Το username είναι υποχρεωτικό.");
        if (isBlank(student.getPassword())) result.addError("Το password είναι υποχρεωτικό.");
        if (isBlank(student.getEmail())) result.addError("Το email είναι υποχρεωτικό.");
        if (isBlank(student.getRegistrationNumber())) result.addError("Ο αριθμός μητρώου είναι υποχρεωτικός.");
        if (isBlank(student.getDepartment())) result.addError("Το τμήμα είναι υποχρεωτικό.");
        if (student.getStudyLevel() == null) result.addError("Το επίπεδο σπουδών είναι υποχρεωτικό.");

        return result;
    }

    public ValidationResult validateTermsAccepted(boolean acceptedTerms) {
        return acceptedTerms
                ? ValidationResult.valid()
                : ValidationResult.invalid("Πρέπει να αποδεχτείτε τους όρους χρήσης.");
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
