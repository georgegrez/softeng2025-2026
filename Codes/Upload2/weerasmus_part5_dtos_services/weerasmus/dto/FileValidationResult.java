package weerasmus.dto;

public class FileValidationResult extends ValidationResult {
    private String filePath;

    private FileValidationResult(boolean valid, String filePath) {
        super(valid);
        this.filePath = filePath;
    }

    public static FileValidationResult valid(String filePath) {
        return new FileValidationResult(true, filePath);
    }

    public static FileValidationResult invalidSize() {
        FileValidationResult result = new FileValidationResult(false, null);
        result.addError("Το μέγεθος του αρχείου είναι μεγαλύτερο από το επιτρεπτό.");
        return result;
    }

    public static FileValidationResult invalidType() {
        FileValidationResult result = new FileValidationResult(false, null);
        result.addError("Ο τύπος του αρχείου δεν είναι αποδεκτός.");
        return result;
    }

    public String getFilePath() {
        return filePath;
    }
}
