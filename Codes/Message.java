class Message 
{
  private int messageid ;
  private String content;
  private DateTime sentAt;
  private boolean isRead ;

  public int get_messageid()
    {
       return messageid;

     }

  public void set_messageid(int i)  
   {
      messageid = i;
    }
 
  public String get_content()
    {
       return content;

     }

  public void set_content(String s)  
   {
      content = s;
    }

  public DateTime get_sentAt()
    {
       return sentAt;

     }

  public void set_sentAt(DateTime d)  
   {
      sentAt = d;
    }

   public boolean get_isRead()
    {
       return isRead;

     }

  public void set_isRead(boolean b)  
   {
      isRead = b;
    }


  public Message(int messageid , String content , DateTime sentAt ,  boolean isRead )

  {
     this.messageid = messageid;
     this.content = content;
     this DateTime = DateTime;
     this isRead = isRead;

   }


}
