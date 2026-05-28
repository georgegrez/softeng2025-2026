package weerasmus.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import weerasmus.controller.ProgramController;
import weerasmus.dto.ProgramSearchCriteria;
import weerasmus.dto.ProgramSearchResultDTO;
import weerasmus.model.ErasmusProgram;

public class ProgramSearchFrame extends JFrame {
    private final ProgramController programController;
    private final int studentId;

    private JTextField searchField;
    private JTextField countryField;
    private JTextField universityField;
    private JTable resultsTable;
    private DefaultTableModel tableModel;

    public ProgramSearchFrame(ProgramController programController, int studentId) {
        this.programController = programController;
        this.studentId = studentId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Αναζήτηση Προγραμμάτων Erasmus");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel filtersPanel = new JPanel(new GridLayout(2, 4, 8, 8));
        filtersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        searchField = new JTextField();
        countryField = new JTextField();
        universityField = new JTextField();

        JButton searchButton = new JButton("Αναζήτηση");
        JButton detailsButton = new JButton("Περισσότερες πληροφορίες");

        filtersPanel.add(new JLabel("Κείμενο:"));
        filtersPanel.add(searchField);
        filtersPanel.add(new JLabel("Χώρα:"));
        filtersPanel.add(countryField);
        filtersPanel.add(new JLabel("Πανεπιστήμιο:"));
        filtersPanel.add(universityField);
        filtersPanel.add(searchButton);
        filtersPanel.add(detailsButton);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Πανεπιστήμιο", "Τμήμα", "Χώρα", "Θέσεις"}, 0
        );
        resultsTable = new JTable(tableModel);

        add(filtersPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultsTable), BorderLayout.CENTER);

        searchButton.addActionListener(e -> submitSearch(searchField.getText(), buildCriteria()));
        detailsButton.addActionListener(e -> openSelectedProgramDetails());
    }

    private ProgramSearchCriteria buildCriteria() {
        ProgramSearchCriteria criteria = new ProgramSearchCriteria();
        criteria.setCountry(countryField.getText());
        criteria.setUniversity(universityField.getText());
        return criteria;
    }

    public void submitSearch(String text, ProgramSearchCriteria filters) {
        ProgramSearchResultDTO result = programController.searchPrograms(studentId, text, filters);

        if (result.hasResults()) {
            refreshResultsTable(result);
        } else {
            showNoResultsMessage();
        }
    }

    public void refreshResultsTable(ProgramSearchResultDTO results) {
        tableModel.setRowCount(0);

        for (ErasmusProgram program : results.getResults()) {
            tableModel.addRow(new Object[]{
                    program.getProgramId(),
                    program.getUniversityName(),
                    program.getDepartment(),
                    program.getCountry(),
                    program.getAvailablePositions()
            });
        }
    }

    public void showNoResultsMessage() {
        JOptionPane.showMessageDialog(this,
                "Δεν βρέθηκαν αποτελέσματα.",
                "Αναζήτηση",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void showLoadResultsError() {
        JOptionPane.showMessageDialog(this,
                "Δεν κατέστη δυνατή η φόρτωση των αποτελεσμάτων.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }

    public void clickMoreInfo(int programId) {
        programController.openProgramDetailsFrame(programId);
    }

    private void openSelectedProgramDetails() {
        int row = resultsTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Επιλέξτε ένα πρόγραμμα.");
            return;
        }

        int programId = (int) tableModel.getValueAt(row, 0);
        clickMoreInfo(programId);
    }

    public void closeFrame() {
        dispose();
    }

    public void showFrame(int studentId) {
        setVisible(true);
    }
}
