package weerasmus.service;

import java.util.ArrayList;

public class EmailService {
    public void sendRegistrationConfirmationEmail(String email) {
        System.out.println("Mock email επιβεβαίωσης εγγραφής προς: " + email);
    }

    public void sendParticipationSuspensionEmail(int studentId, int programId) {
        System.out.println("Mock email αναστολής συμμετοχής προς studentId=" + studentId
                + " για programId=" + programId);
    }

    public void sendApplicationDecisionEmails(ArrayList<Integer> applicationIds, String status) {
        System.out.println("Mock emails απόφασης αιτήσεων: " + applicationIds + " status=" + status);
    }
}
