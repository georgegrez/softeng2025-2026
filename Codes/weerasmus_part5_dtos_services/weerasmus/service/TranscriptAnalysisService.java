package weerasmus.service;

import java.io.File;
import weerasmus.dto.FileValidationResult;
import weerasmus.dto.TranscriptDataDTO;

public class TranscriptAnalysisService {
    private FileValidationService fileValidationService;

    public TranscriptAnalysisService(FileValidationService fileValidationService) {
        this.fileValidationService = fileValidationService;
    }

    public FileValidationResult validateTranscriptFile(File file) {
        return fileValidationService.validateFileTypeAndSize(file);
    }

    public TranscriptDataDTO extractTranscriptData(File file) {
        // Mock ανάλυση: σε πραγματική εφαρμογή εδώ θα γινόταν parsing του αρχείου.
        return new TranscriptDataDTO();
    }
}
