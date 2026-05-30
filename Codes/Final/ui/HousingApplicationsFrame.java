package weerasmus.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import weerasmus.controller.HousingController;
import weerasmus.model.HousingApplication;

public class HousingApplicationsFrame extends JFrame {
    private final HousingController housingController;

    private JTable applicationsTable;
    private DefaultTableModel tableModel;

    public HousingApplicationsFrame(HousingController housingController) {
        this.housingController = housingController;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Αιτήσεις Κατοικίας");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Accommodation ID", "Ποσό", "Κατάσταση"}, 0
        );
        applicationsTable = new JTable(tableModel);

        add(new JScrollPane(applicationsTable), BorderLayout.CENTER);
    }

    public void showFrame(int studentId) {
        setVisible(true);
    }

    public void refreshHousingApplicationsTable(ArrayList<HousingApplication> applications) {
        tableModel.setRowCount(0);

        for (HousingApplication application : applications) {
            tableModel.addRow(new Object[]{
                    application.getHousingApplicationId(),
                    application.getAccommodationId(),
                    application.getTotalAmount(),
                    application.getStatus()
            });
        }
    }
}
