
public class CourseMapping {

    private int mappingId; 
    private String homeCourseName;  
    private String hostCourseName;  
    private String mappingStatus;  
    private String comments;

public CourseMapping(int mappingId, String homeCourseName, String hostCourseName,String mappingStatus, String comments) {
        this.mappingId = mappingId;
        this.homeCourseName = homeCourseName;
        this.hostCourseName = hostCourseName;
        this.mappingStatus = mappingStatus;
        this.comments = comments;
    }

    public int get_mappingId() {
        return mappingId;
    }

    public void set_mappingId(int id) {
        mappingId = id;
    }

    public String get_homeCourseName() {
        return homeCourseName;
    }

    public void set_homeCourseName(String n) {
        homeCourseName = n;
    }

    public String get_hostCourseName() {
        return hostCourseName;
    }

    public void set_hostCourseName(String n) {
        hostCourseName = n;
    }

    public String get_mappingStatus() {
        return mappingStatus;
    }

    public void set_mappingStatus(String s) {
        mappingStatus = s;
    }

    public String get_comments() {
        return comments;
    }

    public void set_comments(String c) {
        comments = c;
    }
} 