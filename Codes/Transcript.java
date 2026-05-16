class Transcript
{
	private int transcriptId;
	
	private Date uploadDate;
	
	private double averageGrade;
	
	private int totalECTS;
	
	
	public Transcript(int transcriptId, Date uploadDate, double averageGrade, int totalECTS)
	{
		this.transcriptId = transcriptId;
		this.uploadDate = uploadDate;
		this.averageGrade = averageGrade;
		this.totalECTS = totalECTS;
	}
	
	
	public int get_transcriptId()
	{
		return transcriptId;
	}
	
	public void set_transcriptId(int i)
	{
		transcriptId = i;
	}
	
	public Date get_uploadDate()
	{
		return uploadDate;
	}
	
	public void set_uploadDate(Date d)
	{
		uploadDate = d;
	}
	
	public double get_averageGrade()
	{
		return averageGrade;
	}
	
	public void set_averageGrade(double d)
	{
		averageGrade = d;
	}
	
	public int get_totalECTS()
	{
		return totalECTS;
	}
	
	public void set_totalECTS(int i)
	{
		totalECTS = i;
	}
	
}