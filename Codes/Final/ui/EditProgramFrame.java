package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import weerasmus.controller.ProfessorController;
import weerasmus.dto.ValidationResult;
import weerasmus.model.ErasmusProgram;

public class EditProgramFrame extends JFrame {
    private final ProfessorController professorController;
    private final int professorId;
    private ErasmusProgram program;

    private JTextField deadlineField;
    private JTextField positionsField;

    public EditProgramFrame(ProfessorController professorController, int professorId) {
        this.professorController = professorController;
        this.professorId = professorId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Επεξεργασία Προγράμματος");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        deadlineField = new JTextField();
        positionsField = new JTextField();

        JButton validateDeadlineButton = new JButton("Έλεγχος Προθεσμίας");
        JButton updateButton = new JButton("Ενημέρωση");

        panel.add(new JLabel("Νέα προθεσμία yyyy-MM-dd:"));
        panel.add(deadlineField);
        panel.add(new JLabel("Διαθέσιμες θέσεις:"));
        panel.add(positionsField);
        panel.add(validateDeadlineButton);
        panel.add(updateButton);

        add(panel);

        validateDeadlineButton.addActionListener(e -> changeApplicationDeadline(parseDate()));
        updateButton.addActionListener(e -> clickUpdateProgramButton());
    }

    public void showFrame(ErasmusProgram program) {
        this.program = program;
        if (program.getApplicationDeadline() != null) {
            deadlineField.setText(new SimpleDateFormat("yyyy-MM-dd").format(program.getApplicationDeadline()));
        }
        positionsField.setText(String.valueOf(program.getAvailablePositions()));
        setVisible(true);
    }

    private Date parseDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(deadlineField.getText());
        } catch (Exception e) {
            return null;
        }
    }

    public void changeApplicationDeadline(Date newDeadline) {
        ValidationResult result = professorController.validateApplicationDeadline(program.getProgramId(), newDeadline);
        if (result.isValid()) {
            showDeadlineValidMessage();
        } else {
            showDeadlineBeforeTodayError();
        }
    }

    public void changeAvailablePositions(int newAvailablePositions) {
        positionsField.setText(String.valueOf(newAvailablePositions));
    }

    public void clickUpdateProgramButton() {
        Date newDeadline = parseDate();
        int positions;

        try {
            positions = Integer.parseInt(positionsField.getText());
        } catch (NumberFormatException e) {
            showAvailablePositionsError();
            return;
        }

        ValidationResult result = professorController.updateProgram(program.getProgramId(), newDeadline, positions);
        if (result.isValid()) {
            showProgramUpdateSuccess();
            dispose();
        } else {
            showAvailablePositionsError();
        }
    }

    public void showDeadlineValidMessage() {
        JOptionPane.showMessageDialog(this, "Η προθεσμία είναι έγκυρη.");
    }

    public void showDeadlineBeforeTodayError() {
        JOptionPane.showMessageDialog(this,
                "Η προθεσμία δεν μπορεί να είναι προγενέστερη της σημερινής ημερομηνίας.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showProgramUpdateSuccess() {
        JOptionPane.showMessageDialog(this, "Το πρόγραμμα ενημερώθηκε επιτυχώς.");
    }

    public void showAvailablePositionsError() {
        JOptionPane.showMessageDialog(this,
                "Οι διαθέσιμες θέσεις δεν είναι έγκυρες.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }
}
