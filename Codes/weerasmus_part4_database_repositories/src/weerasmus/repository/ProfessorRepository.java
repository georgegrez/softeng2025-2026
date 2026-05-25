package weerasmus.repository;

import java.util.ArrayList;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.Professor;

public class ProfessorRepository {

    public ArrayList<Professor> findAll() {
        return new ArrayList<>(InMemoryDatabase.professors);
    }

    public Professor findById(int professorId) {
        for (Professor professor : InMemoryDatabase.professors) {
            if (professor.getUserId() == professorId) {
                return professor;
            }
        }
        return null;
    }

    public Professor findByUsername(String username) {
        for (Professor professor : InMemoryDatabase.professors) {
            if (professor.getUsername() != null && professor.getUsername().equals(username)) {
                return professor;
            }
        }
        return null;
    }

    public boolean existsByUsername(String username) {
        return findByUsername(username) != null;
    }

    public Professor save(Professor professor) {
        if (professor == null) {
            return null;
        }

        if (professor.getUserId() == 0) {
            professor.setUserId(InMemoryDatabase.generateProfessorId());
            InMemoryDatabase.professors.add(professor);
            return professor;
        }

        Professor existing = findById(professor.getUserId());
        if (existing == null) {
            InMemoryDatabase.professors.add(professor);
        }

        return professor;
    }
}
