package weerasmus.model;

import java.util.ArrayList;
import java.util.Date;

public class CourseRecognitionRequest {
    private int requestId;
    private int studentId;
    private Date submissionDate;
    private ApplicationStatus status;
    private String comments;
    private ArrayList<CourseMapping> courseMappings;

    public CourseRecognitionRequest() {
        this.submissionDate = new Date();
        this.status = ApplicationStatus.PENDING;
        this.courseMappings = new ArrayList<>();
    }

    public CourseRecognitionRequest(int requestId, int studentId, String comments) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.comments = comments;
        this.submissionDate = new Date();
        this.status = ApplicationStatus.PENDING;
        this.courseMappings = new ArrayList<>();
    }

    public void submit() {
        this.submissionDate = new Date();
        this.status = ApplicationStatus.PENDING;
    }

    public void approve(String comments) {
        this.status = ApplicationStatus.APPROVED;
        this.comments = comments;
    }

    public void reject(String comments) {
        this.status = ApplicationStatus.REJECTED;
        this.comments = comments;
    }

    public void addCourseMapping(CourseMapping mapping) {
        if (mapping != null) {
            courseMappings.add(mapping);
        }
    }

    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public Date getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(Date submissionDate) { this.submissionDate = submissionDate; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public ArrayList<CourseMapping> getCourseMappings() { return courseMappings; }
    public void setCourseMappings(ArrayList<CourseMapping> courseMappings) { this.courseMappings = courseMappings; }
}
