package weerasmus.repository;

import java.util.ArrayList;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.Course;

public class CourseRepository {

    public ArrayList<Course> findAll() {
        return new ArrayList<>(InMemoryDatabase.courses);
    }

    public Course findById(int courseId) {
        for (Course course : InMemoryDatabase.courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }

    public ArrayList<Course> findCourses(String text, String department) {
        ArrayList<Course> results = new ArrayList<>();

        for (Course course : InMemoryDatabase.courses) {
            boolean matchesText = course.matchesTitle(text);
            boolean matchesDepartment = department == null || department.isBlank()
                    || course.getDepartment().equalsIgnoreCase(department);

            if (matchesText && matchesDepartment) {
                results.add(course);
            }
        }

        return results;
    }

    public Course save(Course course) {
        if (course == null) {
            return null;
        }

        if (course.getCourseId() == 0) {
            course.setCourseId(InMemoryDatabase.generateCourseId());
            InMemoryDatabase.courses.add(course);
            return course;
        }

        Course existing = findById(course.getCourseId());
        if (existing == null) {
            InMemoryDatabase.courses.add(course);
        }

        return course;
    }

    public ArrayList<Course> saveAll(ArrayList<Course> courses) {
        ArrayList<Course> saved = new ArrayList<>();

        if (courses == null) {
            return saved;
        }

        for (Course course : courses) {
            saved.add(save(course));
        }

        return saved;
    }
}
