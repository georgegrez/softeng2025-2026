package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import weerasmus.controller.HousingController;
import weerasmus.model.Accommodation;

public class AccommodationDetailsFrame extends JFrame {
    private final HousingController housingController;
    private Accommodation accommodation;
    private JTextArea detailsArea;

    public AccommodationDetailsFrame(HousingController housingController) {
        this.housingController = housingController;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Πληροφορίες Κατοικίας");
        setSize(600, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);

        JButton reserveButton = new JButton("Κράτηση Κατοικίας");
        reserveButton.addActionListener(e -> clickReserveButton(accommodation.getAccommodationId()));

        add(new JScrollPane(detailsArea), BorderLayout.CENTER);
        add(reserveButton, BorderLayout.SOUTH);
    }

    public void showFrame(Accommodation accommodation) {
        this.accommodation = accommodation;

        detailsArea.setText(
                "Τίτλος: " + accommodation.getTitle() + "\n"
                        + "Περιοχή: " + accommodation.getArea() + "\n"
                        + "Κόστος: " + accommodation.getMonthlyCost() + "\n"
                        + "Περίοδος: " + accommodation.getRentalPeriod() + "\n"
                        + "Τρόπος πληρωμής: " + accommodation.getPaymentMethod() + "\n"
                        + "Απόσταση από πανεπιστήμιο: " + accommodation.getDistanceFromUniversity() + "\n"
                        + "Πληροφορίες οικοδεσπότη: " + accommodation.getHostInfo()
        );

        setVisible(true);
    }

    public void closeFrame() {
        dispose();
    }

    public void clickReserveButton(int accommodationId) {
        housingController.openHousingApplicationFrame(accommodationId);
    }
}
