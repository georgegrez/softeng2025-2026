package weerasmus.controller;

import java.io.File;
import java.util.ArrayList;
import weerasmus.dto.EligibilityResultDTO;
import weerasmus.dto.FileValidationResult;
import weerasmus.dto.PdfFile;
import weerasmus.dto.TranscriptDataDTO;
import weerasmus.dto.TranscriptSummaryDTO;
import weerasmus.dto.ValidationResult;
import weerasmus.model.EligibilityRule;
import weerasmus.model.Grade;
import weerasmus.model.Student;
import weerasmus.model.Transcript;
import weerasmus.repository.EligibilityRuleRepository;
import weerasmus.repository.StudentRepository;
import weerasmus.repository.TranscriptRepository;
import weerasmus.service.EligibilityService;
import weerasmus.service.PdfExportService;
import weerasmus.service.TranscriptAnalysisService;
import weerasmus.ui.EligibilityCheckFrame;
import weerasmus.ui.EligibilityResultFrame;

public class EligibilityController {
    private final StudentRepository studentRepository;
    private final TranscriptRepository transcriptRepository;
    private final EligibilityRuleRepository eligibilityRuleRepository;
    private final TranscriptAnalysisService transcriptAnalysisService;
    private final EligibilityService eligibilityService;
    private final PdfExportService pdfExportService;

    public EligibilityController(StudentRepository studentRepository,
                                 TranscriptRepository transcriptRepository,
                                 EligibilityRuleRepository eligibilityRuleRepository,
                                 TranscriptAnalysisService transcriptAnalysisService,
                                 EligibilityService eligibilityService,
                                 PdfExportService pdfExportService) {
        this.studentRepository = studentRepository;
        this.transcriptRepository = transcriptRepository;
        this.eligibilityRuleRepository = eligibilityRuleRepository;
        this.transcriptAnalysisService = transcriptAnalysisService;
        this.eligibilityService = eligibilityService;
        this.pdfExportService = pdfExportService;
    }

    public void openEligibilityCheckFrame(int studentId) {
        EligibilityCheckFrame frame = new EligibilityCheckFrame(this, studentId);
        frame.showFrame(studentId);
    }

    public TranscriptSummaryDTO analyzeTranscript(int studentId, File file) {
        FileValidationResult fileResult = transcriptAnalysisService.validateTranscriptFile(file);
        if (!fileResult.isValid()) {
            return null;
        }

        TranscriptDataDTO data = transcriptAnalysisService.extractTranscriptData(file);
        Transcript transcript = new Transcript();
        transcript.setStudentId(studentId);

        for (Grade grade : data.getGrades()) {
            transcript.addGrade(grade);
        }

        Transcript saved = transcriptRepository.save(transcript);

        return new TranscriptSummaryDTO(
                saved.getTranscriptId(),
                saved.getAverageGrade(),
                saved.getTotalECTS(),
                saved.getFailedCoursesCount()
        );
    }

    public void resetTranscriptUpload() {
        // The UI clears the selected file and preview.
    }

    public EligibilityResultDTO checkEligibility(int studentId, int transcriptId) {
        Student student = studentRepository.findById(studentId);
        Transcript transcript = transcriptRepository.findById(transcriptId);

        if (student == null || transcript == null) {
            return new EligibilityResultDTO(0, false, "Δεν βρέθηκαν στοιχεία φοιτητή ή βαθμολογίας.");
        }

        ArrayList<EligibilityRule> rules = eligibilityRuleRepository.findByDepartmentAndStudyLevel(
                student.getDepartment(),
                student.getStudyLevel()
        );

        EligibilityResultDTO result = eligibilityService.evaluate(student, transcript, rules);

        EligibilityResultFrame frame = new EligibilityResultFrame(this);
        frame.showFrame(result);

        return result;
    }

    public PdfFile downloadEligibilityResultPdf(int resultId) {
        return pdfExportService.generateEligibilityPdf(resultId);
    }
}
