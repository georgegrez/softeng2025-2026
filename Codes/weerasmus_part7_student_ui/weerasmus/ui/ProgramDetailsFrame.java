package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import weerasmus.controller.ProgramController;
import weerasmus.dto.ErasmusProgramDTO;

public class ProgramDetailsFrame extends JFrame {
    private final ProgramController programController;
    private ErasmusProgramDTO program;

    public ProgramDetailsFrame(ProgramController programController) {
        this.programController = programController;
        initializeComponents();
    }

    private JTextArea detailsArea;

    private void initializeComponents() {
        setTitle("Πληροφορίες Προγράμματος Erasmus");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);

        JButton applyButton = new JButton("Υποβολή Αίτησης");
        applyButton.addActionListener(e -> clickSubmitApplicationButton(
                program == null ? 0 : program.getProgramId()
        ));

        add(new JScrollPane(detailsArea), BorderLayout.CENTER);
        add(applyButton, BorderLayout.SOUTH);
    }

    public void showFrame(ErasmusProgramDTO program) {
        this.program = program;

        detailsArea.setText(
                "Πανεπιστήμιο: " + program.getUniversityName() + "\n"
                        + "Τμήμα: " + program.getDepartment() + "\n"
                        + "Χώρα: " + program.getCountry() + "\n"
                        + "Διάρκεια: " + program.getDuration() + "\n"
                        + "Περίοδος: " + program.getPeriod() + "\n"
                        + "Διαθέσιμες θέσεις: " + program.getAvailablePositions() + "\n"
                        + "Περιγραφή: " + program.getUniversityDescription() + "\n"
                        + "Εκτιμώμενο κόστος διαβίωσης: " + program.getEstimatedLivingCost() + "\n"
                        + "Εκτιμώμενο κόστος στέγασης: " + program.getEstimatedHousingCost() + "\n"
                        + "Εκτιμώμενο κόστος μεταφορών: " + program.getEstimatedTransportCost()
        );

        setVisible(true);
    }

    public void clickSubmitApplicationButton(int programId) {
        ProgramApplicationFrame frame = new ProgramApplicationFrame(programId);
        frame.showFrame(programId, 0);
    }
}
