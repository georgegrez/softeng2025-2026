package weerasmus.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import weerasmus.model.ProgramApplication;

public class StudentApplicationsFrame extends JFrame {
    private JTable applicationsTable;
    private DefaultTableModel tableModel;

    public StudentApplicationsFrame() {
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Αιτήσεις Φοιτητή");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Program ID", "Ημερομηνία", "Κατάσταση"}, 0
        );
        applicationsTable = new JTable(tableModel);

        add(new JScrollPane(applicationsTable), BorderLayout.CENTER);
    }

    public void showFrame(int studentId) {
        setVisible(true);
    }

    public void refreshApplicationsTable(ArrayList<ProgramApplication> applications) {
        tableModel.setRowCount(0);

        for (ProgramApplication application : applications) {
            tableModel.addRow(new Object[]{
                    application.getApplicationId(),
                    application.getProgramId(),
                    application.getSubmissionDate(),
                    application.getStatus()
            });
        }
    }
}
