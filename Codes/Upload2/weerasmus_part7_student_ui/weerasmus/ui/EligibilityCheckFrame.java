package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import weerasmus.controller.EligibilityController;
import weerasmus.dto.EligibilityResultDTO;
import weerasmus.dto.TranscriptSummaryDTO;

public class EligibilityCheckFrame extends JFrame {
    private final EligibilityController eligibilityController;
    private final int studentId;

    private JLabel selectedFileLabel;
    private JTextArea summaryArea;
    private File selectedFile;
    private int transcriptId;

    public EligibilityCheckFrame(EligibilityController eligibilityController, int studentId) {
        this.eligibilityController = eligibilityController;
        this.studentId = studentId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Έλεγχος Προϋποθέσεων Erasmus");
        setSize(600, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        JButton uploadButton = new JButton("Ανέβασμα Αναλυτικής Βαθμολογίας");
        JButton replaceButton = new JButton("Αντικατάσταση");
        JButton checkButton = new JButton("Έλεγχος Προϋποθέσεων");

        selectedFileLabel = new JLabel("Δεν έχει επιλεγεί αρχείο");
        summaryArea = new JTextArea();
        summaryArea.setEditable(false);

        topPanel.add(uploadButton);
        topPanel.add(replaceButton);
        topPanel.add(checkButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(summaryArea), BorderLayout.CENTER);
        add(selectedFileLabel, BorderLayout.SOUTH);

        uploadButton.addActionListener(e -> uploadTranscript(null));
        replaceButton.addActionListener(e -> clickReplaceTranscriptButton());
        checkButton.addActionListener(e -> clickRunEligibilityCheckButton());
    }

    public void showFrame(int studentId) {
        setVisible(true);
    }

    public void uploadTranscript(File file) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            selectedFileLabel.setText(selectedFile.getName());

            TranscriptSummaryDTO summary = eligibilityController.analyzeTranscript(studentId, selectedFile);
            if (summary == null) {
                showTranscriptAnalysisError();
            } else {
                transcriptId = summary.getTranscriptId();
                showTranscriptSummary(summary);
            }
        }
    }

    public void showTranscriptSummary(TranscriptSummaryDTO summary) {
        summaryArea.setText(
                "Transcript ID: " + summary.getTranscriptId() + "\n"
                        + "Μέσος όρος: " + summary.getAverageGrade() + "\n"
                        + "ECTS: " + summary.getTotalECTS() + "\n"
                        + "Χρωστούμενα: " + summary.getFailedCourses()
        );
    }

    public void showTranscriptAnalysisError() {
        JOptionPane.showMessageDialog(this,
                "Αποτυχία ανάλυσης εγγράφου.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showInvalidFileSizeError() {
        JOptionPane.showMessageDialog(this,
                "Το μέγεθος του αρχείου είναι μεγαλύτερο από το επιτρεπτό.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showInvalidFileTypeError() {
        JOptionPane.showMessageDialog(this,
                "Ο τύπος αρχείου δεν είναι αποδεκτός.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }

    public void clickReplaceTranscriptButton() {
        eligibilityController.resetTranscriptUpload();
        clearTranscriptPreview();
    }

    public void clearTranscriptPreview() {
        selectedFile = null;
        transcriptId = 0;
        selectedFileLabel.setText("Δεν έχει επιλεγεί αρχείο");
        summaryArea.setText("");
    }

    public void clickRunEligibilityCheckButton() {
        if (transcriptId == 0) {
            showEligibilityCheckError();
            return;
        }

        EligibilityResultDTO result = eligibilityController.checkEligibility(studentId, transcriptId);
        if (result == null) {
            showEligibilityCheckError();
        }
    }

    public void showEligibilityCheckError() {
        JOptionPane.showMessageDialog(this,
                "Αποτυχία για τον έλεγχο των προϋποθέσεων.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }
}
