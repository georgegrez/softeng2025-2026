import java.util.List;

public class Student extends User {
    private String registrationNumber;
    private String amka;
    private String department;
    private String studyLevel;
    private String institutionalEmail;
    private List<String> languages;
    private List<String> favoriteCourses;
    private String specialization;
    private List<String> researchInterests;

    public Student() {
        super();
    }

    public Student(int userId, String firstName, String lastName, String idCardNumber,
                   String address, String phone, String email, String username,
                   String password, String verificationDocument, boolean isVerified,
                   String registrationNumber, String amka, String department,
                   String studyLevel, String institutionalEmail, List<String> languages,
                   List<String> favoriteCourses, String specialization,
                   List<String> researchInterests) {
        super(userId, firstName, lastName, idCardNumber, address, phone, email,
              username, password, verificationDocument, isVerified);
        this.registrationNumber = registrationNumber;
        this.amka = amka;
        this.department = department;
        this.studyLevel = studyLevel;
        this.institutionalEmail = institutionalEmail;
        this.languages = languages;
        this.favoriteCourses = favoriteCourses;
        this.specialization = specialization;
        this.researchInterests = researchInterests;
    }

    public String get_registrationNumber() {
        return registrationNumber;
    }

    public void set_registrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String get_amka() {
        return amka;
    }

    public void set_amka(String amka) {
        this.amka = amka;
    }

    public String get_department() {
        return department;
    }

    public void set_department(String department) {
        this.department = department;
    }

    public String get_studyLevel() {
        return studyLevel;
    }

    public void set_studyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String get_institutionalEmail() {
        return institutionalEmail;
    }

    public void set_institutionalEmail(String institutionalEmail) {
        this.institutionalEmail = institutionalEmail;
    }

    public List<String> get_languages() {
        return languages;
    }

    public void set_languages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> get_favoriteCourses() {
        return favoriteCourses;
    }

    public void set_favoriteCourses(List<String> favoriteCourses) {
        this.favoriteCourses = favoriteCourses;
    }

    public String get_specialization() {
        return specialization;
    }

    public void set_specialization(String specialization) {
        this.specialization = specialization;
    }

    public List<String> get_researchInterests() {
        return researchInterests;
    }

    public void set_researchInterests(List<String> researchInterests) {
        this.researchInterests = researchInterests;
    }
}