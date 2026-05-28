package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import weerasmus.controller.ProfessorController;
import weerasmus.dto.StudentProgressDTO;

public class StudentProgressFrame extends JFrame {
    private final ProfessorController professorController;
    private StudentProgressDTO progress;

    public StudentProgressFrame(ProfessorController professorController) {
        this.professorController = professorController;
        initializeComponents();
    }

    private JTextArea progressArea;

    private void initializeComponents() {
        setTitle("Πορεία Φοιτητή");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        progressArea = new JTextArea();
        progressArea.setEditable(false);

        JButton messageButton = new JButton("Αποστολή Μηνύματος");
        JButton evaluateButton = new JButton("Αξιολόγηση Φοιτητή");
        JButton suspendButton = new JButton("Αναστολή Συμμετοχής");

        JPanel buttons = new JPanel();
        buttons.add(messageButton);
        buttons.add(evaluateButton);
        buttons.add(suspendButton);

        add(new JScrollPane(progressArea), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        messageButton.addActionListener(e -> clickSendMessageButton(progress.getStudentId()));
        evaluateButton.addActionListener(e -> clickEvaluateStudentButton(progress.getStudentId()));
        suspendButton.addActionListener(e -> clickSuspendParticipationButton(progress.getStudentId(), progress.getProgramId()));
    }

    public void showFrame(StudentProgressDTO progress) {
        this.progress = progress;
        progressArea.setText(
                "Student ID: " + progress.getStudentId() + "\n"
                        + "Program ID: " + progress.getProgramId() + "\n"
                        + "Σημειώσεις πορείας: " + progress.getProgressNotes()
        );
        setVisible(true);
    }

    public void clickSendMessageButton(int studentId) {
        professorController.openSendMessageDialog(studentId);
    }

    public void clickEvaluateStudentButton(int studentId) {
        professorController.openStudentEvaluationFrame(studentId);
    }

    public void clickSuspendParticipationButton(int studentId, int programId) {
        professorController.openConfirmSuspendDialog(studentId, programId);
    }

    public void cancelCurrentAction() {
        // No-op for Swing.
    }

    public void closeFrame() {
        dispose();
    }
}
