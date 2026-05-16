import java.util.List;

public class User {
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
    private boolean isVerified;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String idCardNumber,
                String address, String phone, String email, String username,
                String password, String verificationDocument, boolean isVerified) {
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
        this.isVerified = isVerified;
    }

    public int get_userId() {
        return userId;
    }

    public void set_userId(int userId) {
        this.userId = userId;
    }

    public String get_firstName() {
        return firstName;
    }

    public void set_firstName(String firstName) {
        this.firstName = firstName;
    }

    public String get_lastName() {
        return lastName;
    }

    public void set_lastName(String lastName) {
        this.lastName = lastName;
    }

    public String get_idCardNumber() {
        return idCardNumber;
    }

    public void set_idCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String get_address() {
        return address;
    }

    public void set_address(String address) {
        this.address = address;
    }

    public String get_phone() {
        return phone;
    }

    public void set_phone(String phone) {
        this.phone = phone;
    }

    public String get_email() {
        return email;
    }

    public void set_email(String email) {
        this.email = email;
    }

    public String get_username() {
        return username;
    }

    public void set_username(String username) {
        this.username = username;
    }

    public String get_password() {
        return password;
    }

    public void set_password(String password) {
        this.password = password;
    }

    public String get_verificationDocument() {
        return verificationDocument;
    }

    public void set_verificationDocument(String verificationDocument) {
        this.verificationDocument = verificationDocument;
    }

    public boolean get_isVerified() {
        return isVerified;
    }

    public void set_isVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }
}