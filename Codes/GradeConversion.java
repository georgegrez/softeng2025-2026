class GradeConversion
{
  private int conversionid;
  private String sourseScale;
  private String targetScale;
  private Date conversionDate;
  private String status;

   public int get_conversionid()
    {
       return conversionid;

     }

  public void set_studylevel(int i)  
   {
      conversionid = i;
    }
   
    public String get_sourseScale()
    {
       return sourseScale;

     }

  public void set_sourseScale(String s)  
   {
      sourseScale = s;
    }
  
   public String get_targetScale()
    {
       return targetScale;

     }

  public void set_targetScale(String s)  
   {
      targetScale = s;
    }

   public Date get_conversionDate()
    {
       return conversionDate;

     }

  public void set_conversionDate(Date d)  
   {
      conversionDate = d;
    }

   public String get_status()
    {
       return status;

     }

  public void set_status(String s)  
   {
      status= s;
    }


 public GradeConversion(int conversionid , String sourseScale , String targetScale , Date conversionDate , String status )
 {
    this.conversionid = conversionid;
    this.sourseScale = sourseScale;
    this.targetScale = targetScale;
    this.conversionDate = conversionDate;
    this.status = status;

  }


}
