class ProgramEvaluation
{
	private int evaluationId;
	
	private int rating;
	
	private List<String> answers;
	
	private int platformRating;
	
	private String comments;
	
	private Date submissionDate;
	
	public ProgramEvaluation(int evaluationId, int rating, List<String> answers, int platformRating, String comments, Date submissionDate)
	{
		this.evaluationId = evaluationId;
		this.rating = rating;
		this.answers = answers;
		this.platformRating = platformRating;
		this.comments = comments;
		this.submissionDate= submissionDate;
	}
	
	
	
	public int get_evaluationId()
	{
		return evaluationId;
	}
	
	public void set_evaluationId(int i)
	{
		evaluationId = i;
	}
	
	public int get_rating()
	{
		return rating;
	}
	
	public void set_rating(int i)
	{
		rating = i;
	}
	
	public List<String> get_answers()
	{
		return answers;
	}
	
	public void set_answers(List<String> l)
	{
		answers = l;
	}
	
	public int get_platformRating()
	{
		return platformRating;
	}
	
	public void set_platformRating(int i)
	{
		platformRating = i;
	}
	
	
	public String get_comments()
	{
		return comments;
	}
	
	public void set_comments(String s)
	{
		comments = s;
	}
	
	public Date get_submissionDate()
	{
		return submissionDate;
	}
	
	public void set_submissionDate(Date d)
	{
		submissionDate = d;
	}
	
	
}