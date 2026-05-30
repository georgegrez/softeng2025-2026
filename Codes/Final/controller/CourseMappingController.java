package weerasmus.controller;

import weerasmus.dto.CourseMappingSearchCriteria;
import weerasmus.dto.MappingSearchResultDTO;
import weerasmus.dto.ValidationResult;
import weerasmus.model.CourseMapping;
import weerasmus.repository.CourseMappingRepository;
import weerasmus.service.CourseMappingService;
import weerasmus.ui.CourseMappingsFrame;
import weerasmus.ui.EditCourseMappingFrame;

public class CourseMappingController {
    private final CourseMappingRepository courseMappingRepository;
    private final CourseMappingService courseMappingService;

    public CourseMappingController(CourseMappingRepository courseMappingRepository,
                                   CourseMappingService courseMappingService) {
        this.courseMappingRepository = courseMappingRepository;
        this.courseMappingService = courseMappingService;
    }

    public void openCourseMappingsFrame(int professorId) {
        CourseMappingsFrame frame = new CourseMappingsFrame(this, professorId);
        frame.showFrame(professorId);
    }

    public MappingSearchResultDTO searchMappingsByCourseName(int professorId, String courseName) {
        CourseMappingSearchCriteria criteria = new CourseMappingSearchCriteria();
        criteria.setCourseName(courseName);
        return courseMappingService.searchMappings(criteria);
    }

    public MappingSearchResultDTO searchMappingsByFilters(int professorId, CourseMappingSearchCriteria filters) {
        return courseMappingService.searchMappings(filters);
    }

    public void openEditCourseMappingFrame(int mappingId) {
        CourseMapping mapping = courseMappingRepository.findById(mappingId);
        if (mapping != null) {
            EditCourseMappingFrame frame = new EditCourseMappingFrame(this);
            frame.showFrame(mapping);
        }
    }

    public ValidationResult updateMapping(int mappingId, CourseMapping mappingData) {
        ValidationResult result = courseMappingService.validateMapping(mappingData);
        if (!result.isValid()) {
            return result;
        }

        courseMappingRepository.update(mappingId, mappingData);
        return ValidationResult.valid();
    }

    public void deleteMapping(int mappingId) {
        courseMappingRepository.deleteById(mappingId);
    }

    public void cancelEditMapping(int professorId) {
        openCourseMappingsFrame(professorId);
    }
}
