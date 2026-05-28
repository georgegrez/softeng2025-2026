package weerasmus.dto;

import java.util.ArrayList;

public class StudentEvaluationDTO {
    private ArrayList<Integer> criteriaScores;
    private String comments;
    private String additionalDetails;

    public StudentEvaluationDTO() {
        this.criteriaScores = new ArrayList<>();
    }

    public ArrayList<Integer> getCriteriaScores() {
        return criteriaScores;
    }

    public void setCriteriaScores(ArrayList<Integer> criteriaScores) {
        this.criteriaScores = criteriaScores;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }
}
