package weerasmus.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import weerasmus.dto.PdfFile;

public class PdfExportService {
    public PdfFile generateEligibilityPdf(int resultId) {
        File directory = new File("exports");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = "eligibility-result-" + resultId + ".pdf";
        File file = new File(directory, fileName);

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            String minimalPdf = "%PDF-1.4\n"
                    + "1 0 obj << /Type /Catalog /Pages 2 0 R >> endobj\n"
                    + "2 0 obj << /Type /Pages /Kids [3 0 R] /Count 1 >> endobj\n"
                    + "3 0 obj << /Type /Page /Parent 2 0 R /MediaBox [0 0 612 792] "
                    + "/Contents 4 0 R /Resources << >> >> endobj\n"
                    + "4 0 obj << /Length 72 >> stream\n"
                    + "BT /F1 12 Tf 72 720 Td (WeErasmus Eligibility Result ID: " + resultId + ") Tj ET\n"
                    + "endstream endobj\n"
                    + "xref\n"
                    + "0 5\n"
                    + "0000000000 65535 f \n"
                    + "trailer << /Root 1 0 R /Size 5 >>\n"
                    + "startxref\n"
                    + "0\n"
                    + "%%EOF";
            outputStream.write(minimalPdf.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            return null;
        }

        return new PdfFile(fileName, file.getAbsolutePath());
    }
}
