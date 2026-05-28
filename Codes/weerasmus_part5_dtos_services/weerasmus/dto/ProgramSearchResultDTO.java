package weerasmus.dto;

import java.util.ArrayList;
import weerasmus.model.ErasmusProgram;

public class ProgramSearchResultDTO {
    private ArrayList<ErasmusProgram> results;
    private String message;

    public ProgramSearchResultDTO() {
        this.results = new ArrayList<>();
    }

    public ProgramSearchResultDTO(ArrayList<ErasmusProgram> results, String message) {
        this.results = results == null ? new ArrayList<>() : results;
        this.message = message;
    }

    public boolean hasResults() {
        return results != null && !results.isEmpty();
    }

    public ArrayList<ErasmusProgram> getResults() {
        return results;
    }

    public void setResults(ArrayList<ErasmusProgram> results) {
        this.results = results;
    }

    public String getMessage() {
        return message;
    }
}
