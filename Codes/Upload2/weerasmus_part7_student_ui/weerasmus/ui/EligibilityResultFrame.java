package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import weerasmus.controller.EligibilityController;
import weerasmus.dto.EligibilityResultDTO;
import weerasmus.dto.PdfFile;

public class EligibilityResultFrame extends JFrame {
    private final EligibilityController eligibilityController;
    private EligibilityResultDTO result;

    private JTextArea resultArea;

    public EligibilityResultFrame(EligibilityController eligibilityController) {
        this.eligibilityController = eligibilityController;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Αποτέλεσμα Ελέγχου Προϋποθέσεων");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        JButton downloadButton = new JButton("Λήψη Αποτελεσμάτων PDF");
        downloadButton.addActionListener(e -> clickDownloadPdfButton(
                result == null ? 0 : result.getResultId()
        ));

        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        add(downloadButton, BorderLayout.SOUTH);
    }

    public void showFrame(EligibilityResultDTO result) {
        this.result = result;
        resultArea.setText(
                "Αποτέλεσμα: " + (result.isEligible() ? "Επιλέξιμος" : "Μη επιλέξιμος") + "\n"
                        + "Μήνυμα: " + result.getMessage()
        );
        setVisible(true);
    }

    public void clickDownloadPdfButton(int resultId) {
        PdfFile pdfFile = eligibilityController.downloadEligibilityResultPdf(resultId);

        if (pdfFile == null) {
            showPdfDownloadError();
        } else {
            savePdfFile(pdfFile);
        }
    }

    public void savePdfFile(PdfFile pdfFile) {
        JOptionPane.showMessageDialog(this,
                "Το PDF δημιουργήθηκε: " + pdfFile.getFilePath());
    }

    public void showPdfDownloadError() {
        JOptionPane.showMessageDialog(this,
                "Αποτυχία λήψης αρχείου.",
                "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
    }
}
