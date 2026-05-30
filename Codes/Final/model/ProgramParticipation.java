package weerasmus.model;

import java.util.Date;

public class ProgramParticipation {
    private int participationId;
    private int studentId;
    private int programId;
    private Date startDate;
    private Date endDate;
    private ParticipationStatus status;

    public ProgramParticipation() {
        this.status = ParticipationStatus.ACTIVE;
    }

    public ProgramParticipation(int participationId, int studentId, int programId, Date startDate, Date endDate) {
        this.participationId = participationId;
        this.studentId = studentId;
        this.programId = programId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ParticipationStatus.ACTIVE;
    }

    public void suspend() { this.status = ParticipationStatus.SUSPENDED; }
    public void complete() { this.status = ParticipationStatus.COMPLETED; }
    public void withdraw() { this.status = ParticipationStatus.WITHDRAWN; }
    public boolean isActive() { return status == ParticipationStatus.ACTIVE; }

    public int getParticipationId() { return participationId; }
    public void setParticipationId(int participationId) { this.participationId = participationId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public int getProgramId() { return programId; }
    public void setProgramId(int programId) { this.programId = programId; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public ParticipationStatus getStatus() { return status; }
    public void setStatus(ParticipationStatus status) { this.status = status; }
}
