package weerasmus.service;

import weerasmus.dto.StudentEvaluationDTO;
import weerasmus.dto.ValidationResult;

public class StudentEvaluationService {
    public ValidationResult validateEvaluation(StudentEvaluationDTO evaluationData) {
        ValidationResult result = ValidationResult.valid();

        if (evaluationData == null) {
            return ValidationResult.invalid("Η αξιολόγηση δεν έχει συμπληρωθεί.");
        }

        if (evaluationData.getCriteriaScores() == null || evaluationData.getCriteriaScores().isEmpty()) {
            result.addError("Πρέπει να συμπληρωθούν τα κριτήρια αξιολόγησης.");
        }

        if (evaluationData.getComments() == null || evaluationData.getComments().isBlank()) {
            result.addError("Το συνοδευτικό κείμενο είναι υποχρεωτικό.");
        }

        return result;
    }
}
