package weerasmus.ui;

import javax.swing.*;
import java.awt.*;

public class ProgramApplicationFrame extends JFrame {
    private final int programId;
    private JTextArea motivationArea;

    public ProgramApplicationFrame(int programId) {
        this.programId = programId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Υποβολή Αίτησης Erasmus");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        motivationArea = new JTextArea();

        JButton submitButton = new JButton("Υποβολή");
        submitButton.addActionListener(e -> submitApplication());

        add(new JLabel("Motivation Letter:"), BorderLayout.NORTH);
        add(new JScrollPane(motivationArea), BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
    }

    private void submitApplication() {
        if (motivationArea.getText().isBlank()) {
            showApplicationError("Το motivation letter είναι υποχρεωτικό.");
            return;
        }

        showApplicationSuccess();
        dispose();
    }

    public void showFrame(int programId, int studentId) {
        setVisible(true);
    }

    public void submitApplication(Object applicationData) {
        submitApplication();
    }

    public void showApplicationSuccess() {
        JOptionPane.showMessageDialog(this, "Η αίτηση υποβλήθηκε επιτυχώς.");
    }

    public void showApplicationError(String message) {
        JOptionPane.showMessageDialog(this, message, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }
}
