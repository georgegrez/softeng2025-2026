package weerasmus.model;

public class CourseMapping {
    private int mappingId;
    private Course homeCourse;
    private Course hostCourse;
    private String homeCourseName;
    private String hostCourseName;
    private MappingStatus mappingStatus;
    private String comments;

    public CourseMapping() {
        this.mappingStatus = MappingStatus.PENDING_REVIEW;
    }

    public CourseMapping(int mappingId, Course homeCourse, Course hostCourse, String comments) {
        this.mappingId = mappingId;
        this.homeCourse = homeCourse;
        this.hostCourse = hostCourse;
        this.homeCourseName = homeCourse != null ? homeCourse.getTitle() : "";
        this.hostCourseName = hostCourse != null ? hostCourse.getTitle() : "";
        this.comments = comments;
        this.mappingStatus = MappingStatus.ACTIVE;
    }

    public void updateMapping(Course homeCourse, Course hostCourse, String comments) {
        this.homeCourse = homeCourse;
        this.hostCourse = hostCourse;
        this.homeCourseName = homeCourse != null ? homeCourse.getTitle() : "";
        this.hostCourseName = hostCourse != null ? hostCourse.getTitle() : "";
        this.comments = comments;
    }

    public void activate() { this.mappingStatus = MappingStatus.ACTIVE; }
    public void deactivate() { this.mappingStatus = MappingStatus.INACTIVE; }

    public boolean involvesCourse(Course course) {
        if (course == null) return false;
        return (homeCourse != null && homeCourse.getCourseId() == course.getCourseId())
                || (hostCourse != null && hostCourse.getCourseId() == course.getCourseId());
    }

    public int getMappingId() { return mappingId; }
    public void setMappingId(int mappingId) { this.mappingId = mappingId; }
    public Course getHomeCourse() { return homeCourse; }
    public void setHomeCourse(Course homeCourse) { this.homeCourse = homeCourse; }
    public Course getHostCourse() { return hostCourse; }
    public void setHostCourse(Course hostCourse) { this.hostCourse = hostCourse; }
    public String getHomeCourseName() { return homeCourseName; }
    public void setHomeCourseName(String homeCourseName) { this.homeCourseName = homeCourseName; }
    public String getHostCourseName() { return hostCourseName; }
    public void setHostCourseName(String hostCourseName) { this.hostCourseName = hostCourseName; }
    public MappingStatus getMappingStatus() { return mappingStatus; }
    public void setMappingStatus(MappingStatus mappingStatus) { this.mappingStatus = mappingStatus; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}
