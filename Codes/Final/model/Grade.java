package weerasmus.model;

public class Grade {
    private int gradeId;
    private Course course;
    private double value;
    private String gradingScale;
    private int semester;
    private String academicYear;

    public Grade() {}

    public Grade(int gradeId, Course course, double value, String gradingScale, int semester, String academicYear) {
        this.gradeId = gradeId;
        this.course = course;
        this.value = value;
        this.gradingScale = gradingScale;
        this.semester = semester;
        this.academicYear = academicYear;
    }

    public double convertToScale(String targetScale) {
        if (targetScale == null || targetScale.isBlank() || gradingScale == null) return value;
        if (gradingScale.equals("0-10") && targetScale.equals("0-100")) return value * 10;
        if (gradingScale.equals("0-100") && targetScale.equals("0-10")) return value / 10;
        return value;
    }

    public int getGradeId() { return gradeId; }
    public void setGradeId(int gradeId) { this.gradeId = gradeId; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public String getGradingScale() { return gradingScale; }
    public void setGradingScale(String gradingScale) { this.gradingScale = gradingScale; }
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }
}
