package weerasmus.model;

import java.util.ArrayList;

public class EligibilityRule {
    private int ruleId;
    private String department;
    private int minECTS;
    private int maxFailedCourses;
    private StudyLevel requiredStudyLevel;
    private ArrayList<String> requiredLanguages;
    private String additionalRequirements;

    public EligibilityRule() {
        this.requiredLanguages = new ArrayList<>();
    }

    public EligibilityRule(int ruleId, String department, int minECTS, int maxFailedCourses,
                           StudyLevel requiredStudyLevel, String additionalRequirements) {
        this.ruleId = ruleId;
        this.department = department;
        this.minECTS = minECTS;
        this.maxFailedCourses = maxFailedCourses;
        this.requiredStudyLevel = requiredStudyLevel;
        this.additionalRequirements = additionalRequirements;
        this.requiredLanguages = new ArrayList<>();
    }

    public boolean isSatisfiedBy(Student student, Transcript transcript) {
        if (student == null || transcript == null) return false;
        boolean ectsOk = transcript.getTotalECTS() >= minECTS;
        boolean failedCoursesOk = transcript.getFailedCoursesCount() <= maxFailedCourses;
        boolean studyLevelOk = requiredStudyLevel == null || student.getStudyLevel() == requiredStudyLevel;
        boolean languagesOk = student.getLanguages().containsAll(requiredLanguages);
        return ectsOk && failedCoursesOk && studyLevelOk && languagesOk;
    }

    public void addRequiredLanguage(String language) {
        if (language != null && !language.isBlank()) requiredLanguages.add(language);
    }

    public int getRuleId() { return ruleId; }
    public void setRuleId(int ruleId) { this.ruleId = ruleId; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public int getMinECTS() { return minECTS; }
    public void setMinECTS(int minECTS) { this.minECTS = minECTS; }
    public int getMaxFailedCourses() { return maxFailedCourses; }
    public void setMaxFailedCourses(int maxFailedCourses) { this.maxFailedCourses = maxFailedCourses; }
    public StudyLevel getRequiredStudyLevel() { return requiredStudyLevel; }
    public void setRequiredStudyLevel(StudyLevel requiredStudyLevel) { this.requiredStudyLevel = requiredStudyLevel; }
    public ArrayList<String> getRequiredLanguages() { return requiredLanguages; }
    public void setRequiredLanguages(ArrayList<String> requiredLanguages) { this.requiredLanguages = requiredLanguages; }
    public String getAdditionalRequirements() { return additionalRequirements; }
    public void setAdditionalRequirements(String additionalRequirements) { this.additionalRequirements = additionalRequirements; }
}
