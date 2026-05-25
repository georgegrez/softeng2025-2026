package weerasmus.model;

import java.util.ArrayList;
import java.util.Date;

public class Transcript {
    private int transcriptId;
    private int studentId;
    private Date uploadDate;
    private double averageGrade;
    private int totalECTS;
    private ArrayList<Grade> grades;

    public Transcript() {
        this.uploadDate = new Date();
        this.grades = new ArrayList<>();
    }

    public Transcript(int transcriptId, int studentId) {
        this.transcriptId = transcriptId;
        this.studentId = studentId;
        this.uploadDate = new Date();
        this.grades = new ArrayList<>();
    }

    public void addGrade(Grade grade) {
        if (grade != null) {
            grades.add(grade);
            recalculateStatistics();
        }
    }

    public void recalculateStatistics() {
        calculateAverageGrade();
        calculateTotalECTS();
    }

    public double calculateAverageGrade() {
        if (grades.isEmpty()) {
            averageGrade = 0.0;
            return averageGrade;
        }
        double sum = 0.0;
        for (Grade grade : grades) sum += grade.getValue();
        averageGrade = sum / grades.size();
        return averageGrade;
    }

    public int calculateTotalECTS() {
        int sum = 0;
        for (Grade grade : grades) {
            if (grade.getCourse() != null) sum += grade.getCourse().getEcts();
        }
        totalECTS = sum;
        return totalECTS;
    }

    public int getFailedCoursesCount() {
        int count = 0;
        for (Grade grade : grades) {
            if (grade.getValue() < 5.0) count++;
        }
        return count;
    }

    public int getTranscriptId() { return transcriptId; }
    public void setTranscriptId(int transcriptId) { this.transcriptId = transcriptId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public Date getUploadDate() { return uploadDate; }
    public void setUploadDate(Date uploadDate) { this.uploadDate = uploadDate; }
    public double getAverageGrade() { return averageGrade; }
    public void setAverageGrade(double averageGrade) { this.averageGrade = averageGrade; }
    public int getTotalECTS() { return totalECTS; }
    public void setTotalECTS(int totalECTS) { this.totalECTS = totalECTS; }
    public ArrayList<Grade> getGrades() { return grades; }
    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades != null ? grades : new ArrayList<>();
        recalculateStatistics();
    }
}
