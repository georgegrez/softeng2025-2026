class StudentEvaluation
{
	private int evaluationId;
	
	private List<int> criteriaScores;
	
	private String comments;
	
	private Date submissionDate;
	
	public StudentEvaluation(int evaluationId, List<int> criteriaScores, String comments, Date submissionDate )
	{
		this.evaluationId = evaluationId;
		this.criteriaScores = criteriaScores;
		this.comments = comments;
		this.submissionDate = submissionDate;
	}
	
	
	
	public int get_evaluationId()
	{
		return evaluationId;
	}
	
	public void set_evaluationId(int i)
	{
		evaluationId = i;
	}
	
	public List<int> get_criteriaScores()
	{
		return criteriaScores;
	}
	
	public void set_criteriaScores(List<int> l)
	{
		criteriaScores = l;
	}
	
	public String get_comments()
	{
		return comments;
	}
	
	public void set_comments(String s)
	{
		comments = s;
	}
	
	public Date getsubmissionDate()
	{
		return submissionDate;
	}
	
	public void set_submissionDate(String s)
	{
		submissionDate = s;
	}
	
}