package weerasmus.dto;

import java.util.ArrayList;
import weerasmus.model.Grade;

public class TranscriptDataDTO {
    private ArrayList<Grade> grades;

    public TranscriptDataDTO() {
        this.grades = new ArrayList<>();
    }

    public TranscriptDataDTO(ArrayList<Grade> grades) {
        this.grades = grades == null ? new ArrayList<>() : grades;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }
}
