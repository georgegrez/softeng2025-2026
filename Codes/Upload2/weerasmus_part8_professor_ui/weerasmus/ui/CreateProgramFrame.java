package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import weerasmus.controller.ProgramController;
import weerasmus.dto.ErasmusProgramDTO;
import weerasmus.dto.ProgramBasicInfoDTO;
import weerasmus.dto.ProgramTimeInfoDTO;
import weerasmus.dto.ValidationResult;
import weerasmus.model.StudyLevel;

public class CreateProgramFrame extends JFrame {
    private final ProgramController programController;
    private final int professorId;

    private JTextField universityField;
    private JTextField departmentField;
    private JTextField countryField;
    private JComboBox<StudyLevel> studyLevelBox;
    private JTextField durationField;
    private JTextField periodField;
    private JTextField deadlineField;
    private JTextField positionsField;
    private JTextField livingCostField;
    private JTextField housingCostField;
    private JTextField transportCostField;
    private JTextArea requirementsArea;
    private JTextArea descriptionArea;

    public CreateProgramFrame(ProgramController programController, int professorId) {
        this.programController = programController;
        this.professorId = professorId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Δημιουργία Προγράμματος Erasmus");
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(13, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        universityField = new JTextField();
        departmentField = new JTextField();
        countryField = new JTextField();
        studyLevelBox = new JComboBox<>(StudyLevel.values());
        durationField = new JTextField();
        periodField = new JTextField();
        deadlineField = new JTextField("2026-06-30");
        positionsField = new JTextField();
        livingCostField = new JTextField();
        housingCostField = new JTextField();
        transportCostField = new JTextField();
        requirementsArea = new JTextArea();
        descriptionArea = new JTextArea();

        JButton validateBasicButton = new JButton("Έλεγχος βασικών στοιχείων");
        JButton validateTimeButton = new JButton("Έλεγχος χρονικών στοιχείων");
        JButton createButton = new JButton("Δημιουργία Προγράμματος");

        form.add(new JLabel("Πανεπιστήμιο:"));
        form.add(universityField);
        form.add(new JLabel("Τμήμα:"));
        form.add(departmentField);
        form.add(new JLabel("Χώρα:"));
        form.add(countryField);
        form.add(new JLabel("Επίπεδο σπουδών:"));
        form.add(studyLevelBox);
        form.add(new JLabel("Διάρκεια:"));
        form.add(durationField);
        form.add(new JLabel("Περίοδος:"));
        form.add(periodField);
        form.add(new JLabel("Προθεσμία yyyy-MM-dd:"));
        form.add(deadlineField);
        form.add(new JLabel("Διαθέσιμες θέσεις:"));
        form.add(positionsField);
        form.add(new JLabel("Κόστος διαβίωσης:"));
        form.add(livingCostField);
        form.add(new JLabel("Κόστος στέγασης:"));
        form.add(housingCostField);
        form.add(new JLabel("Κόστος μεταφορών:"));
        form.add(transportCostField);
        form.add(validateBasicButton);
        form.add(validateTimeButton);
        form.add(new JLabel(""));

        JPanel buttons = new JPanel();
        buttons.add(createButton);

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.add(new JScrollPane(requirementsArea));
        textPanel.add(new JScrollPane(descriptionArea));
        requirementsArea.setBorder(BorderFactory.createTitledBorder("Προϋποθέσεις"));
        descriptionArea.setBorder(BorderFactory.createTitledBorder("Περιγραφή Πανεπιστημίου"));

        add(form, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        validateBasicButton.addActionListener(e -> submitBasicProgramInfo(buildBasicInfo()));
        validateTimeButton.addActionListener(e -> submitProgramTimeInfo(buildTimeInfo()));
        createButton.addActionListener(e -> clickCreateProgramSubmitButton());
    }

    private ProgramBasicInfoDTO buildBasicInfo() {
        ProgramBasicInfoDTO dto = new ProgramBasicInfoDTO();
        dto.setUniversityName(universityField.getText());
        dto.setDepartment(departmentField.getText());
        dto.setCountry(countryField.getText());
        dto.setStudyLevel((StudyLevel) studyLevelBox.getSelectedItem());
        return dto;
    }

    private ProgramTimeInfoDTO buildTimeInfo() {
        ProgramTimeInfoDTO dto = new ProgramTimeInfoDTO();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            dto.setApplicationDeadline(formatter.parse(deadlineField.getText()));
        } catch (Exception ignored) {
        }

        dto.setPeriod(periodField.getText());

        try {
            dto.setDuration(Integer.parseInt(durationField.getText()));
        } catch (NumberFormatException ignored) {
        }

        return dto;
    }

    private ErasmusProgramDTO buildProgramDTO() {
        ErasmusProgramDTO dto = new ErasmusProgramDTO();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        dto.setUniversityName(universityField.getText());
        dto.setDepartment(departmentField.getText());
        dto.setCountry(countryField.getText());
        dto.setStudyLevel((StudyLevel) studyLevelBox.getSelectedItem());
        dto.setPeriod(periodField.getText());
        dto.setRequirements(requirementsArea.getText());
        dto.setUniversityDescription(descriptionArea.getText());

        try { dto.setDuration(Integer.parseInt(durationField.getText())); } catch (NumberFormatException ignored) {}
        try { dto.setAvailablePositions(Integer.parseInt(positionsField.getText())); } catch (NumberFormatException ignored) {}
        try { dto.setEstimatedLivingCost(Double.parseDouble(livingCostField.getText())); } catch (NumberFormatException ignored) {}
        try { dto.setEstimatedHousingCost(Double.parseDouble(housingCostField.getText())); } catch (NumberFormatException ignored) {}
        try { dto.setEstimatedTransportCost(Double.parseDouble(transportCostField.getText())); } catch (NumberFormatException ignored) {}
        try { dto.setApplicationDeadline(formatter.parse(deadlineField.getText())); } catch (Exception ignored) {}

        return dto;
    }

    public void showFrame(int professorId) {
        setVisible(true);
    }

    public void submitBasicProgramInfo(ProgramBasicInfoDTO basicInfo) {
        ValidationResult result = programController.validateBasicProgramInfo(basicInfo);
        if (result.isValid()) {
            showBasicInfoValidMessage();
        } else {
            showValidationErrors(result.getErrors());
        }
    }

    public void submitProgramTimeInfo(ProgramTimeInfoDTO timeInfo) {
        ValidationResult result = programController.validateProgramTimeInfo(timeInfo);
        if (result.isValid()) {
            showTimeInfoValidMessage();
        } else {
            showValidationErrors(result.getErrors());
        }
    }

    public void submitAdditionalProgramInfo(Object additionalInfo) {
        // Additional info is read directly from the fields.
    }

    public void clickCreateProgramSubmitButton() {
        ValidationResult result = programController.createProgram(professorId, buildProgramDTO());
        if (result.isValid()) {
            showProgramCreationSuccess();
            dispose();
        } else {
            showProgramCreationValidationErrors(result.getErrors());
        }
    }

    public void showBasicInfoValidMessage() {
        JOptionPane.showMessageDialog(this, "Τα βασικά στοιχεία είναι έγκυρα.");
    }

    public void showTimeInfoValidMessage() {
        JOptionPane.showMessageDialog(this, "Οι χρονικές πληροφορίες είναι έγκυρες.");
    }

    public void showValidationErrors(java.util.ArrayList<String> errors) {
        JOptionPane.showMessageDialog(this, String.join("\n", errors), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public void showInvalidAcademicYearError() {
        JOptionPane.showMessageDialog(this, "Το ακαδημαϊκό έτος δεν είναι έγκυρο.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public void showInvalidDeadlineError() {
        JOptionPane.showMessageDialog(this, "Η προθεσμία δεν είναι έγκυρη.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public void showProgramCreationSuccess() {
        JOptionPane.showMessageDialog(this, "Το πρόγραμμα δημιουργήθηκε επιτυχώς.");
    }

    public void showProgramCreationValidationErrors(java.util.ArrayList<String> errors) {
        showValidationErrors(errors);
    }
}
