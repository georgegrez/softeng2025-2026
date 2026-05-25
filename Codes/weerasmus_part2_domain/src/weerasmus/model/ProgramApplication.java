package weerasmus.model;

import java.util.Date;

public class ProgramApplication {
    private int applicationId;
    private int studentId;
    private int programId;
    private Date submissionDate;
    private ApplicationStatus status;
    private String comments;
    private String motivationLetter;

    public ProgramApplication() {
        this.submissionDate = new Date();
        this.status = ApplicationStatus.PENDING;
    }

    public ProgramApplication(int applicationId, int studentId, int programId, String motivationLetter) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.programId = programId;
        this.motivationLetter = motivationLetter;
        this.submissionDate = new Date();
        this.status = ApplicationStatus.PENDING;
    }

    public void submit() {
        this.submissionDate = new Date();
        this.status = ApplicationStatus.PENDING;
    }

    public void approve(String comments) {
        this.status = ApplicationStatus.APPROVED;
        this.comments = comments;
    }

    public void reject(String comments) {
        this.status = ApplicationStatus.REJECTED;
        this.comments = comments;
    }

    public void cancel() {
        if (this.status == ApplicationStatus.PENDING) {
            this.status = ApplicationStatus.CANCELLED;
        }
    }

    public boolean isPending() {
        return status == ApplicationStatus.PENDING;
    }

    public int getApplicationId() { return applicationId; }
    public void setApplicationId(int applicationId) { this.applicationId = applicationId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public int getProgramId() { return programId; }
    public void setProgramId(int programId) { this.programId = programId; }
    public Date getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(Date submissionDate) { this.submissionDate = submissionDate; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public String getMotivationLetter() { return motivationLetter; }
    public void setMotivationLetter(String motivationLetter) { this.motivationLetter = motivationLetter; }
}
