package weerasmus.model;

import java.util.ArrayList;
import java.util.Date;

public class ErasmusProgram {
    private int programId;
    private int professorId;
    private String universityName;
    private String department;
    private String country;
    private int duration;
    private String period;
    private ArrayList<String> requiredLanguages;
    private Date applicationDeadline;
    private int availablePositions;
    private String requirements;
    private String universityDescription;
    private StudyLevel studyLevel;
    private double estimatedLivingCost;
    private double estimatedHousingCost;
    private double estimatedTransportCost;
    private ArrayList<Course> availableCourses;

    public ErasmusProgram() {
        this.requiredLanguages = new ArrayList<>();
        this.availableCourses = new ArrayList<>();
    }

    public ErasmusProgram(int programId, int professorId, String universityName, String department,
                          String country, int duration, String period,
                          Date applicationDeadline, int availablePositions,
                          String requirements, String universityDescription,
                          StudyLevel studyLevel, double estimatedLivingCost,
                          double estimatedHousingCost, double estimatedTransportCost) {
        this.programId = programId;
        this.professorId = professorId;
        this.universityName = universityName;
        this.department = department;
        this.country = country;
        this.duration = duration;
        this.period = period;
        this.applicationDeadline = applicationDeadline;
        this.availablePositions = availablePositions;
        this.requirements = requirements;
        this.universityDescription = universityDescription;
        this.studyLevel = studyLevel;
        this.estimatedLivingCost = estimatedLivingCost;
        this.estimatedHousingCost = estimatedHousingCost;
        this.estimatedTransportCost = estimatedTransportCost;
        this.requiredLanguages = new ArrayList<>();
        this.availableCourses = new ArrayList<>();
    }

    public boolean matchesCriteria(String text, String country, String university, StudyLevel studyLevel) {
        boolean matchesText = text == null || text.isBlank()
                || safeContains(universityName, text)
                || safeContains(department, text)
                || safeContains(this.country, text);

        boolean matchesCountry = country == null || country.isBlank()
                || safeEquals(this.country, country);

        boolean matchesUniversity = university == null || university.isBlank()
                || safeEquals(this.universityName, university);

        boolean matchesStudyLevel = studyLevel == null || this.studyLevel == studyLevel;

        return matchesText && matchesCountry && matchesUniversity && matchesStudyLevel;
    }

    private boolean safeContains(String value, String text) {
        return value != null && text != null && value.toLowerCase().contains(text.toLowerCase());
    }

    private boolean safeEquals(String first, String second) {
        return first != null && second != null && first.equalsIgnoreCase(second);
    }

    public boolean isApplicationOpen(Date currentDate) {
        if (applicationDeadline == null || currentDate == null) {
            return false;
        }
        return currentDate.before(applicationDeadline) || currentDate.equals(applicationDeadline);
    }

    public boolean hasAvailablePositions() {
        return availablePositions > 0;
    }

    public void decreaseAvailablePositions() {
        if (availablePositions > 0) {
            availablePositions--;
        }
    }

    public void addRequiredLanguage(String language) {
        if (language != null && !language.isBlank()) {
            requiredLanguages.add(language);
        }
    }

    public void addCourse(Course course) {
        if (course != null) {
            availableCourses.add(course);
        }
    }

    public double calculateEstimatedMonthlyCost() {
        return estimatedLivingCost + estimatedHousingCost + estimatedTransportCost;
    }

    public int getProgramId() { return programId; }
    public void setProgramId(int programId) { this.programId = programId; }
    public int getProfessorId() { return professorId; }
    public void setProfessorId(int professorId) { this.professorId = professorId; }
    public String getUniversityName() { return universityName; }
    public void setUniversityName(String universityName) { this.universityName = universityName; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
    public ArrayList<String> getRequiredLanguages() { return requiredLanguages; }
    public void setRequiredLanguages(ArrayList<String> requiredLanguages) { this.requiredLanguages = requiredLanguages; }
    public Date getApplicationDeadline() { return applicationDeadline; }
    public void setApplicationDeadline(Date applicationDeadline) { this.applicationDeadline = applicationDeadline; }
    public int getAvailablePositions() { return availablePositions; }
    public void setAvailablePositions(int availablePositions) { this.availablePositions = availablePositions; }
    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }
    public String getUniversityDescription() { return universityDescription; }
    public void setUniversityDescription(String universityDescription) { this.universityDescription = universityDescription; }
    public StudyLevel getStudyLevel() { return studyLevel; }
    public void setStudyLevel(StudyLevel studyLevel) { this.studyLevel = studyLevel; }
    public double getEstimatedLivingCost() { return estimatedLivingCost; }
    public void setEstimatedLivingCost(double estimatedLivingCost) { this.estimatedLivingCost = estimatedLivingCost; }
    public double getEstimatedHousingCost() { return estimatedHousingCost; }
    public void setEstimatedHousingCost(double estimatedHousingCost) { this.estimatedHousingCost = estimatedHousingCost; }
    public double getEstimatedTransportCost() { return estimatedTransportCost; }
    public void setEstimatedTransportCost(double estimatedTransportCost) { this.estimatedTransportCost = estimatedTransportCost; }
    public ArrayList<Course> getAvailableCourses() { return availableCourses; }
    public void setAvailableCourses(ArrayList<Course> availableCourses) { this.availableCourses = availableCourses; }

    @Override
    public String toString() {
        return universityName + " - " + department + " (" + country + ")";
    }
}
