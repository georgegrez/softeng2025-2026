public class Grade {
 
    private int gradeId;
    private double value;
    private String gradingScale;
    private int semester;
    private String academicYear;

public Grade(int gradeId, double value, String gradingScale, int semester,String academicYear) {
        this.gradeId = gradeId;
        this.value = value;
        this.gradingScale = gradingScale;
        this.semester = semester;
        this.academicYear = academicYear;
    }

    public int get_gradeId() {
        return gradeId;
    }

    public void set_gradeId(int id) {
        gradeId = id;
    }

    public double get_value() {
        return value;
    }

    public void set_value(double v) {
        value = v;
    }

    public String get_gradingScale() {
        return gradingScale;
    }

    public void set_gradingScale(String s) {
        gradingScale = s;
    }

    public int get_semester() {
        return semester;
    }

    public void set_semester(int s) {
        semester = s;
    }

    public String get_academicYear() {
        return academicYear;
    }

    public void set_academicYear(String a) {
        academicYear = a;
    }

}