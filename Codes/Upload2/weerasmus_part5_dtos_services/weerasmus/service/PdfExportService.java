package weerasmus.service;

import weerasmus.dto.PdfFile;

public class PdfExportService {
    public PdfFile generateEligibilityPdf(int resultId) {
        // Mock PDF export για desktop εργασία.
        String fileName = "eligibility-result-" + resultId + ".pdf";
        String filePath = "./exports/" + fileName;
        return new PdfFile(fileName, filePath);
    }
}
