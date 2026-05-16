class EligibilityRule
{
  private int ruleid;
  private int minECTS;
  private String requiredStudyLevel;
  private List requiredLanquages;
  private String additionalRequirements;

  public int get_ruleid()
    {
       return ruleid;

     }

  public void set_ruleid(int i)  
   {
      ruleid= i;
    }

  public int get_minECTS()
    {
       return minECTS;

     }

  public void set_minECTS(int i)  
   {
      minECTS = i;
    }


  public String get_requiredStudyLevel()
    {
       return requiredStudyLevel;

     }

  public void set_requiredStudyLevel(String s)  
   {
      requiredStudyLevel = s;
    }


  public List get_requiredLanquages()
    {
       return requiredLanquages;

     }

  public void set_requiredLanquages(String s)  
   {
      requiredLanquages = s;
    }


  public String get_additionalRequirements()
    {
       return additionalRequirements;

     }

  public void set_additionalRequirements(String s)  
   {
      additionalRequirements = s;
    }

  public class EligibilityRule(int ruleid , int minECTS , String requiredStudyLevel ,  List requiredLanguages , String additionalRequirements)
  {
    this.ruleid = ruleid;
    this.minECTS = minECTS;
    this.requiredStudyLevel = requiredStusdyLevel;
    this.requiredLanguages  = requiredLanguages;
    this.additionalRequirements = additionalRequirements;
 
  }

}