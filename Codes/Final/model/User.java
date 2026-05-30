package weerasmus.model;

public abstract class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String idCardNumber;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String verificationDocument;
    private boolean verified;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String idCardNumber,
                String address, String phone, String email, String username,
                String password, String verificationDocument, boolean verified) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idCardNumber = idCardNumber;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.verificationDocument = verificationDocument;
        this.verified = verified;
    }

    public boolean login(String username, String password) {
        return this.username != null
                && this.password != null
                && this.username.equals(username)
                && this.password.equals(password);
    }

    public void logout() {
        // In Swing, logout/navigation is handled by the controller.
    }

    public void uploadVerificationDocument(String filePath) {
        this.verificationDocument = filePath;
        this.verified = false;
    }

    public void updatePersonalInfo(String firstName, String lastName, String address,
                                   String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationDocument() {
        return verificationDocument;
    }

    public void setVerificationDocument(String verificationDocument) {
        this.verificationDocument = verificationDocument;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
