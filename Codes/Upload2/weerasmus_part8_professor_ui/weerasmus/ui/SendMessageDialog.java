package weerasmus.ui;

import javax.swing.*;
import java.awt.*;
import weerasmus.controller.ProfessorController;
import weerasmus.dto.MessageDTO;

public class SendMessageDialog extends JDialog {
    private final ProfessorController professorController;
    private final int studentId;
    private JTextArea messageArea;

    public SendMessageDialog(ProfessorController professorController, int studentId) {
        this.professorController = professorController;
        this.studentId = studentId;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Αποστολή Μηνύματος");
        setSize(450, 300);
        setModal(true);
        setLocationRelativeTo(null);

        messageArea = new JTextArea();

        JButton sendButton = new JButton("Αποστολή");
        JButton cancelButton = new JButton("Ακύρωση");

        JPanel buttons = new JPanel();
        buttons.add(sendButton);
        buttons.add(cancelButton);

        add(new JScrollPane(messageArea), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> clickSendMessageButton());
        cancelButton.addActionListener(e -> clickCancelSendMessageButton());
    }

    public void showDialog(int studentId) {
        setVisible(true);
    }

    public void typeMessage(String content) {
        messageArea.setText(content);
    }

    public void clickSendMessageButton() {
        submitMessage(messageArea.getText());
    }

    public void clickCancelSendMessageButton() {
        professorController.cancelSendMessage();
        closeDialog();
    }

    public void submitMessage(String content) {
        if (content == null || content.isBlank()) {
            JOptionPane.showMessageDialog(this, "Το μήνυμα δεν μπορεί να είναι κενό.");
            return;
        }

        MessageDTO dto = professorController.sendMessage(0, studentId, content);
        if (dto != null && dto.isSent()) {
            showMessageSentSuccess();
            closeDialog();
        }
    }

    public void showMessageSentSuccess() {
        JOptionPane.showMessageDialog(this, "Το μήνυμα στάλθηκε επιτυχώς.");
    }

    public void closeDialog() {
        dispose();
    }
}
