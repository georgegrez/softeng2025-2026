package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import weerasmus.controller.EligibilityController;
import weerasmus.controller.HousingController;
import weerasmus.controller.ProgramController;
import weerasmus.model.Student;

public class StudentHomeFrame extends JFrame {
    private final ProgramController programController;
    private final HousingController housingController;
    private final EligibilityController eligibilityController;
    private final Student student;

    public StudentHomeFrame(ProgramController programController,
                            HousingController housingController,
                            EligibilityController eligibilityController,
                            Student student) {
        this.programController = programController;
        this.housingController = housingController;
        this.eligibilityController = eligibilityController;
        this.student = student;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("WeErasmus - Αρχική Φοιτητή");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel welcomeLabel = new JLabel("Καλωσήρθες στο WeErasmus", SwingConstants.CENTER);
        JButton searchProgramsButton = new JButton("Αναζήτηση Προγραμμάτων Erasmus");
        JButton searchHousingButton = new JButton("Αναζήτηση Στέγασης");
        JButton eligibilityButton = new JButton("Έλεγχος Προϋποθέσεων");

        panel.add(welcomeLabel);
        panel.add(searchProgramsButton);
        panel.add(searchHousingButton);
        panel.add(eligibilityButton);

        add(panel);

        searchProgramsButton.addActionListener(e -> clickSearchProgramsButton());
        searchHousingButton.addActionListener(e -> clickSearchHousingButton());
        eligibilityButton.addActionListener(e -> clickEligibilityCheckButton());
    }

    public void clickSearchProgramsButton() {
        if (programController != null && student != null) {
            programController.openProgramSearchFrame(student.getUserId());
        }
    }

    public void clickSearchHousingButton() {
        if (housingController != null && student != null) {
            housingController.openHousingSearchFrame(student.getUserId());
        }
    }

    public void clickEligibilityCheckButton() {
        if (eligibilityController != null && student != null) {
            eligibilityController.openEligibilityCheckFrame(student.getUserId());
        }
    }

    public void showFrame(Student student) {
        setVisible(true);
    }
}
