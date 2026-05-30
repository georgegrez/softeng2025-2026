package weerasmus.service;

import java.util.ArrayList;
import weerasmus.dto.EligibilityResultDTO;
import weerasmus.model.EligibilityRule;
import weerasmus.model.Student;
import weerasmus.model.Transcript;

public class EligibilityService {
    public EligibilityResultDTO evaluate(Student student, Transcript transcript, ArrayList<EligibilityRule> rules) {
        if (student == null || transcript == null) {
            return new EligibilityResultDTO(0, false, "Δεν υπάρχουν επαρκή στοιχεία για τον έλεγχο.");
        }

        if (rules == null || rules.isEmpty()) {
            return new EligibilityResultDTO(1, true, "Δεν υπάρχουν ειδικοί κανόνες. Ο φοιτητής θεωρείται επιλέξιμος.");
        }

        for (EligibilityRule rule : rules) {
            if (!rule.isSatisfiedBy(student, transcript)) {
                return new EligibilityResultDTO(1, false, "Ο φοιτητής δεν πληροί όλες τις προϋποθέσεις.");
            }
        }

        return new EligibilityResultDTO(1, true, "Ο φοιτητής πληροί τις προϋποθέσεις.");
    }
}
