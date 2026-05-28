package weerasmus.service;

import java.io.File;
import weerasmus.dto.FileValidationResult;

public class FileValidationService {
    private static final long MAX_FILE_SIZE_BYTES = 5 * 1024 * 1024;

    public FileValidationResult validateFileTypeAndSize(File file) {
        if (file == null || !file.exists()) {
            return FileValidationResult.invalidType();
        }

        if (file.length() > MAX_FILE_SIZE_BYTES) {
            return FileValidationResult.invalidSize();
        }

        String name = file.getName().toLowerCase();
        boolean allowed = name.endsWith(".pdf")
                || name.endsWith(".png")
                || name.endsWith(".jpg")
                || name.endsWith(".jpeg");

        if (!allowed) {
            return FileValidationResult.invalidType();
        }

        return FileValidationResult.valid(file.getAbsolutePath());
    }
}
