package weerasmus.controller;

import java.io.File;
import weerasmus.dto.FileValidationResult;
import weerasmus.dto.ValidationResult;
import weerasmus.model.Student;
import weerasmus.repository.StudentRepository;
import weerasmus.service.EmailService;
import weerasmus.service.FileValidationService;
import weerasmus.service.UserValidationService;
import weerasmus.ui.LoginFrame;
import weerasmus.ui.RegisterFrame;
import weerasmus.ui.TermsOfUseDialog;

public class AuthController {
    private final StudentRepository studentRepository;
    private final FileValidationService fileValidationService;
    private final UserValidationService userValidationService;
    private final EmailService emailService;

    public AuthController(StudentRepository studentRepository,
                          FileValidationService fileValidationService,
                          UserValidationService userValidationService,
                          EmailService emailService) {
        this.studentRepository = studentRepository;
        this.fileValidationService = fileValidationService;
        this.userValidationService = userValidationService;
        this.emailService = emailService;
    }

    public void openRegisterFrame() {
        RegisterFrame frame = new RegisterFrame(this);
        frame.showFrame();
    }

    public void openLoginFrame() {
        LoginFrame frame = new LoginFrame(this);
        frame.showFrame();
    }

    public void openTermsOfUseDialog() {
        TermsOfUseDialog dialog = new TermsOfUseDialog();
        dialog.showDialog();
    }

    public FileValidationResult validateVerificationDocument(File file) {
        return fileValidationService.validateFileTypeAndSize(file);
    }

    public ValidationResult registerStudent(Student student, File file, boolean acceptedTerms) {
        ValidationResult fieldsResult = userValidationService.validateRequiredFields(student);
        if (!fieldsResult.isValid()) {
            return fieldsResult;
        }

        ValidationResult termsResult = userValidationService.validateTermsAccepted(acceptedTerms);
        if (!termsResult.isValid()) {
            return termsResult;
        }

        if (studentRepository.existsByUsername(student.getUsername())) {
            return ValidationResult.invalid("Το username χρησιμοποιείται ήδη.");
        }

        FileValidationResult fileResult = validateVerificationDocument(file);
        if (!fileResult.isValid()) {
            return fileResult;
        }

        student.uploadVerificationDocument(fileResult.getFilePath());
        studentRepository.save(student);
        emailService.sendRegistrationConfirmationEmail(student.getEmail());

        return ValidationResult.valid();
    }

    public Student login(String username, String password) {
        Student student = studentRepository.findByUsername(username);
        if (student != null && student.login(username, password)) {
            return student;
        }
        return null;
    }
}
