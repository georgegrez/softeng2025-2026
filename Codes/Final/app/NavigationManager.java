package weerasmus.app;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NavigationManager {
    public void openFrame(JFrame frame) {
        if (frame != null) {
            frame.setVisible(true);
        }
    }

    public void closeFrame(JFrame frame) {
        if (frame != null) {
            frame.dispose();
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public boolean showConfirmDialog(String message) {
        int result = JOptionPane.showConfirmDialog(
                null,
                message,
                "Επιβεβαίωση",
                JOptionPane.YES_NO_OPTION
        );

        return result == JOptionPane.YES_OPTION;
    }
}
