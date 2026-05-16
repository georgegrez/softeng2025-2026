public class Installment {
 
    private int installmentNumber;
    private Date dueDate;
    private double installmentAmount;
    private boolean isPaid;

public Installment( int installmentNumber,Date dueDate, double installmentAmount, boolean isPaid){ 
                        
        this.installmentNumber = installmentNumber;
        this.dueDate = dueDate;
        this.installmentAmount = installmentAmount;
        this.isPaid = isPaid;
    }

    public int get_installmentNumber() {
        return installmentNumber;
    }

    public void set_installmentNumber(int n) {
        installmentNumber = n;
    }

    public Date get_dueDate() {
        return dueDate;
    }

    public void set_dueDate(Date d) {
        dueDate = d;
    }

    public double get_installmentAmount() {
        return installmentAmount;
    }

    public void set_installmentAmount(double a) {
        installmentAmount = a;
    }

    public boolean get_isPaid() {
        return isPaid;
    }

    public void set_isPaid(boolean p) {
        isPaid = p;
    }

}
