package weerasmus.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import weerasmus.controller.HousingController;
import weerasmus.dto.AccommodationListDTO;
import weerasmus.dto.AccommodationSearchCriteria;
import weerasmus.model.Accommodation;

public class HousingSearchFrame extends JFrame {
    private final HousingController housingController;
    private final int studentId;

    private JTextField areaField;
    private JTextField maxCostField;
    private JTable resultsTable;
    private DefaultTableModel tableModel;

    public HousingSearchFrame(HousingController housingController, int studentId) {
        this.housingController = housingController;
        this.studentId = studentId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Αναζήτηση Στέγασης");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel filterPanel = new JPanel(new GridLayout(1, 6, 8, 8));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        areaField = new JTextField();
        maxCostField = new JTextField();

        JButton searchButton = new JButton("Αναζήτηση");
        JButton detailsButton = new JButton("Προβολή λεπτομερειών");

        filterPanel.add(new JLabel("Περιοχή:"));
        filterPanel.add(areaField);
        filterPanel.add(new JLabel("Μέγιστο κόστος:"));
        filterPanel.add(maxCostField);
        filterPanel.add(searchButton);
        filterPanel.add(detailsButton);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Τίτλος", "Περιοχή", "Κόστος", "Απόσταση"}, 0
        );
        resultsTable = new JTable(tableModel);

        add(filterPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultsTable), BorderLayout.CENTER);

        searchButton.addActionListener(e -> applyFilters(buildCriteria()));
        detailsButton.addActionListener(e -> openSelectedAccommodationDetails());
    }

    private AccommodationSearchCriteria buildCriteria() {
        AccommodationSearchCriteria criteria = new AccommodationSearchCriteria();
        criteria.setArea(areaField.getText());

        try {
            if (!maxCostField.getText().isBlank()) {
                criteria.setMaxMonthlyCost(Double.parseDouble(maxCostField.getText()));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Το κόστος πρέπει να είναι αριθμός.");
        }

        return criteria;
    }

    public void showFrame(AccommodationListDTO recommendations) {
        refreshResultsTable(recommendations);
        setVisible(true);
    }

    public void applyFilters(AccommodationSearchCriteria filters) {
        AccommodationListDTO result = housingController.searchAccommodations(filters);

        if (result.hasResults()) {
            refreshResultsTable(result);
        } else {
            showNoAccommodationAvailableMessage();
        }
    }

    public void refreshResultsTable(AccommodationListDTO results) {
        tableModel.setRowCount(0);

        for (Accommodation accommodation : results.getAccommodations()) {
            tableModel.addRow(new Object[]{
                    accommodation.getAccommodationId(),
                    accommodation.getTitle(),
                    accommodation.getArea(),
                    accommodation.getMonthlyCost(),
                    accommodation.getDistanceFromUniversity()
            });
        }
    }

    public void showNoAccommodationAvailableMessage() {
        JOptionPane.showMessageDialog(this,
                "Δεν υπάρχει διαθέσιμη κατοικία με βάση τα φίλτρα.",
                "Αναζήτηση",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void clickViewDetails(int accommodationId) {
        housingController.openAccommodationDetailsFrame(accommodationId);
    }

    private void openSelectedAccommodationDetails() {
        int row = resultsTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Επιλέξτε κατοικία.");
            return;
        }

        int accommodationId = (int) tableModel.getValueAt(row, 0);
        clickViewDetails(accommodationId);
    }
}
