package weerasmus.controller;

import java.util.ArrayList;
import java.util.Date;
import weerasmus.dto.MessageDTO;
import weerasmus.dto.ParticipationResultDTO;
import weerasmus.dto.StudentEvaluationDTO;
import weerasmus.dto.StudentProgressDTO;
import weerasmus.dto.ValidationResult;
import weerasmus.model.ErasmusProgram;
import weerasmus.model.ProgramApplication;
import weerasmus.model.Student;
import weerasmus.model.StudentEvaluation;
import weerasmus.repository.ErasmusProgramRepository;
import weerasmus.repository.ProgramApplicationRepository;
import weerasmus.repository.StudentEvaluationRepository;
import weerasmus.repository.StudentRepository;
import weerasmus.service.ApplicationDecisionService;
import weerasmus.service.EmailService;
import weerasmus.service.MessageService;
import weerasmus.service.ParticipationService;
import weerasmus.service.ProgramValidationService;
import weerasmus.service.StudentEvaluationService;
import weerasmus.ui.ConfirmSuspendDialog;
import weerasmus.ui.EditProgramFrame;
import weerasmus.ui.ManageProgramsFrame;
import weerasmus.ui.ProgramApplicationsFrame;
import weerasmus.ui.ProgramProgressFrame;
import weerasmus.ui.SendMessageDialog;
import weerasmus.ui.StudentEvaluationFrame;
import weerasmus.ui.StudentProgressFrame;

public class ProfessorController {
    private final ErasmusProgramRepository erasmusProgramRepository;
    private final ProgramApplicationRepository programApplicationRepository;
    private final StudentRepository studentRepository;
    private final StudentEvaluationRepository studentEvaluationRepository;
    private final MessageService messageService;
    private final StudentEvaluationService studentEvaluationService;
    private final ParticipationService participationService;
    private final ProgramValidationService programValidationService;
    private final ApplicationDecisionService applicationDecisionService;
    private final EmailService emailService;

    public ProfessorController(ErasmusProgramRepository erasmusProgramRepository,
                               ProgramApplicationRepository programApplicationRepository,
                               StudentRepository studentRepository,
                               StudentEvaluationRepository studentEvaluationRepository,
                               MessageService messageService,
                               StudentEvaluationService studentEvaluationService,
                               ParticipationService participationService,
                               ProgramValidationService programValidationService,
                               ApplicationDecisionService applicationDecisionService,
                               EmailService emailService) {
        this.erasmusProgramRepository = erasmusProgramRepository;
        this.programApplicationRepository = programApplicationRepository;
        this.studentRepository = studentRepository;
        this.studentEvaluationRepository = studentEvaluationRepository;
        this.messageService = messageService;
        this.studentEvaluationService = studentEvaluationService;
        this.participationService = participationService;
        this.programValidationService = programValidationService;
        this.applicationDecisionService = applicationDecisionService;
        this.emailService = emailService;
    }

    public void openProgramProgressFrame(int professorId) {
        ProgramProgressFrame frame = new ProgramProgressFrame(this, professorId);
        frame.showFrame(professorId);
    }

    public ArrayList<Student> getActiveStudentsForProgram(int professorId, int programId) {
        return studentRepository.findActiveByProgram(programId);
    }

    public void openStudentProgressFrame(int programId, int studentId) {
        StudentProgressDTO progress = studentRepository.findProgressByProgram(studentId, programId);
        StudentProgressFrame frame = new StudentProgressFrame(this);
        frame.showFrame(progress);
    }

    public void openSendMessageDialog(int studentId) {
        SendMessageDialog dialog = new SendMessageDialog(this, studentId);
        dialog.showDialog(studentId);
    }

    public MessageDTO sendMessage(int professorId, int studentId, String content) {
        return messageService.createMessage(professorId, studentId, content);
    }

    public void cancelSendMessage() {
        // Dialog closes itself.
    }

    public void openStudentEvaluationFrame(int studentId) {
        StudentEvaluationFrame frame = new StudentEvaluationFrame(this, studentId);
        frame.showFrame(studentId);
    }

    public void storeAdditionalEvaluationDetails(StudentEvaluationDTO evaluationData, String extraDetails) {
        if (evaluationData != null) {
            evaluationData.setAdditionalDetails(extraDetails);
        }
    }

    public ValidationResult evaluateStudent(int professorId, int studentId, StudentEvaluationDTO evaluationData) {
        ValidationResult validation = studentEvaluationService.validateEvaluation(evaluationData);
        if (!validation.isValid()) {
            return validation;
        }

        StudentEvaluation evaluation = new StudentEvaluation();
        evaluation.setProfessorId(professorId);
        evaluation.setStudentId(studentId);
        evaluation.setCriteriaScores(evaluationData.getCriteriaScores());
        evaluation.setComments(evaluationData.getComments());
        evaluation.submit();

        studentEvaluationRepository.save(evaluation);
        return ValidationResult.valid();
    }

    public void cancelEvaluation() {
        // UI returns to StudentProgressFrame.
    }

    public void openConfirmSuspendDialog(int studentId, int programId) {
        ConfirmSuspendDialog dialog = new ConfirmSuspendDialog(this, studentId, programId);
        dialog.showDialog(studentId, programId);
    }

    public ParticipationResultDTO suspendStudentParticipation(int professorId, int studentId, int programId) {
        ParticipationResultDTO result = participationService.suspendParticipation(studentId, programId);
        if (result.isSuccess()) {
            emailService.sendParticipationSuspensionEmail(studentId, programId);
        }
        return result;
    }

    public void cancelSuspension() {
        // Dialog closes itself.
    }

    public void openManageProgramsFrame(int professorId) {
        ArrayList<ErasmusProgram> programs = erasmusProgramRepository.findActiveProgramsByProfessor(professorId);
        ManageProgramsFrame frame = new ManageProgramsFrame(this, professorId);
        frame.showFrame(programs);
    }

    public void openEditProgramFrame(int professorId, int programId) {
        ErasmusProgram program = erasmusProgramRepository.findManagedProgram(professorId, programId);
        if (program != null) {
            EditProgramFrame frame = new EditProgramFrame(this, professorId);
            frame.showFrame(program);
        }
    }

    public ValidationResult validateApplicationDeadline(int programId, Date newDeadline) {
        return programValidationService.validateDeadline(newDeadline);
    }

    public ValidationResult updateProgram(int programId, Date newDeadline, int newAvailablePositions) {
        int approvedCount = programApplicationRepository.countApprovedByProgram(programId);
        ValidationResult validation = programValidationService.validateAvailablePositions(newAvailablePositions, approvedCount);
        if (!validation.isValid()) {
            return validation;
        }

        erasmusProgramRepository.updateProgram(programId, newDeadline, newAvailablePositions);
        return ValidationResult.valid();
    }

    public void openProgramApplicationsFrame(int programId) {
        ArrayList<ProgramApplication> applications = programApplicationRepository.findByProgram(programId);
        ProgramApplicationsFrame frame = new ProgramApplicationsFrame(this, programId);
        frame.showFrame(applications);
    }

    public ValidationResult approveApplications(int programId, ArrayList<Integer> applicationIds) {
        ErasmusProgram program = erasmusProgramRepository.findById(programId);
        int approvedCount = programApplicationRepository.countApprovedByProgram(programId);

        ValidationResult validation = applicationDecisionService.validateApprovalCapacity(program, approvedCount, applicationIds);
        if (!validation.isValid()) {
            return validation;
        }

        programApplicationRepository.updateStatus(applicationIds, "APPROVED");
        emailService.sendApplicationDecisionEmails(applicationIds, "APPROVED");

        return ValidationResult.valid();
    }
}
