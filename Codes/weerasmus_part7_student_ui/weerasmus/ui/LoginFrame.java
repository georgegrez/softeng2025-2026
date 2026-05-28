package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import weerasmus.controller.AuthController;
import weerasmus.model.Student;

public class LoginFrame extends JFrame {
    private final AuthController authController;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame(AuthController authController) {
        this.authController = authController;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("WeErasmus - Σύνδεση");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Σύνδεση");
        registerButton = new JButton("Εγγραφή");

        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(registerButton);

        add(mainPanel);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> clickRegisterButton());
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        Student student = authController.login(username, password);
        if (student == null) {
            JOptionPane.showMessageDialog(this,
                    "Λάθος username ή password.",
                    "Σφάλμα σύνδεσης",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Επιτυχής σύνδεση.");
        StudentHomeFrame homeFrame = new StudentHomeFrame(null, null, null, student);
        homeFrame.showFrame(student);
        dispose();
    }

    public void clickRegisterButton() {
        authController.openRegisterFrame();
        dispose();
    }

    public void showFrame() {
        setVisible(true);
    }
}
