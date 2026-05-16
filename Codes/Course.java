
class Course
{
 private int courseid;
 private String courseCode;
 private String title;
 private int ects;
 private String description;
 private String department;
 private String gradeScale;

 
   public int get_courseid()
    {
       return courseid;

     }

  public void set_courseid(int i)  
   {
      courseid = i;
    }


   public String get_courseCode()
    {
       return courseCode;

     }

  public void set_courseCode(String s)  
   {
      courseCode = s;
    }


   public String get_title()
    {
       return title;

     }

  public void set_title(String s)  
   {
      title = s;
    }


   public int get_ects()
    {
       return ects;

     }

  public void set_ects(int i)  
   {
      ects = i;
    }
 
  
   public String get_description()
    {
       return description;

     }

  public void set_description(String s)  
   {
      description = s;
    }

  public String get_department()
    {
       return department;

     }

  public void set_department(String s)  
   {
      department = s;
    }

  public String get_gradeScale()
    {
       return gradeScale;

     }

  public void set_gradeScale(String s)  
   {
      gradeScale = s;
    }

  public class Course(int courseid , String courseCode , String title , int ects , String description , String department , String gradeScale)
  {
     this.courseid = courseid;
     this.courseCode = courseCode;
     this.title = title ;
     this.ects = ects;
     this.description = description;
     this.department = department;
     this.gradeScale= gradeScale;  

  }

}