package weerasmus.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import weerasmus.controller.ProfessorController;
import weerasmus.model.ErasmusProgram;

public class ManageProgramsFrame extends JFrame {
    private final ProfessorController professorController;
    private final int professorId;

    private JTable programsTable;
    private DefaultTableModel tableModel;

    public ManageProgramsFrame(ProfessorController professorController, int professorId) {
        this.professorController = professorController;
        this.professorId = professorId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Διαχείριση Προγραμμάτων Erasmus");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Πανεπιστήμιο", "Τμήμα", "Χώρα", "Θέσεις"}, 0);
        programsTable = new JTable(tableModel);

        JButton editButton = new JButton("Επεξεργασία");
        JButton applicationsButton = new JButton("Προβολή Αιτήσεων");

        JPanel buttons = new JPanel();
        buttons.add(editButton);
        buttons.add(applicationsButton);

        add(new JScrollPane(programsTable), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        editButton.addActionListener(e -> openSelectedProgramForEdit());
        applicationsButton.addActionListener(e -> openSelectedProgramApplications());
    }

    public void showFrame(int professorId) {
        setVisible(true);
    }

    public void showFrame(ArrayList<ErasmusProgram> programs) {
        refreshProgramsTable(programs);
        setVisible(true);
    }

    private void refreshProgramsTable(ArrayList<ErasmusProgram> programs) {
        tableModel.setRowCount(0);

        if (programs == null) {
            return;
        }

        for (ErasmusProgram program : programs) {
            tableModel.addRow(new Object[]{
                    program.getProgramId(),
                    program.getUniversityName(),
                    program.getDepartment(),
                    program.getCountry(),
                    program.getAvailablePositions()
            });
        }
    }

    private int getSelectedProgramId() {
        int row = programsTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Επιλέξτε πρόγραμμα.");
            return -1;
        }
        return (int) tableModel.getValueAt(row, 0);
    }

    private void openSelectedProgramForEdit() {
        int programId = getSelectedProgramId();
        if (programId != -1) {
            clickEditProgram(programId);
        }
    }

    private void openSelectedProgramApplications() {
        int programId = getSelectedProgramId();
        if (programId != -1) {
            clickViewApplications(programId);
        }
    }

    public void clickEditProgram(int programId) {
        if (professorController != null) {
            professorController.openEditProgramFrame(professorId, programId);
        }
    }

    public void clickViewApplications(int programId) {
        if (professorController != null) {
            professorController.openProgramApplicationsFrame(programId);
        }
    }

    public void closeFrame() {
        dispose();
    }
}
