package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import weerasmus.controller.CourseMappingController;
import weerasmus.controller.ProfessorController;
import weerasmus.controller.ProgramController;
import weerasmus.model.Professor;

public class ProfessorHomeFrame extends JFrame {
    private final CourseMappingController courseMappingController;
    private final ProgramController programController;
    private final ProfessorController professorController;
    private final Professor professor;

    public ProfessorHomeFrame(CourseMappingController courseMappingController,
                              ProgramController programController,
                              ProfessorController professorController,
                              Professor professor) {
        this.courseMappingController = courseMappingController;
        this.programController = programController;
        this.professorController = professorController;
        this.professor = professor;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("WeErasmus - Αρχική Καθηγητή");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("Αρχική Σελίδα Καθηγητή", SwingConstants.CENTER);
        JButton mappingsButton = new JButton("Διαχείριση Αντιστοιχίσεων Μαθημάτων");
        JButton createProgramButton = new JButton("Δημιουργία Προγράμματος Erasmus");
        JButton progressButton = new JButton("Παρακολούθηση Πορείας Προγράμματος");
        JButton manageProgramsButton = new JButton("Διαχείριση Προγραμμάτων");

        panel.add(titleLabel);
        panel.add(mappingsButton);
        panel.add(createProgramButton);
        panel.add(progressButton);
        panel.add(manageProgramsButton);

        add(panel);

        mappingsButton.addActionListener(e -> clickCourseMappingsButton());
        createProgramButton.addActionListener(e -> clickCreateProgramButton());
        progressButton.addActionListener(e -> clickProgramProgressButton());
        manageProgramsButton.addActionListener(e -> clickManageProgramsButton());
    }

    public void clickCourseMappingsButton() {
        if (courseMappingController != null && professor != null) {
            courseMappingController.openCourseMappingsFrame(professor.getUserId());
        }
    }

    public void clickCreateProgramButton() {
        if (programController != null && professor != null) {
            programController.openCreateProgramFrame(professor.getUserId());
        }
    }

    public void clickProgramProgressButton() {
        if (professorController != null && professor != null) {
            professorController.openProgramProgressFrame(professor.getUserId());
        }
    }

    public void clickManageProgramsButton() {
        if (professorController != null && professor != null) {
            professorController.openManageProgramsFrame(professor.getUserId());
        }
    }

    public void showFrame(Professor professor) {
        setVisible(true);
    }
}
