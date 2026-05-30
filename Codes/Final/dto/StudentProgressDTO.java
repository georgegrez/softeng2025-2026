package weerasmus.dto;

import java.util.ArrayList;
import weerasmus.model.Course;
import weerasmus.model.ProgramApplication;

public class StudentProgressDTO {
    private int studentId;
    private int programId;
    private ArrayList<Course> courses;
    private ArrayList<ProgramApplication> requests;
    private String progressNotes;

    public StudentProgressDTO(int studentId, int programId) {
        this.studentId = studentId;
        this.programId = programId;
        this.courses = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public int getProgramId() {
        return programId;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<ProgramApplication> getRequests() {
        return requests;
    }

    public String getProgressNotes() {
        return progressNotes;
    }

    public void setProgressNotes(String progressNotes) {
        this.progressNotes = progressNotes;
    }
}
