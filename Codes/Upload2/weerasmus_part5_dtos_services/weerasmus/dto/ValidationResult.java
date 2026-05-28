package weerasmus.dto;

import java.util.ArrayList;

public class ValidationResult {
    private boolean valid;
    private ArrayList<String> errors;

    public ValidationResult(boolean valid) {
        this.valid = valid;
        this.errors = new ArrayList<>();
    }

    public ValidationResult(boolean valid, ArrayList<String> errors) {
        this.valid = valid;
        this.errors = errors == null ? new ArrayList<>() : errors;
    }

    public static ValidationResult valid() {
        return new ValidationResult(true);
    }

    public static ValidationResult invalid(String error) {
        ValidationResult result = new ValidationResult(false);
        result.addError(error);
        return result;
    }

    public boolean isValid() {
        return valid;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void addError(String error) {
        if (error != null && !error.isBlank()) {
            this.valid = false;
            this.errors.add(error);
        }
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public String getErrorsAsText() {
        return String.join("\n", errors);
    }
}
