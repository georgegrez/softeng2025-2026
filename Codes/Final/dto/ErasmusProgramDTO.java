package weerasmus.dto;

import java.util.ArrayList;
import java.util.Date;
import weerasmus.model.Course;
import weerasmus.model.StudyLevel;

public class ErasmusProgramDTO {
    private int programId;
    private String universityName;
    private String department;
    private String country;
    private int duration;
    private String period;
    private Date applicationDeadline;
    private int availablePositions;
    private String requirements;
    private String universityDescription;
    private StudyLevel studyLevel;
    private double estimatedLivingCost;
    private double estimatedHousingCost;
    private double estimatedTransportCost;
    private ArrayList<Course> availableCourses;

    public ErasmusProgramDTO() {
        this.availableCourses = new ArrayList<>();
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(Date applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public int getAvailablePositions() {
        return availablePositions;
    }

    public void setAvailablePositions(int availablePositions) {
        this.availablePositions = availablePositions;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getUniversityDescription() {
        return universityDescription;
    }

    public void setUniversityDescription(String universityDescription) {
        this.universityDescription = universityDescription;
    }

    public StudyLevel getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(StudyLevel studyLevel) {
        this.studyLevel = studyLevel;
    }

    public double getEstimatedLivingCost() {
        return estimatedLivingCost;
    }

    public void setEstimatedLivingCost(double estimatedLivingCost) {
        this.estimatedLivingCost = estimatedLivingCost;
    }

    public double getEstimatedHousingCost() {
        return estimatedHousingCost;
    }

    public void setEstimatedHousingCost(double estimatedHousingCost) {
        this.estimatedHousingCost = estimatedHousingCost;
    }

    public double getEstimatedTransportCost() {
        return estimatedTransportCost;
    }

    public void setEstimatedTransportCost(double estimatedTransportCost) {
        this.estimatedTransportCost = estimatedTransportCost;
    }

    public ArrayList<Course> getAvailableCourses() {
        return availableCourses;
    }

    public void setAvailableCourses(ArrayList<Course> availableCourses) {
        this.availableCourses = availableCourses;
    }
}
