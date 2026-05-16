class Payment
{
	private int paymentId;
	
	private double amount;
	
	private Date paymentDate;
	
	private String paymentMethod;
	
	private String status;
	
	private String receiptNumber;
	
	public Payment(int paymentId, double amount, Date paymentDate, String paymentMethod, String status, String receiptNumber)
	{
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.receiptNumber = receiptNumber;
	}
	
	
	public int get_paymentId()
	{
		return paymentId;
	}
	
	public void set_paymentID(int i)
	{
		paymentId = i;
	}
	
	public double get_amount()
	{
		return amount;
	}
	
	public void set_amount(double d)
	{
		amount = d;
	}
	
	public Date get_paymentDate()
	{
		return paymentDate;
	}
	
	public void set_paymentDate(Date d)
	{
		paymentDate = d;
	}
	
	public String get_paymentMethod()
	{
		return paymentMethod;
	}
	
	public void set_paymentMethod(String s)
	{
		paymentMethod = s;
	}
	
	public String get_status()
	{
		return status;
	}
	
	public void set_status(String s)
	{
		status = s;
	}
	
	public String get_receiptNumber()
	{
		return receiptNumber;
	}
	
	public void set_receiptNumber(String s)
	{
		receiptNumber = s;
	}
}
