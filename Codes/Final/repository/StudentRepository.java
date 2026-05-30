package weerasmus.repository;

import java.util.ArrayList;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.ParticipationStatus;
import weerasmus.model.ProgramParticipation;
import weerasmus.model.Student;

public class StudentRepository {

    public ArrayList<Student> findAll() {
        return new ArrayList<>(InMemoryDatabase.students);
    }

    public Student findById(int studentId) {
        for (Student student : InMemoryDatabase.students) {
            if (student.getUserId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public Student findByUsername(String username) {
        for (Student student : InMemoryDatabase.students) {
            if (student.getUsername() != null && student.getUsername().equals(username)) {
                return student;
            }
        }
        return null;
    }

    public boolean existsByUsername(String username) {
        return findByUsername(username) != null;
    }

    public Student save(Student student) {
        if (student == null) {
            return null;
        }

        if (student.getUserId() == 0) {
            student.setUserId(InMemoryDatabase.generateStudentId());
            InMemoryDatabase.students.add(student);
            return student;
        }

        Student existing = findById(student.getUserId());
        if (existing == null) {
            InMemoryDatabase.students.add(student);
        }

        return student;
    }

    public boolean deleteById(int studentId) {
        Student student = findById(studentId);
        if (student == null) {
            return false;
        }
        return InMemoryDatabase.students.remove(student);
    }

    public ArrayList<Student> findActiveByProgram(int programId) {
        ArrayList<Student> results = new ArrayList<>();

        for (ProgramParticipation participation : InMemoryDatabase.programParticipations) {
            if (participation.getProgramId() == programId
                    && participation.getStatus() == ParticipationStatus.ACTIVE) {
                Student student = findById(participation.getStudentId());
                if (student != null) {
                    results.add(student);
                }
            }
        }

        return results;
    }

    public void updateParticipationStatus(int studentId, int programId, String status) {
        for (ProgramParticipation participation : InMemoryDatabase.programParticipations) {
            if (participation.getStudentId() == studentId
                    && participation.getProgramId() == programId) {
                participation.setStatus(ParticipationStatus.valueOf(status));
                return;
            }
        }
    }
}
