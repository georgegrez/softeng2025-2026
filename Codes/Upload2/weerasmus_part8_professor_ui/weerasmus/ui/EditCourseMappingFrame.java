package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import weerasmus.controller.CourseMappingController;
import weerasmus.dto.ValidationResult;
import weerasmus.model.CourseMapping;

public class EditCourseMappingFrame extends JFrame {
    private final CourseMappingController courseMappingController;
    private CourseMapping mapping;

    private JTextField homeCourseField;
    private JTextField hostCourseField;
    private JTextArea commentsArea;

    public EditCourseMappingFrame(CourseMappingController courseMappingController) {
        this.courseMappingController = courseMappingController;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Επεξεργασία Αντιστοίχισης");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        homeCourseField = new JTextField();
        hostCourseField = new JTextField();
        commentsArea = new JTextArea();

        form.add(new JLabel("Μάθημα προέλευσης:"));
        form.add(homeCourseField);
        form.add(new JLabel("Μάθημα υποδοχής:"));
        form.add(hostCourseField);
        form.add(new JLabel("Σχόλια:"));
        form.add(new JScrollPane(commentsArea));

        JButton saveButton = new JButton("Αποθήκευση");
        JButton deleteButton = new JButton("Διαγραφή");
        JButton cancelButton = new JButton("Ακύρωση");

        JPanel buttons = new JPanel();
        buttons.add(saveButton);
        buttons.add(deleteButton);
        buttons.add(cancelButton);

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> submitMappingChanges(mapping));
        deleteButton.addActionListener(e -> clickDeleteMappingButton(mapping.getMappingId()));
        cancelButton.addActionListener(e -> clickCancelButton());
    }

    public void showFrame(CourseMapping mapping) {
        this.mapping = mapping;
        homeCourseField.setText(mapping.getHomeCourseName());
        hostCourseField.setText(mapping.getHostCourseName());
        commentsArea.setText(mapping.getComments());
        setVisible(true);
    }

    public void submitMappingChanges(CourseMapping mappingData) {
        if (mapping == null) {
            return;
        }

        mapping.setHomeCourseName(homeCourseField.getText());
        mapping.setHostCourseName(hostCourseField.getText());
        mapping.setComments(commentsArea.getText());

        ValidationResult result = courseMappingController.updateMapping(mapping.getMappingId(), mapping);

        if (result.isValid()) {
            showMappingUpdateSuccess();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    result.getErrorsAsText(),
                    "Σφάλμα",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clickDeleteMappingButton(int mappingId) {
        courseMappingController.deleteMapping(mappingId);
        showMappingDeleteSuccess();
        dispose();
    }

    public void clickCancelButton() {
        dispose();
    }

    public void showMappingUpdateSuccess() {
        JOptionPane.showMessageDialog(this, "Η αντιστοίχιση ενημερώθηκε επιτυχώς.");
    }

    public void showMappingDeleteSuccess() {
        JOptionPane.showMessageDialog(this, "Η αντιστοίχιση διαγράφηκε επιτυχώς.");
    }
}
