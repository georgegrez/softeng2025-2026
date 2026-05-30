package weerasmus.model;

import java.util.ArrayList;
import java.util.Date;

public class ProgramEvaluation {
    private int evaluationId;
    private int studentId;
    private int programId;
    private int rating;
    private ArrayList<String> answers;
    private int platformRating;
    private String comments;
    private Date submissionDate;

    public ProgramEvaluation() {
        this.answers = new ArrayList<>();
        this.submissionDate = new Date();
    }

    public ProgramEvaluation(int evaluationId, int studentId, int programId,
                             int rating, int platformRating, String comments) {
        this.evaluationId = evaluationId;
        this.studentId = studentId;
        this.programId = programId;
        this.rating = rating;
        this.platformRating = platformRating;
        this.comments = comments;
        this.answers = new ArrayList<>();
        this.submissionDate = new Date();
    }

    public void submit() {
        this.submissionDate = new Date();
    }

    public boolean isValid() {
        return rating >= 1 && rating <= 5 && platformRating >= 1 && platformRating <= 5;
    }

    public void addAnswer(String answer) {
        if (answer != null && !answer.isBlank()) {
            answers.add(answer);
        }
    }

    public int getEvaluationId() { return evaluationId; }
    public void setEvaluationId(int evaluationId) { this.evaluationId = evaluationId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public int getProgramId() { return programId; }
    public void setProgramId(int programId) { this.programId = programId; }
    public int getRating() { return rating; }
    public void updateRating(int rating) { this.rating = rating; }
    public ArrayList<String> getAnswers() { return answers; }
    public void setAnswers(ArrayList<String> answers) { this.answers = answers; }
    public int getPlatformRating() { return platformRating; }
    public void setPlatformRating(int platformRating) { this.platformRating = platformRating; }
    public String getComments() { return comments; }
    public void updateComments(String comments) { this.comments = comments; }
    public Date getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(Date submissionDate) { this.submissionDate = submissionDate; }
}
