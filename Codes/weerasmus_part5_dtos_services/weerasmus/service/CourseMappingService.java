package weerasmus.service;

import java.util.ArrayList;
import weerasmus.dto.CourseMappingSearchCriteria;
import weerasmus.dto.MappingSearchResultDTO;
import weerasmus.dto.ValidationResult;
import weerasmus.model.Course;
import weerasmus.model.CourseMapping;
import weerasmus.repository.CourseMappingRepository;
import weerasmus.repository.CourseRepository;

public class CourseMappingService {
    private CourseRepository courseRepository;
    private CourseMappingRepository courseMappingRepository;

    public CourseMappingService(CourseRepository courseRepository, CourseMappingRepository courseMappingRepository) {
        this.courseRepository = courseRepository;
        this.courseMappingRepository = courseMappingRepository;
    }

    public MappingSearchResultDTO searchMappings(CourseMappingSearchCriteria criteria) {
        ArrayList<Course> courses = courseRepository.findCourses(criteria);
        ArrayList<CourseMapping> mappings = courseMappingRepository.findByCourses(courses);
        return new MappingSearchResultDTO(mappings);
    }

    public ValidationResult validateMapping(CourseMapping mapping) {
        ValidationResult result = ValidationResult.valid();

        if (mapping == null) {
            return ValidationResult.invalid("Η αντιστοίχιση δεν είναι έγκυρη.");
        }

        if (mapping.getHomeCourse() == null) result.addError("Το μάθημα προέλευσης είναι υποχρεωτικό.");
        if (mapping.getHostCourse() == null) result.addError("Το μάθημα υποδοχής είναι υποχρεωτικό.");

        return result;
    }
}
