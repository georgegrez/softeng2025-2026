package weerasmus.repository;

import java.util.ArrayList;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.ProgramParticipation;

public class ProgramParticipationRepository {

    public ArrayList<ProgramParticipation> findAll() {
        return new ArrayList<>(InMemoryDatabase.programParticipations);
    }

    public ProgramParticipation findById(int participationId) {
        for (ProgramParticipation participation : InMemoryDatabase.programParticipations) {
            if (participation.getParticipationId() == participationId) {
                return participation;
            }
        }
        return null;
    }

    public ProgramParticipation findByStudentAndProgram(int studentId, int programId) {
        for (ProgramParticipation participation : InMemoryDatabase.programParticipations) {
            if (participation.getStudentId() == studentId
                    && participation.getProgramId() == programId) {
                return participation;
            }
        }
        return null;
    }

    public ProgramParticipation save(ProgramParticipation participation) {
        if (participation == null) {
            return null;
        }

        if (participation.getParticipationId() == 0) {
            participation.setParticipationId(InMemoryDatabase.generateProgramParticipationId());
            InMemoryDatabase.programParticipations.add(participation);
            return participation;
        }

        ProgramParticipation existing = findById(participation.getParticipationId());
        if (existing == null) {
            InMemoryDatabase.programParticipations.add(participation);
        }

        return participation;
    }
}
