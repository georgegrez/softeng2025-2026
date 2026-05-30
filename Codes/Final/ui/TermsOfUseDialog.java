package weerasmus.ui;

import javax.swing.*;
import java.awt.*;

public class TermsOfUseDialog extends JDialog {
    public TermsOfUseDialog() {
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Όροι Χρήσης");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setModal(true);

        JTextArea termsArea = new JTextArea();
        termsArea.setEditable(false);
        termsArea.setLineWrap(true);
        termsArea.setWrapStyleWord(true);
        termsArea.setText(
                "Όροι Χρήσης WeErasmus\n\n"
                        + "Η πλατφόρμα χρησιμοποιείται για τη διαχείριση διαδικασιών Erasmus. "
                        + "Ο χρήστης οφείλει να εισάγει αληθή στοιχεία και να χρησιμοποιεί "
                        + "την εφαρμογή αποκλειστικά για ακαδημαϊκούς σκοπούς."
        );

        JButton closeButton = new JButton("Κλείσιμο");
        closeButton.addActionListener(e -> dispose());

        add(new JScrollPane(termsArea), BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }

    public void showDialog() {
        setVisible(true);
    }
}
