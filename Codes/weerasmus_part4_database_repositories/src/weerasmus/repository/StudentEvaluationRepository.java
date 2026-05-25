package weerasmus.repository;

import java.util.ArrayList;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.StudentEvaluation;

public class StudentEvaluationRepository {

    public ArrayList<StudentEvaluation> findAll() {
        return new ArrayList<>(InMemoryDatabase.studentEvaluations);
    }

    public StudentEvaluation findById(int evaluationId) {
        for (StudentEvaluation evaluation : InMemoryDatabase.studentEvaluations) {
            if (evaluation.getEvaluationId() == evaluationId) {
                return evaluation;
            }
        }
        return null;
    }

    public ArrayList<StudentEvaluation> findByStudent(int studentId) {
        ArrayList<StudentEvaluation> results = new ArrayList<>();

        for (StudentEvaluation evaluation : InMemoryDatabase.studentEvaluations) {
            if (evaluation.getStudentId() == studentId) {
                results.add(evaluation);
            }
        }

        return results;
    }

    public StudentEvaluation save(StudentEvaluation evaluation) {
        if (evaluation == null) {
            return null;
        }

        if (evaluation.getEvaluationId() == 0) {
            evaluation.setEvaluationId(InMemoryDatabase.generateStudentEvaluationId());
            InMemoryDatabase.studentEvaluations.add(evaluation);
            return evaluation;
        }

        StudentEvaluation existing = findById(evaluation.getEvaluationId());
        if (existing == null) {
            InMemoryDatabase.studentEvaluations.add(evaluation);
        }

        return evaluation;
    }
}
