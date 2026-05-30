package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import weerasmus.controller.HousingController;
import weerasmus.dto.HousingApplicationDTO;
import weerasmus.dto.ValidationResult;
import weerasmus.model.Accommodation;

public class HousingApplicationFrame extends JFrame {
    private final HousingController housingController;
    private final int accommodationId;
    private final int studentId;

    private JTextField entryDateField;
    private JTextField exitDateField;
    private JTextField paymentMethodField;

    public HousingApplicationFrame(HousingController housingController, int accommodationId) {
        this(housingController, accommodationId, 0);
    }

    public HousingApplicationFrame(HousingController housingController, int accommodationId, int studentId) {
        this.housingController = housingController;
        this.accommodationId = accommodationId;
        this.studentId = studentId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Υποβολή Αίτησης Κατοικίας");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        entryDateField = new JTextField("2026-01-01");
        exitDateField = new JTextField("2026-06-01");
        paymentMethodField = new JTextField("Δόσεις");

        JButton submitButton = new JButton("Υποβολή");

        panel.add(new JLabel("Ημερομηνία εισόδου yyyy-MM-dd:"));
        panel.add(entryDateField);
        panel.add(new JLabel("Ημερομηνία εξόδου yyyy-MM-dd:"));
        panel.add(exitDateField);
        panel.add(new JLabel("Τρόπος πληρωμής:"));
        panel.add(paymentMethodField);
        panel.add(new JLabel(""));
        panel.add(submitButton);

        add(panel);

        submitButton.addActionListener(e -> submitHousingApplication(buildDTO()));
    }

    private HousingApplicationDTO buildDTO() {
        HousingApplicationDTO dto = new HousingApplicationDTO();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            dto.setEntryDate(formatter.parse(entryDateField.getText()));
            dto.setExitDate(formatter.parse(exitDateField.getText()));
        } catch (Exception ex) {
            // Validation service handles null dates.
        }

        dto.setPaymentMethod(paymentMethodField.getText());
        return dto;
    }

    public void showFrame(Accommodation accommodation) {
        setVisible(true);
    }

    public void submitHousingApplication(HousingApplicationDTO applicationData) {
        ValidationResult result = housingController.submitHousingApplication(studentId, accommodationId, applicationData);

        if (result.isValid()) {
            showHousingApplicationSuccess();
            dispose();
        } else {
            String errors = result.getErrorsAsText();

            if (errors.contains("διαθέσιμη")) {
                showAccommodationUnavailableForPeriodError();
            } else if (errors.contains("εισόδου")) {
                showInvalidRentalDatesError();
            } else {
                showRequiredFieldsError();
            }
        }
    }

    public void showHousingApplicationSuccess() {
        JOptionPane.showMessageDialog(this, "Η αίτηση κατοικίας υποβλήθηκε επιτυχώς.");
    }

    public void showAccommodationUnavailableForPeriodError() {
        JOptionPane.showMessageDialog(this,
                "Η κατοικία δεν είναι διαθέσιμη για την περίοδο που επιλέξατε.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showInvalidRentalDatesError() {
        JOptionPane.showMessageDialog(this,
                "Η ημερομηνία εισόδου πρέπει να είναι προγενέστερη της ημερομηνίας εξόδου.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showRequiredFieldsError() {
        JOptionPane.showMessageDialog(this,
                "Δεν έχουν συμπληρωθεί όλα τα απαιτούμενα στοιχεία.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }
}
