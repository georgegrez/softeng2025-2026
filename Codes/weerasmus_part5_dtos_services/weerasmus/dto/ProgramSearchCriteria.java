package weerasmus.dto;

import weerasmus.model.StudyLevel;

public class ProgramSearchCriteria {
    private int studentId;
    private String text;
    private String country;
    private String university;
    private String department;
    private StudyLevel studyLevel;
    private Double maxLivingCost;
    private String period;

    public ProgramSearchCriteria() {
    }

    public ProgramSearchCriteria(int studentId, String text, String country,
                                 String university, String department,
                                 StudyLevel studyLevel, Double maxLivingCost,
                                 String period) {
        this.studentId = studentId;
        this.text = text;
        this.country = country;
        this.university = university;
        this.department = department;
        this.studyLevel = studyLevel;
        this.maxLivingCost = maxLivingCost;
        this.period = period;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public StudyLevel getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(StudyLevel studyLevel) {
        this.studyLevel = studyLevel;
    }

    public Double getMaxLivingCost() {
        return maxLivingCost;
    }

    public void setMaxLivingCost(Double maxLivingCost) {
        this.maxLivingCost = maxLivingCost;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
