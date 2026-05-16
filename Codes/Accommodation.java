class Accomodation
{
	private int accommodationId;
	
	private String title;
	
	private String type;
	
	private String area;
	
	private double monthlyCost;
	
	private String rentalPeriod;
	
	private String paymentMethod;
	
	private double distanceFromUniversity;
	
	private boolean cohabitation;
	
	private String hostInfo;
	
	private List<String> amentities;
	
	private List<String> photos;
	
	private String availabilityStatus;
	
	public Accomodation(int accommodationId, String title, String type, String area, double monthlyCost, String rentalPeriod, String paymentMethod, double distanceFromUniversity, boolean cohabitation, String hostInfo, List<String> amentities, List<String> photos, String availabilityStatus)
	{
		this.accommodationId = accommodationId;
		this.title = title;
		this.type = type;
		this.area = area;
		this.monthlyCost = monthlyCost;
		this.rentalPeriod = rentalPeriod;
		this. paymentMethod = paymentMethod;
		this.distanceFromUniversity = distanceFromUniversity;
		this.cohabitation = cohabitation;
		this.hostInfo = hostInfo;
		this.amentities = amentities;
		this.photos = photos;
		this.availabilityStatus = availabilityStatus;
	}
	
	public int get_accommadationId()
	{
		return accommodationId;
	}
	
	public void set_accommadationId(int i)
	{
		accommodationId = i;
	}
	
	public String get_title()
	{
		return title;
	}
	
	public void set_title(String s)
	{
		title = s;
	}
	
	public String get_type()
	{
		return type;
	}
	
	public void set_type(String s)
	{
		type = s;
	}
	
	public String get_area()
	{
		return area;
	}
	
	public void set_area(String s)
	{
		area = s;
	}
	
	public double get_monthlyCost()
	{
		return monthlyCost;
	}
	
	public void set_monthlyCost(double d)
	{
		monthlyCost = d;
	}
	
	public String get_rentalPeriod()
	{
		return rentalPeriod;
	}
	
	public void set_rentalPeriod(String s)
	{
		rentalPeriod = s;
	}
	
	public String get_paymentMethod()
	{
		return paymentMethod;
	}
	
	public void set_paymentMethod(String s)
	{
		paymentMethod = s;
	}
	
	public double get_distanceFromUniversity()
	{
		return distanceFromUniversity;
	}
	
	public void set_distanceFromUniversity(double d)
	{
		distanceFromUniversity = d;
	}
	
	public boolean get_cohabitation()
	{
		return cohabitation;
	}
	
	public void set_cohabitation(boolean b)
	{
		cohabitation = b;
	}
	
	public String get_hostInfo()
	{
		return hostInfo;
	}
	
	public void set_hostInfo(String s)
	{
		hostInfo = s;
	}
	
	public List<String> get_amentities()
	{
		return amentities;
	}
	
	public void set_amentities(List<String> l)
	{
		amentities = l;
	}
	
	public List<String> get_photos()
	{
		return photos;
	}
	
	public void set_photos(List<String> l)
	{
		photos = l;
	}
	
	public String get_availabilityStatus()
	{
		return availabilityStatus;
	}
	
	public void set_availabilityStatus(String s)
	{
		availabilityStatus = s;
	}
}