package weerasmus.model;

import java.util.ArrayList;
import java.util.Date;

public class GradeConversion {
    private int conversionId;
    private int studentId;
    private String sourceScale;
    private String targetScale;
    private Date conversionDate;
    private String status;
    private ArrayList<Grade> convertedGrades;

    public GradeConversion() {
        this.conversionDate = new Date();
        this.status = "CREATED";
        this.convertedGrades = new ArrayList<>();
    }

    public GradeConversion(int conversionId, int studentId, String sourceScale, String targetScale) {
        this.conversionId = conversionId;
        this.studentId = studentId;
        this.sourceScale = sourceScale;
        this.targetScale = targetScale;
        this.conversionDate = new Date();
        this.status = "CREATED";
        this.convertedGrades = new ArrayList<>();
    }

    public ArrayList<Grade> convert(ArrayList<Grade> grades) {
        convertedGrades.clear();
        if (grades == null) {
            return convertedGrades;
        }
        for (Grade grade : grades) {
            double convertedValue = grade.convertToScale(targetScale);
            Grade converted = new Grade(
                    grade.getGradeId(),
                    grade.getCourse(),
                    convertedValue,
                    targetScale,
                    grade.getSemester(),
                    grade.getAcademicYear()
            );
            convertedGrades.add(converted);
        }
        this.status = "CONVERTED";
        this.conversionDate = new Date();
        return convertedGrades;
    }

    public void submitToDepartment() {
        this.status = "SUBMITTED";
    }

    public int getConversionId() { return conversionId; }
    public void setConversionId(int conversionId) { this.conversionId = conversionId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getSourceScale() { return sourceScale; }
    public void setSourceScale(String sourceScale) { this.sourceScale = sourceScale; }
    public String getTargetScale() { return targetScale; }
    public void setTargetScale(String targetScale) { this.targetScale = targetScale; }
    public Date getConversionDate() { return conversionDate; }
    public void setConversionDate(Date conversionDate) { this.conversionDate = conversionDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public ArrayList<Grade> getConvertedGrades() { return convertedGrades; }
    public void setConvertedGrades(ArrayList<Grade> convertedGrades) { this.convertedGrades = convertedGrades; }
}
