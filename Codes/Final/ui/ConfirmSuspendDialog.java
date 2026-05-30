package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import weerasmus.controller.ProfessorController;
import weerasmus.dto.ParticipationResultDTO;

public class ConfirmSuspendDialog extends JDialog {
    private final ProfessorController professorController;
    private final int studentId;
    private final int programId;

    public ConfirmSuspendDialog(ProfessorController professorController, int studentId, int programId) {
        this.professorController = professorController;
        this.studentId = studentId;
        this.programId = programId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Επιβεβαίωση Αναστολής");
        setSize(450, 180);
        setModal(true);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Θέλετε σίγουρα να αναστείλετε τη συμμετοχή;", SwingConstants.CENTER);
        JButton confirmButton = new JButton("Ναι");
        JButton cancelButton = new JButton("Όχι");

        JPanel buttons = new JPanel();
        buttons.add(confirmButton);
        buttons.add(cancelButton);

        add(label, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        confirmButton.addActionListener(e -> confirmSuspend());
        cancelButton.addActionListener(e -> clickCancelSuspendButton());
    }

    public void showDialog(int studentId, int programId) {
        setVisible(true);
    }

    public void confirmSuspend() {
        ParticipationResultDTO result = professorController.suspendStudentParticipation(0, studentId, programId);
        if (result != null && result.isSuccess()) {
            showSuspensionSuccess();
        }
        closeDialog();
    }

    public void clickCancelSuspendButton() {
        professorController.cancelSuspension();
        closeDialog();
    }

    public void showSuspensionSuccess() {
        JOptionPane.showMessageDialog(this, "Η συμμετοχή ανεστάλη επιτυχώς.");
    }

    public void closeDialog() {
        dispose();
    }
}
