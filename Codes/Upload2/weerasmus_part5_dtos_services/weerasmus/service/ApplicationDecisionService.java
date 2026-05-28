package weerasmus.service;

import java.util.ArrayList;
import weerasmus.dto.ValidationResult;
import weerasmus.model.ErasmusProgram;

public class ApplicationDecisionService {
    public ValidationResult validateApprovalCapacity(ErasmusProgram program, int approvedCount,
                                                     ArrayList<Integer> applicationIds) {
        if (program == null) {
            return ValidationResult.invalid("Το πρόγραμμα δεν βρέθηκε.");
        }

        int selectedCount = applicationIds == null ? 0 : applicationIds.size();
        int finalApprovedCount = approvedCount + selectedCount;

        if (finalApprovedCount > program.getAvailablePositions()) {
            return ValidationResult.invalid("Έχει συμπληρωθεί ο μέγιστος αριθμός εγκεκριμένων αιτήσεων.");
        }

        return ValidationResult.valid();
    }
}
