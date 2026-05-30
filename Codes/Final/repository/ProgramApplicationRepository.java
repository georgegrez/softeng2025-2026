package weerasmus.repository;

import java.util.ArrayList;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.ApplicationStatus;
import weerasmus.model.ProgramApplication;

public class ProgramApplicationRepository {

    public ArrayList<ProgramApplication> findAll() {
        return new ArrayList<>(InMemoryDatabase.programApplications);
    }

    public ProgramApplication findById(int applicationId) {
        for (ProgramApplication application : InMemoryDatabase.programApplications) {
            if (application.getApplicationId() == applicationId) {
                return application;
            }
        }
        return null;
    }

    public ArrayList<ProgramApplication> findByProgram(int programId) {
        ArrayList<ProgramApplication> results = new ArrayList<>();

        for (ProgramApplication application : InMemoryDatabase.programApplications) {
            if (application.getProgramId() == programId) {
                results.add(application);
            }
        }

        return results;
    }

    public ArrayList<ProgramApplication> findByStudent(int studentId) {
        ArrayList<ProgramApplication> results = new ArrayList<>();

        for (ProgramApplication application : InMemoryDatabase.programApplications) {
            if (application.getStudentId() == studentId) {
                results.add(application);
            }
        }

        return results;
    }

    public int countApprovedByProgram(int programId) {
        int count = 0;

        for (ProgramApplication application : InMemoryDatabase.programApplications) {
            if (application.getProgramId() == programId
                    && application.getStatus() == ApplicationStatus.APPROVED) {
                count++;
            }
        }

        return count;
    }

    public ProgramApplication save(ProgramApplication application) {
        if (application == null) {
            return null;
        }

        if (application.getApplicationId() == 0) {
            application.setApplicationId(InMemoryDatabase.generateProgramApplicationId());
            InMemoryDatabase.programApplications.add(application);
            return application;
        }

        ProgramApplication existing = findById(application.getApplicationId());
        if (existing == null) {
            InMemoryDatabase.programApplications.add(application);
        }

        return application;
    }

    public ArrayList<ProgramApplication> updateStatus(ArrayList<Integer> applicationIds, String status) {
        ArrayList<ProgramApplication> updated = new ArrayList<>();

        for (Integer id : applicationIds) {
            ProgramApplication application = findById(id);
            if (application != null) {
                application.setStatus(ApplicationStatus.valueOf(status));
                updated.add(application);
            }
        }

        return updated;
    }
}
