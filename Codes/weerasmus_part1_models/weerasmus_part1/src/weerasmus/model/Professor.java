package weerasmus.model;

public class Professor extends User {
    private String responsibleDepartment;

    public Professor() {
    }

    public Professor(int userId, String firstName, String lastName, String idCardNumber,
                     String address, String phone, String email, String username,
                     String password, String verificationDocument, boolean verified,
                     String responsibleDepartment) {
        super(userId, firstName, lastName, idCardNumber, address, phone, email,
                username, password, verificationDocument, verified);
        this.responsibleDepartment = responsibleDepartment;
    }

    public String getResponsibleDepartment() {
        return responsibleDepartment;
    }

    public void setResponsibleDepartment(String responsibleDepartment) {
        this.responsibleDepartment = responsibleDepartment;
    }
}
