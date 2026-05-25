package weerasmus.repository;

import java.util.ArrayList;

import weerasmus.database.InMemoryDatabase;
import weerasmus.model.HousingApplication;

public class HousingApplicationRepository {

    public ArrayList<HousingApplication> findAll() {
        return new ArrayList<>(InMemoryDatabase.housingApplications);
    }

    public HousingApplication findById(int housingApplicationId) {
        for (HousingApplication application : InMemoryDatabase.housingApplications) {
            if (application.getHousingApplicationId() == housingApplicationId) {
                return application;
            }
        }
        return null;
    }

    public ArrayList<HousingApplication> findByStudent(int studentId) {
        ArrayList<HousingApplication> results = new ArrayList<>();

        for (HousingApplication application : InMemoryDatabase.housingApplications) {
            if (application.getStudentId() == studentId) {
                results.add(application);
            }
        }

        return results;
    }

    public HousingApplication save(HousingApplication application) {
        if (application == null) {
            return null;
        }

        if (application.getHousingApplicationId() == 0) {
            application.setHousingApplicationId(InMemoryDatabase.generateHousingApplicationId());
            InMemoryDatabase.housingApplications.add(application);
            return application;
        }

        HousingApplication existing = findById(application.getHousingApplicationId());
        if (existing == null) {
            InMemoryDatabase.housingApplications.add(application);
        }

        return application;
    }
}
