public class HousingApplication {

    private int housingApplicationId;
    private Date submissionDate;
    private String status;
    private double totalAmount;
    private String paymentMethod;

public HousingApplication(int housingApplicationId, Date submissionDate, String status, 
                              double totalAmount, String paymentMethod) {
        this.housingApplicationId = housingApplicationId;
        this.submissionDate = submissionDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
       
    }

    public int get_housingApplicationId() {
        return housingApplicationId;
    }

    public void set_housingApplicationId(int id) {
        housingApplicationId = id;
    }

    public Date get_submissionDate() {
        return submissionDate;
    }

    public void set_submissionDate(Date d) {
        submissionDate = d;
    }

    public String get_status() {
        return status;
    }

    public void set_status(String s) {
        status = s;
    }

    public double get_totalAmount() {
        return totalAmount;
    }

    public void set_totalAmount(double a) {
        totalAmount = a;
    }

    public String get_paymentMethod() {
        return paymentMethod;
    }

    public void set_paymentMethod(String p) {
        paymentMethod = p;
    }


}