package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import weerasmus.controller.AuthController;
import weerasmus.dto.FileValidationResult;
import weerasmus.dto.ValidationResult;
import weerasmus.model.Student;
import weerasmus.model.StudyLevel;

public class RegisterFrame extends JFrame {
    private final AuthController authController;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField idCardField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField registrationNumberField;
    private JTextField amkaField;
    private JTextField departmentField;
    private JTextField institutionalEmailField;
    private JComboBox<StudyLevel> studyLevelBox;
    private JCheckBox termsCheckBox;
    private JLabel fileLabel;

    private File selectedFile;

    public RegisterFrame(AuthController authController) {
        this.authController = authController;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("WeErasmus - Δημιουργία Λογαριασμού");
        setSize(700, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(15, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        firstNameField = new JTextField();
        lastNameField = new JTextField();
        idCardField = new JTextField();
        addressField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        registrationNumberField = new JTextField();
        amkaField = new JTextField();
        departmentField = new JTextField();
        institutionalEmailField = new JTextField();
        studyLevelBox = new JComboBox<>(StudyLevel.values());
        termsCheckBox = new JCheckBox("Αποδέχομαι τους όρους χρήσης");
        fileLabel = new JLabel("Δεν έχει επιλεγεί αρχείο");

        JButton uploadButton = new JButton("Ανέβασμα εγγράφου");
        JButton termsButton = new JButton("Όροι χρήσης");
        JButton submitButton = new JButton("Εγγραφή");
        JButton backButton = new JButton("Επιστροφή στη σύνδεση");

        formPanel.add(new JLabel("Όνομα:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Επώνυμο:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("ΑΤ:"));
        formPanel.add(idCardField);
        formPanel.add(new JLabel("Διεύθυνση:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("Τηλέφωνο:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);
        formPanel.add(new JLabel("ΑΜ:"));
        formPanel.add(registrationNumberField);
        formPanel.add(new JLabel("ΑΜΚΑ:"));
        formPanel.add(amkaField);
        formPanel.add(new JLabel("Τμήμα:"));
        formPanel.add(departmentField);
        formPanel.add(new JLabel("Ιδρυματικό email:"));
        formPanel.add(institutionalEmailField);
        formPanel.add(new JLabel("Επίπεδο σπουδών:"));
        formPanel.add(studyLevelBox);
        formPanel.add(uploadButton);
        formPanel.add(fileLabel);
        formPanel.add(termsButton);
        formPanel.add(termsCheckBox);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(submitButton);
        buttonsPanel.add(backButton);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        uploadButton.addActionListener(e -> uploadVerificationDocument(null));
        termsButton.addActionListener(e -> clickTermsOfUseButton());
        submitButton.addActionListener(e -> clickSubmitRegistrationButton());
        backButton.addActionListener(e -> clickBackToLoginButton());
    }

    public void fillRegistrationForm(Student studentData) {
        // In Swing this is represented by the user typing into the fields.
    }

    public void uploadVerificationDocument(File file) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            FileValidationResult validation = authController.validateVerificationDocument(selectedFile);

            if (validation.isValid()) {
                fileLabel.setText(selectedFile.getName());
                showUploadSuccess();
            } else if (validation.getErrorsAsText().contains("μέγεθος")) {
                showInvalidFileSizeError();
            } else {
                showInvalidFileTypeError();
            }
        }
    }

    public void clickTermsOfUseButton() {
        authController.openTermsOfUseDialog();
    }

    public void clickBackToLoginButton() {
        authController.openLoginFrame();
        dispose();
    }

    public void acceptTerms() {
        termsCheckBox.setSelected(true);
    }

    public void clickSubmitRegistrationButton() {
        Student student = buildStudentFromForm();

        ValidationResult result = authController.registerStudent(
                student,
                selectedFile,
                termsCheckBox.isSelected()
        );

        if (result.isValid()) {
            showRegistrationSuccess();
            authController.openLoginFrame();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    result.getErrorsAsText(),
                    "Σφάλμα εγγραφής",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private Student buildStudentFromForm() {
        Student student = new Student();
        student.setFirstName(firstNameField.getText());
        student.setLastName(lastNameField.getText());
        student.setIdCardNumber(idCardField.getText());
        student.setAddress(addressField.getText());
        student.setPhone(phoneField.getText());
        student.setEmail(emailField.getText());
        student.setUsername(usernameField.getText());
        student.setPassword(new String(passwordField.getPassword()));
        student.setRegistrationNumber(registrationNumberField.getText());
        student.setAmka(amkaField.getText());
        student.setDepartment(departmentField.getText());
        student.setInstitutionalEmail(institutionalEmailField.getText());
        student.setStudyLevel((StudyLevel) studyLevelBox.getSelectedItem());
        return student;
    }

    public void showFrame() {
        setVisible(true);
    }

    public void showUploadSuccess() {
        JOptionPane.showMessageDialog(this, "Το αρχείο ανέβηκε επιτυχώς.");
    }

    public void showInvalidFileSizeError() {
        JOptionPane.showMessageDialog(this,
                "Το μέγεθος του αρχείου είναι μεγαλύτερο από το επιτρεπτό.",
                "Σφάλμα αρχείου",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showInvalidFileTypeError() {
        JOptionPane.showMessageDialog(this,
                "Ο τύπος του αρχείου δεν είναι αποδεκτός.",
                "Σφάλμα αρχείου",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showRegistrationSuccess() {
        JOptionPane.showMessageDialog(this, "Η εγγραφή ολοκληρώθηκε επιτυχώς.");
    }

    public void showRequiredFieldsError() {
        JOptionPane.showMessageDialog(this,
                "Δεν έχουν συμπληρωθεί όλα τα απαιτούμενα στοιχεία.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showUsernameAlreadyExistsError() {
        JOptionPane.showMessageDialog(this,
                "Το username χρησιμοποιείται ήδη.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showTermsNotAcceptedError() {
        JOptionPane.showMessageDialog(this,
                "Πρέπει να αποδεχτείτε τους όρους χρήσης.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }
}
