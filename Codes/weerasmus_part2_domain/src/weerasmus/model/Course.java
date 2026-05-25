package weerasmus.model;

public class Course {
    private int courseId;
    private String courseCode;
    private String title;
    private int ects;
    private String description;
    private String department;
    private String gradingScale;

    public Course() {}

    public Course(int courseId, String courseCode, String title, int ects,
                  String description, String department, String gradingScale) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.title = title;
        this.ects = ects;
        this.description = description;
        this.department = department;
        this.gradingScale = gradingScale;
    }

    public boolean matchesTitle(String text) {
        return text == null || text.isBlank()
                || (title != null && title.toLowerCase().contains(text.toLowerCase()))
                || (courseCode != null && courseCode.toLowerCase().contains(text.toLowerCase()));
    }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getEcts() { return ects; }
    public void setEcts(int ects) { this.ects = ects; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getGradingScale() { return gradingScale; }
    public void setGradingScale(String gradingScale) { this.gradingScale = gradingScale; }

    @Override
    public String toString() {
        return courseCode + " - " + title;
    }
}
