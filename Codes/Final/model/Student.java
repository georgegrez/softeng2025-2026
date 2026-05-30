package weerasmus.model;

import java.util.ArrayList;

public class Student extends User {
    private String registrationNumber;
    private String amka;
    private String department;
    private StudyLevel studyLevel;
    private String institutionalEmail;
    private ArrayList<String> languages;
    private ArrayList<String> favoriteCourses;
    private String specialization;
    private ArrayList<String> researchInterests;

    public Student() {
        this.languages = new ArrayList<>();
        this.favoriteCourses = new ArrayList<>();
        this.researchInterests = new ArrayList<>();
    }

    public Student(int userId, String firstName, String lastName, String idCardNumber,
                   String address, String phone, String email, String username,
                   String password, String verificationDocument, boolean verified,
                   String registrationNumber, String amka, String department,
                   StudyLevel studyLevel, String institutionalEmail) {
        super(userId, firstName, lastName, idCardNumber, address, phone, email,
                username, password, verificationDocument, verified);
        this.registrationNumber = registrationNumber;
        this.amka = amka;
        this.department = department;
        this.studyLevel = studyLevel;
        this.institutionalEmail = institutionalEmail;
        this.languages = new ArrayList<>();
        this.favoriteCourses = new ArrayList<>();
        this.researchInterests = new ArrayList<>();
    }

    public void addLanguage(String language) {
        if (language != null && !language.isBlank()) {
            languages.add(language);
        }
    }

    public void addFavoriteCourse(String courseTitle) {
        if (courseTitle != null && !courseTitle.isBlank()) {
            favoriteCourses.add(courseTitle);
        }
    }

    public void addResearchInterest(String interest) {
        if (interest != null && !interest.isBlank()) {
            researchInterests.add(interest);
        }
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public StudyLevel getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(StudyLevel studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String getInstitutionalEmail() {
        return institutionalEmail;
    }

    public void setInstitutionalEmail(String institutionalEmail) {
        this.institutionalEmail = institutionalEmail;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public ArrayList<String> getFavoriteCourses() {
        return favoriteCourses;
    }

    public void setFavoriteCourses(ArrayList<String> favoriteCourses) {
        this.favoriteCourses = favoriteCourses;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public ArrayList<String> getResearchInterests() {
        return researchInterests;
    }

    public void setResearchInterests(ArrayList<String> researchInterests) {
        this.researchInterests = researchInterests;
    }
}
