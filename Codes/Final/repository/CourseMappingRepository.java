package weerasmus.repository;

import java.util.ArrayList;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.Course;
import weerasmus.model.CourseMapping;

public class CourseMappingRepository {

    public ArrayList<CourseMapping> findAll() {
        return new ArrayList<>(InMemoryDatabase.courseMappings);
    }

    public CourseMapping findById(int mappingId) {
        for (CourseMapping mapping : InMemoryDatabase.courseMappings) {
            if (mapping.getMappingId() == mappingId) {
                return mapping;
            }
        }
        return null;
    }

    public ArrayList<CourseMapping> findByCourses(ArrayList<Course> courses) {
        ArrayList<CourseMapping> results = new ArrayList<>();

        if (courses == null) {
            return results;
        }

        for (CourseMapping mapping : InMemoryDatabase.courseMappings) {
            for (Course course : courses) {
                if (mapping.involvesCourse(course)) {
                    results.add(mapping);
                    break;
                }
            }
        }

        return results;
    }

    public CourseMapping save(CourseMapping mapping) {
        if (mapping == null) {
            return null;
        }

        if (mapping.getMappingId() == 0) {
            mapping.setMappingId(InMemoryDatabase.generateCourseMappingId());
            InMemoryDatabase.courseMappings.add(mapping);
            return mapping;
        }

        CourseMapping existing = findById(mapping.getMappingId());
        if (existing == null) {
            InMemoryDatabase.courseMappings.add(mapping);
        }

        return mapping;
    }

    public CourseMapping update(int mappingId, Course homeCourse, Course hostCourse, String comments) {
        CourseMapping mapping = findById(mappingId);

        if (mapping != null) {
            mapping.updateMapping(homeCourse, hostCourse, comments);
        }

        return mapping;
    }

    public boolean deleteById(int mappingId) {
        CourseMapping mapping = findById(mappingId);
        if (mapping == null) {
            return false;
        }
        return InMemoryDatabase.courseMappings.remove(mapping);
    }
}
