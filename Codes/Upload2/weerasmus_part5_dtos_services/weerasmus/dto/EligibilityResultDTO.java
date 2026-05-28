package weerasmus.dto;

public class EligibilityResultDTO {
    private int resultId;
    private boolean eligible;
    private String message;

    public EligibilityResultDTO(int resultId, boolean eligible, String message) {
        this.resultId = resultId;
        this.eligible = eligible;
        this.message = message;
    }

    public int getResultId() {
        return resultId;
    }

    public boolean isEligible() {
        return eligible;
    }

    public String getMessage() {
        return message;
    }
}
