package weerasmus.service;

import java.io.File;
import java.util.ArrayList;
import weerasmus.dto.FileValidationResult;
import weerasmus.dto.TranscriptDataDTO;
import weerasmus.model.Course;
import weerasmus.model.Grade;

public class TranscriptAnalysisService {
    private FileValidationService fileValidationService;

    public TranscriptAnalysisService(FileValidationService fileValidationService) {
        this.fileValidationService = fileValidationService;
    }

    public FileValidationResult validateTranscriptFile(File file) {
        return fileValidationService.validateFileTypeAndSize(file);
    }

    public TranscriptDataDTO extractTranscriptData(File file) {
        // Demo implementation for the desktop prototype.
        // It returns sample parsed grades so the eligibility sequence can run end-to-end.
        ArrayList<Grade> grades = new ArrayList<>();

        Course course1 = new Course(101, "CEID101", "Αντικειμενοστρεφής Προγραμματισμός",
                6, "OOP", "Μηχανικών Η/Υ και Πληροφορικής", "0-10");
        Course course2 = new Course(102, "CEID102", "Βάσεις Δεδομένων",
                6, "Databases", "Μηχανικών Η/Υ και Πληροφορικής", "0-10");
        Course course3 = new Course(103, "CEID103", "Δίκτυα Υπολογιστών",
                6, "Networks", "Μηχανικών Η/Υ και Πληροφορικής", "0-10");

        grades.add(new Grade(1, course1, 8.0, "0-10", "Χειμερινό", "2024-2025"));
        grades.add(new Grade(2, course2, 7.5, "0-10", "Εαρινό", "2024-2025"));
        grades.add(new Grade(3, course3, 6.5, "0-10", "Εαρινό", "2024-2025"));

        return new TranscriptDataDTO(grades);
    }
}
