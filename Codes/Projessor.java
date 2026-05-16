public class Professor extends User {
    private String responsibleDepartment;

    public Professor() {
        super();
    }

    public Professor(int userId, String firstName, String lastName, String idCardNumber,
                     String address, String phone, String email, String username,
                     String password, String verificationDocument, boolean isVerified,
                     String responsibleDepartment) {
        super(userId, firstName, lastName, idCardNumber, address, phone, email,
              username, password, verificationDocument, isVerified);
        this.responsibleDepartment = responsibleDepartment;
    }

    public String get_responsibleDepartment() {
        return responsibleDepartment;
    }

    public void set_responsibleDepartment(String responsibleDepartment) {
        this.responsibleDepartment = responsibleDepartment;
    }
}