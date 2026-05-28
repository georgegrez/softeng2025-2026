package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import weerasmus.controller.ProfessorController;
import weerasmus.dto.StudentEvaluationDTO;
import weerasmus.dto.ValidationResult;

public class StudentEvaluationFrame extends JFrame {
    private final ProfessorController professorController;
    private final int studentId;

    private JTextField score1Field;
    private JTextField score2Field;
    private JTextArea commentsArea;
    private JTextArea additionalDetailsArea;

    public StudentEvaluationFrame(ProfessorController professorController, int studentId) {
        this.professorController = professorController;
        this.studentId = studentId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Αξιολόγηση Φοιτητή");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        score1Field = new JTextField();
        score2Field = new JTextField();
        commentsArea = new JTextArea();
        additionalDetailsArea = new JTextArea();

        form.add(new JLabel("Κριτήριο 1:"));
        form.add(score1Field);
        form.add(new JLabel("Κριτήριο 2:"));
        form.add(score2Field);
        form.add(new JLabel("Σχόλια:"));
        form.add(new JScrollPane(commentsArea));
        form.add(new JLabel("Επιπλέον στοιχεία:"));
        form.add(new JScrollPane(additionalDetailsArea));

        JButton submitButton = new JButton("Υποβολή");
        JButton cancelButton = new JButton("Ακύρωση");

        JPanel buttons = new JPanel();
        buttons.add(submitButton);
        buttons.add(cancelButton);

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> clickSubmitEvaluationButton());
        cancelButton.addActionListener(e -> clickCancelEvaluationButton());
    }

    public void showFrame(int studentId) {
        setVisible(true);
    }

    public void fillRequiredEvaluationFields(ArrayList<Integer> criteriaScores, String comments) {
        if (criteriaScores != null && !criteriaScores.isEmpty()) {
            score1Field.setText(String.valueOf(criteriaScores.get(0)));
            if (criteriaScores.size() > 1) {
                score2Field.setText(String.valueOf(criteriaScores.get(1)));
            }
        }
        commentsArea.setText(comments);
    }

    public void clickAddAdditionalDetailsButton() {
        additionalDetailsArea.requestFocus();
    }

    public void fillAdditionalEvaluationDetails(String extraDetails) {
        additionalDetailsArea.setText(extraDetails);
    }

    private StudentEvaluationDTO buildDTO() {
        StudentEvaluationDTO dto = new StudentEvaluationDTO();
        ArrayList<Integer> scores = new ArrayList<>();

        try { scores.add(Integer.parseInt(score1Field.getText())); } catch (NumberFormatException ignored) {}
        try { scores.add(Integer.parseInt(score2Field.getText())); } catch (NumberFormatException ignored) {}

        dto.setCriteriaScores(scores);
        dto.setComments(commentsArea.getText());
        dto.setAdditionalDetails(additionalDetailsArea.getText());
        return dto;
    }

    public void clickSubmitEvaluationButton() {
        submitStudentEvaluation(null, null);
    }

    public void clickCancelEvaluationButton() {
        professorController.cancelEvaluation();
        dispose();
    }

    public void submitStudentEvaluation(ArrayList<Integer> criteriaScores, String comments) {
        StudentEvaluationDTO dto = buildDTO();
        ValidationResult result = professorController.evaluateStudent(0, studentId, dto);

        if (result.isValid()) {
            showEvaluationSubmissionSuccess();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, result.getErrorsAsText(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showEvaluationSubmissionSuccess() {
        JOptionPane.showMessageDialog(this, "Η αξιολόγηση υποβλήθηκε επιτυχώς.");
    }
}
