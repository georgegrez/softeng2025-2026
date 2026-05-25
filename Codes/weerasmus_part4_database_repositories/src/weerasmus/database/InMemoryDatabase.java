package weerasmus.database;

import java.util.ArrayList;

import weerasmus.model.Accommodation;
import weerasmus.model.Course;
import weerasmus.model.CourseMapping;
import weerasmus.model.EligibilityRule;
import weerasmus.model.ErasmusProgram;
import weerasmus.model.HousingApplication;
import weerasmus.model.Message;
import weerasmus.model.Professor;
import weerasmus.model.ProgramApplication;
import weerasmus.model.ProgramParticipation;
import weerasmus.model.Student;
import weerasmus.model.StudentEvaluation;
import weerasmus.model.Transcript;

public class InMemoryDatabase {
    public static final ArrayList<Student> students = new ArrayList<>();
    public static final ArrayList<Professor> professors = new ArrayList<>();
    public static final ArrayList<ErasmusProgram> programs = new ArrayList<>();
    public static final ArrayList<ProgramApplication> programApplications = new ArrayList<>();
    public static final ArrayList<ProgramParticipation> programParticipations = new ArrayList<>();
    public static final ArrayList<Accommodation> accommodations = new ArrayList<>();
    public static final ArrayList<HousingApplication> housingApplications = new ArrayList<>();
    public static final ArrayList<Course> courses = new ArrayList<>();
    public static final ArrayList<CourseMapping> courseMappings = new ArrayList<>();
    public static final ArrayList<Transcript> transcripts = new ArrayList<>();
    public static final ArrayList<EligibilityRule> eligibilityRules = new ArrayList<>();
    public static final ArrayList<Message> messages = new ArrayList<>();
    public static final ArrayList<StudentEvaluation> studentEvaluations = new ArrayList<>();

    private static int nextStudentId = 1;
    private static int nextProfessorId = 1;
    private static int nextProgramId = 1;
    private static int nextProgramApplicationId = 1;
    private static int nextProgramParticipationId = 1;
    private static int nextAccommodationId = 1;
    private static int nextHousingApplicationId = 1;
    private static int nextCourseId = 1;
    private static int nextCourseMappingId = 1;
    private static int nextTranscriptId = 1;
    private static int nextEligibilityRuleId = 1;
    private static int nextMessageId = 1;
    private static int nextStudentEvaluationId = 1;

    private InMemoryDatabase() {
    }

    public static int generateStudentId() {
        return nextStudentId++;
    }

    public static int generateProfessorId() {
        return nextProfessorId++;
    }

    public static int generateProgramId() {
        return nextProgramId++;
    }

    public static int generateProgramApplicationId() {
        return nextProgramApplicationId++;
    }

    public static int generateProgramParticipationId() {
        return nextProgramParticipationId++;
    }

    public static int generateAccommodationId() {
        return nextAccommodationId++;
    }

    public static int generateHousingApplicationId() {
        return nextHousingApplicationId++;
    }

    public static int generateCourseId() {
        return nextCourseId++;
    }

    public static int generateCourseMappingId() {
        return nextCourseMappingId++;
    }

    public static int generateTranscriptId() {
        return nextTranscriptId++;
    }

    public static int generateEligibilityRuleId() {
        return nextEligibilityRuleId++;
    }

    public static int generateMessageId() {
        return nextMessageId++;
    }

    public static int generateStudentEvaluationId() {
        return nextStudentEvaluationId++;
    }

    public static void clearAll() {
        students.clear();
        professors.clear();
        programs.clear();
        programApplications.clear();
        programParticipations.clear();
        accommodations.clear();
        housingApplications.clear();
        courses.clear();
        courseMappings.clear();
        transcripts.clear();
        eligibilityRules.clear();
        messages.clear();
        studentEvaluations.clear();

        nextStudentId = 1;
        nextProfessorId = 1;
        nextProgramId = 1;
        nextProgramApplicationId = 1;
        nextProgramParticipationId = 1;
        nextAccommodationId = 1;
        nextHousingApplicationId = 1;
        nextCourseId = 1;
        nextCourseMappingId = 1;
        nextTranscriptId = 1;
        nextEligibilityRuleId = 1;
        nextMessageId = 1;
        nextStudentEvaluationId = 1;
    }
}
