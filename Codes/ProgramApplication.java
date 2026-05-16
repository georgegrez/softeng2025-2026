class ProgramApplication
{
	private int applicationId;
	
	private Date submissionDate;
	
	private String status;
	
	private String comments;
	
	private String motivationLetter;
	
	public ProgramApplication(int applicationId, Date submissionDate, String status, String comments, String motivationLetter)
	{
		this.applicationId = applicationId;
		this.submissionDate = submissionDate;
		this.status = status;
		this.comments = comments;
		this.motivationLetter = motivationLetter;
	}
	
	
	
	public int get_applicationId()
	{
		return applicationId;
	}
	
	public void set_applicationId(int i)
	{
		applicationId =i;
	}
	
	public Date get_submissionDate()
	{
		return submissionDate;
	}
	
	public void set_submissionDate(Date d)
	{
		submissionDate = d;
	}
	
	publi String get_status()
	{
		return status;
	}
	
	public void set_status(String s)
	{
		status = s;
	}
	
	public String get_comments()
	{
		return comments;
	}
	
	public void set_comments(String s)
	{
		comments = s;
	}
	
	public String get_motivationLetter()
	{
		return motivationLetter;
	}
	
	public void set_motivationLetter(String s)
	{
		motivationLetter = s;
	}
	
	
}