package weerasmus.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import weerasmus.controller.CourseMappingController;
import weerasmus.dto.CourseMappingSearchCriteria;
import weerasmus.dto.MappingSearchResultDTO;
import weerasmus.model.CourseMapping;

public class CourseMappingsFrame extends JFrame {
    private final CourseMappingController courseMappingController;
    private final int professorId;

    private JTextField courseNameField;
    private JTable mappingsTable;
    private DefaultTableModel tableModel;

    public CourseMappingsFrame(CourseMappingController courseMappingController, int professorId) {
        this.courseMappingController = courseMappingController;
        this.professorId = professorId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Αντιστοιχίσεις Μαθημάτων");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridLayout(1, 4, 8, 8));
        courseNameField = new JTextField();
        JButton searchButton = new JButton("Αναζήτηση");
        JButton editButton = new JButton("Επεξεργασία");

        topPanel.add(new JLabel("Μάθημα:"));
        topPanel.add(courseNameField);
        topPanel.add(searchButton);
        topPanel.add(editButton);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Μάθημα προέλευσης", "Μάθημα υποδοχής", "Κατάσταση"}, 0);
        mappingsTable = new JTable(tableModel);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(mappingsTable), BorderLayout.CENTER);

        searchButton.addActionListener(e -> submitCourseName(courseNameField.getText()));
        editButton.addActionListener(e -> openSelectedMapping());
    }

    public void showFrame(int professorId) {
        setVisible(true);
    }

    public void submitCourseName(String courseName) {
        MappingSearchResultDTO result = courseMappingController.searchMappingsByCourseName(professorId, courseName);
        if (result.hasResults()) {
            refreshMappingsTable(result);
        } else {
            showMappingNotFoundMessage();
        }
    }

    public void applyMappingFilters(CourseMappingSearchCriteria filters) {
        MappingSearchResultDTO result = courseMappingController.searchMappingsByFilters(professorId, filters);
        refreshMappingsTable(result);
    }

    public void refreshMappingsTable(MappingSearchResultDTO results) {
        tableModel.setRowCount(0);

        for (CourseMapping mapping : results.getMappings()) {
            tableModel.addRow(new Object[]{
                    mapping.getMappingId(),
                    mapping.getHomeCourseName(),
                    mapping.getHostCourseName(),
                    mapping.getMappingStatus()
            });
        }
    }

    private void openSelectedMapping() {
        int row = mappingsTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Επιλέξτε αντιστοίχιση.");
            return;
        }

        int mappingId = (int) tableModel.getValueAt(row, 0);
        clickEditMapping(mappingId);
    }

    public void clickEditMapping(int mappingId) {
        courseMappingController.openEditCourseMappingFrame(mappingId);
    }

    public void showMappingNotFoundMessage() {
        JOptionPane.showMessageDialog(this,
                "Δεν υπάρχει κάποια αντιστοίχιση.",
                "Αναζήτηση",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
