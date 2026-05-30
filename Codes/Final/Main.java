package weerasmus;

import javax.swing.SwingUtilities;
import weerasmus.app.AppContext;
import weerasmus.ui.LoginFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppContext context = new AppContext();
            context.seedSampleData();

            LoginFrame loginFrame = new LoginFrame(context.getAuthController());
            loginFrame.showFrame();
        });
    }
}
