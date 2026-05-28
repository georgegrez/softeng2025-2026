package weerasmus.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import weerasmus.controller.ProfessorController;
import weerasmus.dto.ValidationResult;
import weerasmus.model.ProgramApplication;

public class ProgramApplicationsFrame extends JFrame {
    private final ProfessorController professorController;
    private final int programId;

    private JTable applicationsTable;
    private DefaultTableModel tableModel;

    public ProgramApplicationsFrame(ProfessorController professorController, int programId) {
        this.professorController = professorController;
        this.programId = programId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Αιτήσεις Προγράμματος");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Student ID", "Ημερομηνία", "Κατάσταση"}, 0);
        applicationsTable = new JTable(tableModel);
        applicationsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton approveButton = new JButton("Έγκριση Επιλεγμένων");
        JButton exitButton = new JButton("Έξοδος");

        JPanel buttons = new JPanel();
        buttons.add(approveButton);
        buttons.add(exitButton);

        add(new JScrollPane(applicationsTable), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        approveButton.addActionListener(e -> clickApproveSelectedButton());
        exitButton.addActionListener(e -> clickExitButton());
    }

    public void showFrame(ArrayList<ProgramApplication> applications) {
        refreshApplicationsTable(applications);
        setVisible(true);
    }

    private void refreshApplicationsTable(ArrayList<ProgramApplication> applications) {
        tableModel.setRowCount(0);

        if (applications == null) {
            return;
        }

        for (ProgramApplication application : applications) {
            tableModel.addRow(new Object[]{
                    application.getApplicationId(),
                    application.getStudentId(),
                    application.getSubmissionDate(),
                    application.getStatus()
            });
        }
    }

    public ArrayList<Integer> selectApplicationsForApproval() {
        ArrayList<Integer> ids = new ArrayList<>();
        int[] rows = applicationsTable.getSelectedRows();

        for (int row : rows) {
            ids.add((Integer) tableModel.getValueAt(row, 0));
        }

        return ids;
    }

    public void selectApplicationsForApproval(ArrayList<Integer> applicationIds) {
        // Used by sequence diagram; actual Swing selection is done from JTable.
    }

    public void clickApproveSelectedButton() {
        ArrayList<Integer> ids = selectApplicationsForApproval();

        if (ids.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Επιλέξτε τουλάχιστον μία αίτηση.");
            return;
        }

        ValidationResult result = professorController.approveApplications(programId, ids);
        if (result.isValid()) {
            showApplicationsApprovalSuccess();
        } else {
            showMaxApprovedApplicationsReachedError();
        }
    }

    public void showApplicationsApprovalSuccess() {
        JOptionPane.showMessageDialog(this, "Οι αιτήσεις εγκρίθηκαν επιτυχώς.");
    }

    public void showMaxApprovedApplicationsReachedError() {
        JOptionPane.showMessageDialog(this,
                "Έχει συμπληρωθεί ο μέγιστος αριθμός εγκεκριμένων αιτήσεων.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }

    public void clickExitButton() {
        dispose();
    }
}
