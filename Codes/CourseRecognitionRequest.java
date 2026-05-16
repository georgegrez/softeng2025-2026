class CourseRecognitionRequest
{
  private int requestid;
  private Date submissionDate ;
  private String status;
  private String comments;

  public int get_requestid()
    {
       return requestid;

     }

  public void set_requestid(int i)  
   {
      requestid= i;
    }

  public Date get_submissionDate()
    {
       return submissionDate;

     }

  public void set_submissionDate(Date d)  
   {
      submissionDate= d;
    }

  public String get_status()
    {
       return status;

     }

  public void set_status(String s)  
   {
      status= s;
    }

  public String get_comments()
    {
       return comments;

     }

  public void set_comments(String s)  
   {
      comments= s;
    }
  
  public class CourseRecognitionRequest(int requestid , Date submissionDate , String status , String comments )
   {
      this.requestid=requestid;
      this.submissionDate = submissionDate;
      this.status = status;
      this.comments = comments;
 
   }


}