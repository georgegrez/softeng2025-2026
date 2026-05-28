package weerasmus.dto;

import java.util.ArrayList;
import weerasmus.model.CourseMapping;

public class MappingSearchResultDTO {
    private ArrayList<CourseMapping> mappings;

    public MappingSearchResultDTO(ArrayList<CourseMapping> mappings) {
        this.mappings = mappings == null ? new ArrayList<>() : mappings;
    }

    public boolean hasResults() {
        return !mappings.isEmpty();
    }

    public ArrayList<CourseMapping> getMappings() {
        return mappings;
    }
}
