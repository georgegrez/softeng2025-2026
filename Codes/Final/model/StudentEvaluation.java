package weerasmus.model;

import java.util.ArrayList;
import java.util.Date;

public class StudentEvaluation {
    private int evaluationId;
    private int professorId;
    private int studentId;
    private ArrayList<Integer> criteriaScores;
    private String comments;
    private String additionalDetails;
    private Date submissionDate;

    public StudentEvaluation() {
        this.criteriaScores = new ArrayList<>();
        this.submissionDate = new Date();
    }

    public StudentEvaluation(int evaluationId, int professorId, int studentId,
                             ArrayList<Integer> criteriaScores, String comments) {
        this.evaluationId = evaluationId;
        this.professorId = professorId;
        this.studentId = studentId;
        this.criteriaScores = criteriaScores != null ? criteriaScores : new ArrayList<>();
        this.comments = comments;
        this.submissionDate = new Date();
    }

    public void submit() {
        this.submissionDate = new Date();
    }

    public boolean isValid() {
        if (criteriaScores == null || criteriaScores.isEmpty()) {
            return false;
        }
        for (Integer score : criteriaScores) {
            if (score == null || score < 1 || score > 5) {
                return false;
            }
        }
        return true;
    }

    public double calculateAverageScore() {
        if (criteriaScores == null || criteriaScores.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (Integer score : criteriaScores) {
            sum += score;
        }
        return (double) sum / criteriaScores.size();
    }

    public int getEvaluationId() { return evaluationId; }
    public void setEvaluationId(int evaluationId) { this.evaluationId = evaluationId; }
    public int getProfessorId() { return professorId; }
    public void setProfessorId(int professorId) { this.professorId = professorId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public ArrayList<Integer> getCriteriaScores() { return criteriaScores; }
    public void setCriteriaScores(ArrayList<Integer> criteriaScores) { this.criteriaScores = criteriaScores; }
    public String getComments() { return comments; }
    public void updateComments(String comments) { this.comments = comments; }
    public String getAdditionalDetails() { return additionalDetails; }
    public void setAdditionalDetails(String additionalDetails) { this.additionalDetails = additionalDetails; }
    public Date getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(Date submissionDate) { this.submissionDate = submissionDate; }
}
