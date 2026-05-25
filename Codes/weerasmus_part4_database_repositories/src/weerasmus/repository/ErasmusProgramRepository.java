package weerasmus.repository;

import java.util.ArrayList;
import java.util.Date;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.ErasmusProgram;
import weerasmus.model.StudyLevel;

public class ErasmusProgramRepository {

    public ArrayList<ErasmusProgram> findAll() {
        return new ArrayList<>(InMemoryDatabase.programs);
    }

    public ErasmusProgram findById(int programId) {
        for (ErasmusProgram program : InMemoryDatabase.programs) {
            if (program.getProgramId() == programId) {
                return program;
            }
        }
        return null;
    }

    public ErasmusProgram save(ErasmusProgram program) {
        if (program == null) {
            return null;
        }

        if (program.getProgramId() == 0) {
            program.setProgramId(InMemoryDatabase.generateProgramId());
            InMemoryDatabase.programs.add(program);
            return program;
        }

        ErasmusProgram existing = findById(program.getProgramId());
        if (existing == null) {
            InMemoryDatabase.programs.add(program);
        }

        return program;
    }

    public ArrayList<ErasmusProgram> findAvailableByCriteria(String text, String country,
                                                             String university, StudyLevel studyLevel) {
        ArrayList<ErasmusProgram> results = new ArrayList<>();

        for (ErasmusProgram program : InMemoryDatabase.programs) {
            if (program.matchesCriteria(text, country, university, studyLevel)
                    && program.isApplicationOpen(new Date())
                    && program.hasAvailablePositions()) {
                results.add(program);
            }
        }

        return results;
    }

    public ArrayList<ErasmusProgram> findActiveProgramsByProfessor(int professorId) {
        ArrayList<ErasmusProgram> results = new ArrayList<>();

        /*
         * In this simplified ArrayList implementation, ErasmusProgram does not store professorId.
         * The professor-program relation can be handled later through service-level rules
         * or by adding a responsibleProfessorId field if needed.
         */
        results.addAll(InMemoryDatabase.programs);
        return results;
    }

    public ErasmusProgram findManagedProgram(int professorId, int programId) {
        return findById(programId);
    }

    public ErasmusProgram updateProgram(int programId, Date newDeadline, int newAvailablePositions) {
        ErasmusProgram program = findById(programId);

        if (program != null) {
            program.setApplicationDeadline(newDeadline);
            program.setAvailablePositions(newAvailablePositions);
        }

        return program;
    }

    public boolean deleteById(int programId) {
        ErasmusProgram program = findById(programId);
        if (program == null) {
            return false;
        }
        return InMemoryDatabase.programs.remove(program);
    }
}
