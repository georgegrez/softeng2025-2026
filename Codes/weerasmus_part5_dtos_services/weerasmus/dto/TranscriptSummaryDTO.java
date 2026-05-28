package weerasmus.dto;

public class TranscriptSummaryDTO {
    private int transcriptId;
    private double averageGrade;
    private int totalECTS;
    private int failedCourses;

    public TranscriptSummaryDTO(int transcriptId, double averageGrade, int totalECTS, int failedCourses) {
        this.transcriptId = transcriptId;
        this.averageGrade = averageGrade;
        this.totalECTS = totalECTS;
        this.failedCourses = failedCourses;
    }

    public int getTranscriptId() {
        return transcriptId;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public int getTotalECTS() {
        return totalECTS;
    }

    public int getFailedCourses() {
        return failedCourses;
    }
}
